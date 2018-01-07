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
     * @param refundByTransactionId 使用微信订单号来获取退款模型
     * @return 退款结果
     */
    Map<String, String> refundByTransactionId(RefundByTransactionId refundByTransactionId);

    /**
     * 用商户订单号退款
     * @param refundByOutTradeNo 使用商户订单号来获取退款模型
     * @return 退款结果
     */
    Map<String, String> refundByOutTradeNo(RefundByOutTradeNo refundByOutTradeNo);

    /**
     * 用微信订单号查询退款记录
     * @param transactionId 微信订单号
     * @param offset 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    Map<String, String> refundQueryByTransactionId(@NotNull String transactionId, String offset);

    /**
     * 用商户订单号查询退款记录
     * @param outTradeNo 商户订单号
     * @param offset 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    Map<String, String> refundQueryByOutTradeNo(@NotNull String outTradeNo, String offset);

    /**
     * 用商户退款单号查询退款记录
     * @param outRefundNo 商户退款单号
     * @param offset 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    Map<String, String> refundQueryByOutRefundNo(@NotNull String outRefundNo, String offset);

    /**
     * 用微信退款单号查询退款记录
     * @param refundId 微信退款单号
     * @param offset 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    Map<String, String> refundQueryByRefundId(@NotNull String refundId, String offset);

    /**
     * 下载对账单
     * @param billDate 对账单日期
     * @param billType 账单类型
     * @return 对账单数据
     */
    Map<String, String> downloadBill(@NotNull String billDate, @NotNull String billType);

    /**
     * 微信支付结果通知
     * @param notifyData 支付结果
     * @return 支付结果
     */
    String payNotify(String notifyData);

    /**
     * 微信退款结果通知
     * @param notifyData 退款结果
     * @return 退款结果
     */
    String refundNotify(String notifyData);

    /**
     * 交易保障
     * @param reportRequestParam 交易保障请求参数模型
     * @return 返回结果
     */
    ReportResult report(ReportRequestParam reportRequestParam);
}
