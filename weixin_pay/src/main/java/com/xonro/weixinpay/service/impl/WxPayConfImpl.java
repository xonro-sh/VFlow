package com.xonro.weixinpay.service.impl;

import com.github.wxpay.sdk.WXPayConfig;
import com.xonro.serviceno.service.ServiceNoConfService;
import com.xonro.weixinpay.service.PayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Alex
 * @date 2018/1/2
 */
@Component
public class WxPayConfImpl implements WXPayConfig{
    @Autowired
    private PayConfService payConfService;
    @Autowired
    private ServiceNoConfService serviceNoConfService;

    private InputStream inputStream = WxPayConfImpl.class.getClassLoader().getResourceAsStream("apiclient_cert.p12");

    public WxPayConfImpl() throws FileNotFoundException {
    }

    @Override
    public String getAppID() {
        return serviceNoConfService.getConfFromCache().getAppId();
    }

    @Override
    public String getMchID() {
        return payConfService.getConfFromCache().getMchId();
    }

    @Override
    public String getKey() {
        return payConfService.getConfFromCache().getApiKey();
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
