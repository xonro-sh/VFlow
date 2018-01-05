package com.xonro.weixinpay.service.impl;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Alex
 * @date 2018/1/2
 */
@Service
public class WxPayConfImpl implements WXPayConfig{
    /**
     * 公众账号ID
     */
    @Value("${wechat.appID}")
    private String appId;

    /**
     * 商户号
     */
    @Value("${wechat.pay.mch_id}")
    private String mchId;

    /**
     * 商户号支付key
     */
    @Value("${wechat.pay.key}")
    private String payKey;

    private InputStream inputStream = WxPayConfImpl.class.getClassLoader().getResourceAsStream("apiclient_cert.p12");

    public WxPayConfImpl() throws FileNotFoundException {
    }

    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchId;
    }

    @Override
    public String getKey() {
        return this.payKey;
    }

    @Override
    public InputStream getCertStream() {
        return this.inputStream;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 8000;
    }
}
