package com.xonro.vflow.web.controller;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bean.BaseResponse;
import com.xonro.vflow.bean.wechat.ServiceNoConf;
import com.xonro.vflow.bean.wechat.WxPayConf;
import com.xonro.vflow.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @date 2018/1/25
 */
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
    @Autowired
    private WechatService wechatService;
    /**
     * 更新微信服务号配置
     * @param data json数据
     * @return 结果
     */
    @RequestMapping(value = "/updateServiceNoConf")
    public BaseResponse updateServiceNoConf(String data){
        return wechatService.updateServiceNoConf(JSON.parseObject(data, ServiceNoConf.class));
    }

    /**
     * 获取微信服务号配置
     * @return 结果
     */
    @RequestMapping(value = "/getServiceNoConf")
    public BaseResponse getServiceNoConf(){
        return wechatService.getServiceNoConf();
    }

    /**
     * 更新微信支付配置
     * @param data json数据
     * @return 结果
     */
    @RequestMapping(value = "/updateWxPayConf")
    public BaseResponse updateWxPayConf(String data){
        return wechatService.updateWxPayConf(JSON.parseObject(data, WxPayConf.class));
    }

    /**
     * 获取微信支付配置
     * @return 结果
     */
    @RequestMapping(value = "/getWxPayConf")
    public BaseResponse getWxPayConf(){
        return wechatService.getWxPayConf();
    }
}
