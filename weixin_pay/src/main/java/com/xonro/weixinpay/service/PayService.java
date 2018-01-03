package com.xonro.weixinpay.service;

import com.xonro.weixinpay.bean.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

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

    /**
     * 用微信订单号来查询订单
     * @param transactionId 微信订单号
     * @return 订单信息
     */
    Map<String, String> orderQueryByTransactionId(@NotNull String transactionId);

    /**
     * 用商户订单号来查询订单
     * @param outTradeNo 商户订单号
     * @return 订单信息
     */
    Map<String, String> orderQueryByOutTradeNo(@NotNull String outTradeNo);

    /**
     * 用商户订单号来关闭订单
     * @param outTradeNo 商户订单号
     * @return 返回结果
     */
    CloseOrderResult closeOrderByOutTradeNo(@NotNull String outTradeNo);

    /**
     * 用微信订单号退款
     * @param transactionId 微信订单号
     * @param outRefundNo 商户退款单号
     * @param totalFee 订单金额
     * @param refundFee 退款金额
     * @param refundFeeType 货币种类
     * @param refundDesc 退款原因
     * @param refundAccount 退款资金来源
     * @return 退款结果
     */
    Map<String, String> refundByTransactionId(@NotNull String transactionId,
                                              @NotNull String outRefundNo,
                                              @NotNull Integer totalFee,
                                              @NotNull Integer refundFee,
                                              String refundFeeType,
                                              String refundDesc,
                                              String refundAccount);

    /**
     * 用商户订单号退款
     * @param outTradeNo 商户订单号
     * @param outRefundNo 商户退款单号
     * @param totalFee 订单金额
     * @param refundFee 退款金额
     * @param refundFeeType 货币种类
     * @param refundDesc 退款原因
     * @param refundAccount 退款资金来源
     * @return 退款结果
     */
    Map<String, String> refundByOutTradeNo(@NotNull String outTradeNo,
                                           @NotNull String outRefundNo,
                                           @NotNull Integer totalFee,
                                           @NotNull Integer refundFee,
                                           String refundFeeType,
                                           String refundDesc,
                                           String refundAccount);
}
