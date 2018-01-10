package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.bean.CustomServiceResult;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.CustomService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Alex
 * @date 2018/1/10
 */
@Service
public class CustomServiceImpl implements CustomService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;
    @Override
    public CustomServiceResult addCustom() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", "test1@test");
        jsonObject.put("nickname", "客服1");
        jsonObject.put("password", "123456");
        CustomServiceResult customServiceResult = null;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceAdd()).executePostRequest(jsonObject.toJSONString(),CustomServiceResult.class);
            return customServiceResult;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
       return null;
    }
}
