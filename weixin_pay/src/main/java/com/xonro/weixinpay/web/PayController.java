package com.xonro.weixinpay.web;

import com.xonro.weixinpay.bean.PayOrderResult;
import com.xonro.weixinpay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 支付服务控制器
 * @author louie
 * @date 2018-1-2
 */

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 交易类型
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    @RequestMapping(value = "/payOrder")
    public PayOrderResult createPayOrder(@RequestParam String body, @RequestParam String tradeNo, @RequestParam Integer totalFee, String openId){
        return payService.payOrder(body,tradeNo, totalFee, openId);
    }
}
