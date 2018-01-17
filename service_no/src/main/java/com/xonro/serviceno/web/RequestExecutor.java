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
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
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
    private ExecuteResult executeResult;

    /**
     * 执行http请求
     * @param requestData 请求参数，可为空，为空时执行get请求，非空时执行post请求
     * @return 执行结果
     * @throws IOException
     * @throws WechatException
     */
    public RequestExecutor execute(String ... requestData) throws IOException, WechatException {
        String response = "";

        try {
            //有请求参数，执行post请求
            if (requestData != null && requestData.length > 0){
                String data = requestData[0];
                response = Request.Post(requestUrl).connectTimeout(3000).socketTimeout(3000)
                        .bodyString(data, ContentType.APPLICATION_JSON)
                        .execute()
                        .returnContent().asString(charset);
            }
            //无请求参数，执行Get请求
            else {
                response = Request.Get(requestUrl).connectTimeout(3000).socketTimeout(3000)
                        .execute().returnContent().asString(charset);
            }

            if (validateResult(response)){
                this.executeResult = new ExecuteResult(response);
                return this;
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
     * 将响应信息转为对象实例
     * @param objectClass 期望获取的实例类型
     * @param <T>
     * @return 转换后的对象实例
     */
    public <T>T getResponseAsObject(Class<T> objectClass){
        return this.executeResult.getResponseAsObject(objectClass);
    }

    /**
     * 将响应信息转为对象实例集合
     * @param objectClass 期望获取的实例类型
     * @param <T>
     * @return 转换后的对象实例集合
     */
    public <T>List<T> getResponseAsList(Class<T> objectClass){
        return this.executeResult.getResponseAsList(objectClass);
    }

    /**
     * 请求执行结果内部类
     */
    private class ExecuteResult{
        /**
         * http请求响应结果
         */
        private String response;

        private ExecuteResult(String response){
            this.response = response;
        }

        private <T>T getResponseAsObject(Class<T> objectClass){
            return JSON.parseObject(response,objectClass);
        }

        private <T>List<T> getResponseAsList(Class<T> objectClass){
            return JSON.parseArray(response,objectClass);
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
     * @param title 视频素材的标题
     * @param introduction 视频素材的描述
     * @param <T> 泛型
      * @return 请求结果
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

    /**
     * 上传文件
     * @param file 文件
     * @param <T> 泛型
     * @return 请求结果
     */
    public <T>T postFile(File file, Class<T> tClass) {
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
//            output.write(("--" + boundary + "\r\n").getBytes());
//            output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
//            output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}",title,introduction).getBytes());
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
