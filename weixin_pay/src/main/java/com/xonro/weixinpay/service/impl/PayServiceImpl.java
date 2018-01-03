package com.xonro.weixinpay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.weixinpay.bean.*;
import com.xonro.weixinpay.enums.WechatEnum;
import com.xonro.weixinpay.service.PayService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.util.Map;

/**
 *  微信支付相关服务实现类
 * @author louie modified by Alex
 * @date 2018-1-2
 */
@Service
public class PayServiceImpl implements PayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WXPayConfig wxPayConfig;
    @Autowired
    private PayOrder payOrder;
    @Autowired
    private OrderQuery orderQuery;
    @Autowired
    private CloseOrder closeOrder;
    @Autowired
    private Refund refund;

    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 交易类型
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    @Override
    public PayOrderResult payOrder(@NotNull String body, String tradeNo, Integer totalFee, String openId){
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            Map<String, String> p = payOrder.getDefaultOrderDatas(body,tradeNo,totalFee,ip,openId);
            Map<String,String> resData = wxPay.unifiedOrder(p);
            if (validateWxPayResult(resData)){
                PayOrderResult result = new PayOrderResult();
                BeanUtils.populate(result,resData);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用微信订单号来查询订单
     * @param transactionId 微信订单号
     * @return 订单信息
     */
    @Override
    public Map<String, String> orderQueryByTransactionId(@NotNull String transactionId){
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = orderQuery.getOrderQueryByTransactionId(transactionId);
            Map<String, String> resData = wxPay.orderQuery(p);
            return resData;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户订单号来查询订单
     * @param outTradeNo 商户订单号
     * @return 订单信息
     */
    @Override
    public Map<String, String> orderQueryByOutTradeNo(@NotNull String outTradeNo) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = orderQuery.getOrderQueryByOutTradeNo(outTradeNo);
            Map<String, String> resData = wxPay.orderQuery(p);
            return resData;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户订单号来关闭订单
     *
     * @param outTradeNo 商户订单号
     * @return 返回结果
     */
    @Override
    public CloseOrderResult closeOrderByOutTradeNo(@NotNull String outTradeNo) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = closeOrder.getCloseOrderByOutTradeNo(outTradeNo);
            Map<String, String> resData = wxPay.closeOrder(p);
            if (validateWxPayResult(resData)){
                CloseOrderResult result = new CloseOrderResult();
                BeanUtils.populate(result,resData);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用微信订单号退款
     *
     * @param transactionId 微信订单号
     * @param outRefundNo   商户退款单号
     * @param totalFee      订单金额
     * @param refundFee     退款金额
     * @param refundFeeType 货币种类
     * @param refundDesc    退款原因
     * @param refundAccount 退款资金来源
     * @return 退款结果
     */
    @Override
    public Map<String, String> refundByTransactionId(@NotNull String transactionId, @NotNull String outRefundNo, @NotNull Integer totalFee, @NotNull Integer refundFee, String refundFeeType, String refundDesc, String refundAccount) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = refund.refundByTransactionId(transactionId, outRefundNo, totalFee, refundFee, refundFeeType, refundDesc, refundAccount);
            Map<String, String> resData = wxPay.refund(p);
            return resData;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户订单号退款
     *
     * @param outTradeNo    商户订单号
     * @param outRefundNo   商户退款单号
     * @param totalFee      订单金额
     * @param refundFee     退款金额
     * @param refundFeeType 货币种类
     * @param refundDesc    退款原因
     * @param refundAccount 退款资金来源
     * @return 退款结果
     */
    @Override
    public Map<String, String> refundByOutTradeNo(@NotNull String outTradeNo, @NotNull String outRefundNo, @NotNull Integer totalFee, @NotNull Integer refundFee, String refundFeeType, String refundDesc, String refundAccount) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = refund.refundByOutTradeNo(outTradeNo, outRefundNo, totalFee, refundFee, refundFeeType, refundDesc, refundAccount);
            Map<String, String> resData = wxPay.refund(p);
            return resData;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }


    private boolean validateWxPayResult(Map<String,String> resDatas){
        if (resDatas != null && !resDatas.isEmpty()){
            String reCode = resDatas.get("return_code");
            if (StringUtils.isNotEmpty(reCode) && WechatEnum.RETURN_CODE_SUCCESS.getValue().equals(reCode)){
                return true;
            }
            logger.error(resDatas.get("return_msg"),new Throwable("request wxpay service error"));
        }else {
            logger.error("service response is empty",new Throwable("request wxpay service error"));
        }
        return false;
    }
}
