package com.xonro.weixinpay.service.impl;

import com.xonro.serviceno.service.WechatService;
import com.xonro.weixinpay.service.PayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @date 2018/1/30
 */
@Component
public class PayConfRunner implements CommandLineRunner{
    @Autowired
    PayConfService payConfService;
    @Autowired
    WechatService wechatService;
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        payConfService.getConfFromCache();
        wechatService.getConfFromCache();
    }
}
