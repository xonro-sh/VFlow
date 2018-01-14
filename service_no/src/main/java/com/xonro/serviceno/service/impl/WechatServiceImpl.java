package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.bean.CreateQrCode;
import com.xonro.serviceno.bean.QrCode;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.WechatService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author louie
 * @date 2018-1-12
 */
@Service
public class WechatServiceImpl implements WechatService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;

    @Override
    public QrCode createQrCode(Long expireSeconds, Object sceneValue) {
        RequestExecutor executor = new RequestExecutor(urlBuilder.buildCreateQrCodeUrl());
        try {
            // 1、获取含有参数二维码
            CreateQrCode createQrCode = new CreateQrCode(expireSeconds,sceneValue);
            JSONObject jsonObject = executor.executePostRequest(JSON.toJSONString(createQrCode),JSONObject.class);
            String ticket = jsonObject.getString("ticket");
            long reExpireSeconds = jsonObject.getLong("expire_seconds");
            String url = jsonObject.getString("url");

            // 2、通过ticket换取二维码图片
            return new QrCode(
                    reExpireSeconds,System.currentTimeMillis()/1000,url,
                    new RequestExecutor(urlBuilder.buildQrCodeImageUrl(ticket)).downloadFile()
            );
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public byte[] getQrCodeImage(String ticket) {


        return new byte[0];
    }
}
