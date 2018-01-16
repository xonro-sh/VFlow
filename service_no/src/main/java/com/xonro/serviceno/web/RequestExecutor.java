package com.xonro.serviceno.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.exception.WechatException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

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
    private Charset charset = Charset.forName("UTF-8");
    /**
     * 执行get请求
     * @param tClass 预期返回的Class类型
     * @return 请求结果
     * @throws IOException IO异常
     */
    public <T>T executeGetRequest(Class<T> tClass) throws IOException, WechatException {
        try {
            String response = doRequest();
            if (validateResult(response)){
                return JSON.parseObject(response,tClass);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 执行get请求，将结果转为list返回
     * @param tClass 期望返回集合的类型
     * @return 指定类型对象集合
     * @throws WechatException
     * @throws IOException
     */
    public <T>List<T> exeGetRequestAsList(Class<T> tClass) throws WechatException, IOException {
        try {
            String response = doRequest();
            if (validateResult(response)){
                return JSON.parseArray(response,tClass);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 执行post请求
     * @param requestData post请求提交的请求参数
     * @param tClass 期望返回的对象类型
     * @return 期望返回的对象实例
     * @throws IOException
     * @throws WechatException
     */
    public <T>T executePostRequest(String requestData,Class<T> tClass) throws IOException, WechatException {
        try {
            String response = doRequest(requestData);
            if (validateResult(response)){
                return JSON.parseObject(response,tClass);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 执行post请求，将结果转为list返回
     * @param tClass 期望返回集合的类型
     * @return 指定类型对象集合
     * @throws WechatException
     * @throws IOException
     */
    public <T>List<T> exePostRequestAsList(String requestData,Class<T> tClass) throws IOException, WechatException {
        try {
            String response = doRequest(requestData);
            if (validateResult(response)){
                return JSON.parseArray(response,tClass);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 执行http请求
     * @param data 请求数据，有参数则执行post请求，无参数则执行get请求
     * @return http请求响应的字符信息
     */
    private String doRequest(String ... data) throws IOException {
        //有请求参数，执行post请求
        if (data != null && data.length > 0){
            String requestData = data[0];
            return Request.Post(requestUrl).connectTimeout(3000).socketTimeout(3000)
                    .bodyString(requestData, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent().asString(charset);
        }
        //无请求参数，执行Get请求
        else {
            return Request.Get(requestUrl).connectTimeout(3000).socketTimeout(3000)
                    .execute().returnContent().asString(charset);
        }
    }

    /**
     * 下载文件
     * @return 返回下载文件的字节流数据
     * @throws IOException
     */
    public byte[] downloadFile() throws IOException {
        return Request.Get(requestUrl).connectTimeout(3000).socketTimeout(3000)
                .execute().returnContent().asBytes();
    }

    /**
     * 上传文件
     * @param fileData 文件字节数组
     * @param fileName 文件名称
     * @param tClass 期望返回的对象类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T>T uploadFile(String fileName,byte[] fileData,Class<T> tClass) throws WechatException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        InputStreamBody inputStreamBody = null;
        ByteArrayInputStream arrayInputStream = null;
        try {
            arrayInputStream = new ByteArrayInputStream(fileData);
            inputStreamBody = new InputStreamBody(arrayInputStream,fileName);
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(Charset.forName("UTF-8"))
                    .addPart("media",inputStreamBody)
                    .build();

            RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build();
            HttpPost post = new HttpPost(requestUrl);
            post.setConfig(config);

            post.setEntity(httpEntity);
            response = client.execute(post);

            if(response.getStatusLine().getStatusCode() != 200){
                throw new WechatException(response.getStatusLine().getStatusCode()+"","request wechat fail,http code=" + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();
            if (entity != null){
                return JSONObject.parseObject(EntityUtils.toString(entity,charset),tClass);
            }else {
                throw new WechatException("request wechat fail","response is empty");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } finally {
            arrayInputStream.close();
            if (response != null){
                response.close();
            }
            client.close();
        }
    }

    /**
     * 校验微信请求是否获取正确响应
     * @param result 微信的响应信息
     * @return true正确相应 false响应失败
     */
    private boolean validateResult(String result) throws WechatException {
        JSONObject jObject = JSONObject.parseObject(result);
        String errcode = jObject.getString("errcode");
        if (StringUtils.isEmpty(errcode) || "0".equals(errcode.trim())){
            return true;
        }
        logger.error("访问微信失败，url："+requestUrl+",错误信息："+result);
        throw new WechatException(errcode,jObject.getString("errmsg"));
    }

    /**
     * 上传文件
     * @param file 文件
     * @param title 标题
     * @param introduction 介绍
     * @param <T> 泛型
      * @return 请求结果
     * @return result
     */
    public <T>T postFile(File file,
                                  String title,String introduction, Class<T> tClass) {
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

}
