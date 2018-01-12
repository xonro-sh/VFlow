package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.WechatJsApiTicket;
import com.xonro.serviceno.bean.WechatJsSignature;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.JsApiService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author Alex
 * @date 2018/1/9
 */
@Service
public class JsApiServiceImpl implements JsApiService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlBuilder urlBuilder;

    private WechatJsApiTicket jsApiTicket;

    /**
     * 获取微信公众平台jsapi_ticket临时票据
     * @return  jsapi_ticket
     */
    @Override
    public WechatJsApiTicket getJsApiTicket() throws IOException, WechatException {
        String url = urlBuilder.buildJsApiTicketUrl();
        try {
            jsApiTicket = new RequestExecutor(url).executeGetRequest(WechatJsApiTicket.class);
            jsApiTicket.setAccessTime(System.currentTimeMillis()/1000);
            return jsApiTicket;
        } catch (WechatException e) {
           logger.error(e.getMessage(),e);
           throw e;
        }
    }

    /**
     * 从缓存中获取jsapi临时票据
     * @return 从缓存中获取jsapi临时票据
     * @throws IOException io异常
     */
    @Override
    public String getJsApiTicketFromCache() throws IOException,WechatException {
        if(jsApiTicket == null){
            return getJsApiTicket().getTicket();
        }

        Long expiresInTime = jsApiTicket.getExpiresIn();
        Long accessTime = jsApiTicket.getAccessTime();
        //已失效
        if ((System.currentTimeMillis()/1000 - accessTime) >= expiresInTime){
            return getJsApiTicket().getTicket();
        }else {
            return jsApiTicket.getTicket();
        }
    }

    @Value("${wechat.appId}")
    private String appId;

    /**
     * 生成指定url地址的js signature
     * @param url 链接地址
     * @return js签名
     */
    @Override
    public WechatJsSignature getSignature(String url) {
        try {
            //10位随机串
            String noncestr = RandomStringUtils.randomAlphanumeric(10);
            String jsapiTicket = getJsApiTicketFromCache();
            Long timestamp = System.currentTimeMillis()/1000;

            String stringBuilder = "jsapi_ticket=" + jsapiTicket +
                    "&noncestr=" + noncestr +
                    "&timestamp=" + timestamp +
                    "&url=" + URLDecoder.decode(url, "UTF-8");

            String signature = DigestUtils.sha1Hex(stringBuilder);
            WechatJsSignature jsSignature = new WechatJsSignature();
            jsSignature.setAppId(appId);
            jsSignature.setNonceStr(noncestr);
            jsSignature.setSignature(signature);
            jsSignature.setTimestamp(timestamp);

            return jsSignature;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
