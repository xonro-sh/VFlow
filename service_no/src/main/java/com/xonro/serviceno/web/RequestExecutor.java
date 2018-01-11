package com.xonro.serviceno.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 请求执行器
 * @author Alex
 * @date 2018/1/8
 */
public class RequestExecutor {
    public RequestExecutor(String requestUrl){
        this.requestUrl = requestUrl;
    }

    private String requestUrl;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String charset = Charset.forName("UTF-8").displayName();

    /**
     * 执行get请求
     * @param tClass 预期返回的Class类型
     * @param <T> 泛型
     * @return 请求结果
     * @throws IOException IO异常
     */
    public <T>T executeGetRequest(Class<T> tClass) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(10000).build();
            HttpGet httpGet = new HttpGet(requestUrl);
            httpGet.setConfig(config);

            response = httpClient.execute(httpGet,new BasicHttpContext());
            if(response.getStatusLine().getStatusCode() != 200){
                logger.error("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + requestUrl);
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null){
                String result = EntityUtils.toString(entity,charset);
                if (validateResult(result)){
                    return JSON.parseObject(result,tClass);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }finally {
            if (response != null){
                response.close();
            }
            httpClient.close();
        }
        return null;
    }

    /**
     * 执行get请求
     * @return 请求结果
     * @throws IOException IO异常
     */
    public String executeGetRequest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(10000).build();
            HttpGet httpGet = new HttpGet(requestUrl);
            httpGet.setConfig(config);

            response = httpClient.execute(httpGet,new BasicHttpContext());
            if(response.getStatusLine().getStatusCode() != 200){
                logger.error("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + requestUrl);
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null){
                String result = EntityUtils.toString(entity,charset);
                if (validateResult(result)){
                    return result;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }finally {
            if (response != null){
                response.close();
            }
            httpClient.close();
        }
        return null;
    }

    /**
     * 执行post请求
     * @param requestData 请求数据
     * @param tClass 预期返回的Class类型
     * @param <T> 泛型
     * @return 请求结果
     * @throws IOException IO异常
     */
    public <T>T executePostRequest(String requestData,Class<T> tClass) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type","application/json");

        try {
            StringEntity entity = new StringEntity(requestData,charset);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost,new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200){
                logger.error("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + requestUrl);
                return null;
            }

            HttpEntity resEntity = response.getEntity();
            if (resEntity != null){
                String result = EntityUtils.toString(resEntity,charset);
                if (validateResult(result)){
                    return JSON.parseObject(result,tClass);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }finally {
            if (response != null){
                response.close();
            }
            httpClient.close();
        }
        return null;
    }

    /**
     * 校验微信请求是否获取正确响应
     * @param result 微信的响应信息
     * @return true正确相应 false响应失败
     */
    private boolean validateResult(String result){
        JSONObject jObject = JSONObject.parseObject(result);
        String errcode = jObject.getString("errcode");
        if (StringUtils.isEmpty(errcode) || "0".equals(errcode.trim())){
            return true;
        }
        logger.error("访问微信失败，url："+requestUrl+",错误信息："+result);
        return false;
    }

    public <T>T postFile(String filePath,
                                  String title,String introduction, Class<T> tClass) {
        File file = new File(filePath);
        if(!file.exists()) {
            return null;
        }
        String result = null;
        try {
            URL url1 = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write("Content-Type:application/octet-stream \r\n\r\n".getBytes());
            byte[] data = new byte[1024];
            int len;
            FileInputStream input = new FileInputStream(file);
            while((len=input.read(data))>-1){
                output.write(data, 0, len);
            }
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
            output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}",title,introduction).getBytes());
            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            input.close();
            InputStream resp = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            while((len= resp.read(data))>-1) {
                sb.append(new String(data, 0, len, "utf-8"));
            }
            resp.close();
            result = sb.toString();
            System.out.println(result);
        } catch (ClientProtocolException e) {
            logger.error("postFile，不支持http协议",e);
        } catch (IOException e) {
            logger.error("postFile数据传输失败",e);
        }
        logger.info("{}: result={}",requestUrl,result);
        return JSON.parseObject(result,tClass);
    }

//    public static void main(String[] args) {
//        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="
//                + "U_0AIUrkfmVk0LXlaGu0lx5CXx-_4&type=video";
//        postFile(url, "/Users/jlusoft/Desktop/test.mp4","test","dfd");
//    }
}
