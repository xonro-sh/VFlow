package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.WechatJsApiTicket;
import com.xonro.serviceno.bean.WechatJsSignature;
import com.xonro.serviceno.exception.WechatException;

import java.io.IOException;

/**
 * @author Alex
 * @date 2018/1/9
 */
public interface JsApiService {
    /**
     * 获取微信公众平台jsapi_ticket临时票据
     * @return  jsapi_ticket
     */
    WechatJsApiTicket getJsApiTicket() throws IOException, WechatException;

    /**
     * 从缓存中获取jsapi临时票据
     * @return 从缓存中获取jsapi临时票据
     * @throws IOException io异常
     */
    String getJsApiTicketFromCache() throws IOException, WechatException;

    /**
     * 生成指定url地址的js signature
     * @param url 链接地址
     * @return js签名
     */
    WechatJsSignature getSignature(String url);
}
