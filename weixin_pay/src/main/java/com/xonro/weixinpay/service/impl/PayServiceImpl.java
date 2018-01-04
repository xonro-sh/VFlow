package com.xonro.weixinpay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.weixinpay.bean.*;
import com.xonro.weixinpay.enums.WechatEnum;
import com.xonro.weixinpay.exception.WxPayException;
import com.xonro.weixinpay.service.PayService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.sql.Timestamp;
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
            Map<String, String> p = new PayOrder().getDefaultOrderDatas(body,tradeNo,totalFee,ip,openId);
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
            Map<String, String> p = new OrderQuery().getOrderQueryByTransactionId(transactionId);
            return wxPay.orderQuery(p);
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
            Map<String, String> p = new OrderQuery().getOrderQueryByOutTradeNo(outTradeNo);
            return wxPay.orderQuery(p);
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
            Map<String, String> p = new CloseOrder().getCloseOrderByOutTradeNo(outTradeNo);
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
     * @param refundByTransactionId 使用微信订单号来获取退款模型
     * @return 退款结果
     */
    @Override
    public Map<String, String> refundByTransactionId(RefundByTransactionId refundByTransactionId) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new Refund().refundByTransactionId(refundByTransactionId);
            return wxPay.refund(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户订单号退款
     *
     * @param refundByOutTradeNo 使用商户订单号来获取退款模型
     * @return 退款结果
     */
    @Override
    public Map<String, String> refundByOutTradeNo(RefundByOutTradeNo refundByOutTradeNo) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new Refund().refundByOutTradeNo(refundByOutTradeNo);
            return wxPay.refund(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用微信订单号查询退款记录
     *
     * @param transactionId 微信订单号
     * @param offset        偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    @Override
    public Map<String, String> refundQueryByTransactionId(@NotNull String transactionId, String offset) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new RefundQuery().getRefundQueryByTransactionId(transactionId, offset);
            System.err.println(""+wxPay.refundQuery(p));
            return wxPay.refundQuery(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户订单号查询退款记录
     *
     * @param outTradeNo 商户订单号
     * @param offset     偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    @Override
    public Map<String, String> refundQueryByOutTradeNo(@NotNull String outTradeNo, String offset) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new RefundQuery().getRefundQueryByOutTradeNo(outTradeNo, offset);
            return wxPay.refundQuery(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用商户退款单号查询退款记录
     *
     * @param outRefundNo 商户退款单号
     * @param offset      偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    @Override
    public Map<String, String> refundQueryByOutRefundNo(@NotNull String outRefundNo, String offset) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new RefundQuery().getRefundQueryByOutRefundNo(outRefundNo, offset);
            return wxPay.refundQuery(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 用微信退款单号查询退款记录
     *
     * @param refundId 微信退款单号
     * @param offset   偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 退款记录
     */
    @Override
    public Map<String, String> refundQueryByRefundId(@NotNull String refundId, String offset) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new RefundQuery().getRefundQueryByRefundId(refundId, offset);
            return wxPay.refundQuery(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 下载对账单
     * @param billDate 对账单日期
     * @param billType 账单类型
     * @return 对账单数据
     */
    @Override
    public Map<String, String> downloadBill(@NotNull String billDate, @NotNull String billType) {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5, false);
        try {
            Map<String, String> p = new DownloadBill().getDownloadBillByDateAndType(billDate, billType);
            System.err.println("wxPay.downloadBill(p);"+wxPay.downloadBill(p));
            return wxPay.downloadBill(p);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 微信支付结果通知
     * @param notifyData 支付结果
     * @return 支付结果
     */
    @Override
    public String payNotify(String notifyData) {
        WxPayException result = new WxPayException();
        WXPay wxPay = new WXPay(wxPayConfig);
        try {
            Map<String,String> notifyMap = WXPayUtil.xmlToMap(notifyData);
            //签名校验通过
            if (wxPay.isPayResultNotifySignatureValid(notifyMap)){
                String resultCode = notifyMap.get("result_code");
                if (StringUtils.isNotEmpty(resultCode) && WechatEnum.RETURN_CODE_SUCCESS.getValue().equals(resultCode)){
                    return "";

                }else {//业务错误
                    logger.error("微信支付出现业务错误，微信支付通知数据："+notifyData,new Throwable("wxPay result error,err_code:"+notifyMap.get("err_code")));
                }
                Map<String,String> resultMap = BeanUtils.describe(result);
                resultMap.remove("class");
                return WXPayUtil.mapToXml(resultMap);
            }else {
                logger.error("微信支付结果通知签名校验失败，支付通知数据："+notifyData,new Throwable("wxPay notify sign error"));
                return WXPayUtil.mapToXml(BeanUtils.describe(new WxPayException("001","签名失败")));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }


    /**
     * 校验微信支付接口响应是否正确
     * @param resData 微信返回数据
     * @return 是否成功
     */
    private boolean validateWxPayResult(Map<String,String> resData){
        try {
            if (resData != null && !resData.isEmpty()){
                String reCode = resData.get("return_code");
                if (StringUtils.isNotEmpty(reCode) && WechatEnum.RETURN_CODE_SUCCESS.getValue().equals(reCode)){
                    return true;
                }
                throw new WxPayException(reCode, resData.get("return_msg"));
            }else {
                throw new WxPayException("FAIL","service response is empty");
            }
        } catch (WxPayException e) {
            logger.error(e.getMessage(),e);
        }
        return false;
    }
}
