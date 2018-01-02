package com.xonro.weixinpay.service;

import com.xonro.weixinpay.bean.PayOrderResult;

import javax.validation.constraints.NotNull;

/**
 * 微信支付相关服务接口
 * @author louie
 * @date 2018-1-2
 */
public interface PayService {
    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 交易类型
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    PayOrderResult payOrder(@NotNull String body, String tradeNo, Integer totalFee, String openId);
}
