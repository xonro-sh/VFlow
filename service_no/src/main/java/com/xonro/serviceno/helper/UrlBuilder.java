package com.xonro.serviceno.helper;

import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信公众平台请求服务url构建器
 * @author Alex
 * @date 2018/1/8
 */
@Component
public class UrlBuilder {
    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    TokenService tokenService;

    /**
     * 构架获取accessTocken的请求url
     * @return 构建完成的请求url
     */
    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ appID+"&secret="+appSecret;
    }

    /**
     * 构建获取jsapi_ticket临时票据的请求url
     * @return 构建完成的请求url
     */
    public String buildJsApiTicketUrl(){
        return WechatEnums.URL_JSAPI_TICKET.getValue()+"access_token="+tokenService.getTokenFromCache()+"&type=jsapi";
    }

    /**
     * 构建添加客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomServiceAdd(){
        return WechatEnums.URL_CUSTOMSERVICE_ADD.getValue()+"access_token="+tokenService.getTokenFromCache();
    }
}
