package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.WechatAccessToken;
import com.xonro.serviceno.exception.WechatException;

import java.io.IOException;

/**
 * 凭证相关业务服务接口
 * @author louie modified by Alex
 * @date 2018-1-3
 */
public interface TokenService {
    /**
     * 签名认证
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 签名认证成功 返回：echostr 随机字符串 失败返回其他
     */
    String checkSignature(String signature, Long timestamp, String nonce, String echostr);

    /**
     * 获取身份凭证
     * @return WechatAccessToken 对象
     * @throws IOException IO异常
     */
    WechatAccessToken getAccessToken() throws IOException, WechatException;

    /**
     * 获取缓存的微信身份凭证
     * @return token
     */
    String getTokenFromCache() ;
}
