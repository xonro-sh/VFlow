package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.WechatAccessToken;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.TokenService;
import com.xonro.serviceno.service.WechatService;
import com.xonro.serviceno.web.RequestExecutor;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Alex
 * @date 2018/1/8
 */
@Service
public class TokenServiceImpl implements TokenService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WechatService wechatService;
    @Autowired
    private UrlBuilder urlBuilder;

    private WechatAccessToken tokenCache;
    /**
     * 签名认证
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 签名认证成功 返回：echostr 随机字符串 失败返回其他
     */
    @Override
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr) {
        String[] array = new String[]{timestamp+"",nonce, wechatService.getConfFromCache().getToken()};
        Arrays.sort(array);

        StringBuilder builder = new StringBuilder();
        for (String str : array) {
            builder.append(str);
        }

        String digested = DigestUtils.sha1Hex(builder.toString());
        if (signature.equals(digested)){
            return echostr;
        }
        return null;
    }


    @Override
    public String getTokenFromCache() {
        try {
            if (tokenCache == null){
                return getAccessToken().getAccessToken();
            }

            Long expiresIn = tokenCache.getExpiresIn();
            Long accessTime = tokenCache.getAccessTimestamp();

            if (System.currentTimeMillis()/1000 - accessTime >= expiresIn){
                return getAccessToken().getAccessToken();
            }else {
                return tokenCache.getAccessToken();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @CachePut(value = "tokenCache",key = "wechatToken")
    public void cacheToken(WechatAccessToken token){
        tokenCache = token;
    }

    @Override
    public WechatAccessToken getAccessToken() throws IOException, WechatException {
        String url = urlBuilder.buildGetTokenUrl();
        try {
            WechatAccessToken token = new RequestExecutor(url).execute().getResponseAsObject(WechatAccessToken.class);
            token.setAccessTimestamp(System.currentTimeMillis()/1000);
            cacheToken(token);
            return token;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }catch (WechatException e){
            logger.error(e.getMessage(),e);
            throw e;
        }
    }
}
