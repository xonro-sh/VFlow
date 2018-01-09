package com.xonro.serviceno.web.controller;

import com.xonro.serviceno.bean.WechatJsSignature;
import com.xonro.serviceno.service.JsApiService;
import com.xonro.serviceno.service.MessageService;
import com.xonro.serviceno.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众平台相关服务控制器
 * @author louie modified by Alex
 * @date 2018-1-3
 */
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JsApiService jsApiService;
    @Autowired
    private MessageService messageService;
    /**
     * 微信平台服务器配置签名认证
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 签名认证成功 返回：echostr 随机字符串 失败返回其他
     */
    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr){
        return tokenService.checkSignature(signature,timestamp,nonce,echostr);
    }

    /**
     * 生成指定url地址的js signature
     * @param rquestPage 请求页面
     * @return js签名
     */
    @RequestMapping(value = "/jsSignature")
    public WechatJsSignature getJsSignature(String rquestPage){
        return jsApiService.getSignature(rquestPage);
    }

    /**
     * 微信平台消息、事件接收接口
     * @param message 微信平台post的消息xml
     * @return 接收状态
     */
    @RequestMapping(value = "/access",method = RequestMethod.POST)
    public String accessEvent(@RequestBody String message){
        return messageService.parseMessage(message);
    }

}
