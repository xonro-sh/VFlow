package com.xonro.serviceno.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
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

import java.io.IOException;
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
}
