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
    private String response;

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
                this.response = response;
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
     * 上传文件
     * @param fileName 文件名称
     * @param mediaData 文件字节码数组
     * @return 上传结果
     * @throws IOException
     * @throws WechatException
     */
    public RequestExecutor upload(String fileName,byte[] mediaData) throws IOException, WechatException {
        try {
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                    .addBinaryBody(fileName,mediaData,ContentType.DEFAULT_BINARY,fileName)
                    .build();

            String response = Request.Post(requestUrl)
                    .body(httpEntity)
                    .execute()
                    .returnContent().asString(charset);

            if (validateResult(response)){
                this.response = response;
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
        return JSON.parseObject(response,objectClass);
    }

    /**
     * 将响应信息转为对象实例集合
     * @param objectClass 期望获取的实例类型
     * @param <T>
     * @return 转换后的对象实例集合
     */
    public <T>List<T> getResponseAsList(Class<T> objectClass){
        return JSON.parseArray(response,objectClass);
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

}
