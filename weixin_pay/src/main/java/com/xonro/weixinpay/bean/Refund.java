package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 申请退款模型
 * @author Alex
 * @date 2018/1/3
 */
@Component
public class Refund implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 订单金额
     */
    private Integer total_fee;
    /**
     * 退款金额
     */
    private Integer refund_fee;
    /**
     * 货币种类
     */
    private String refund_fee_type="CNY";
    /**
     * 退款原因
     */
    private String refund_desc;
    /**
     * 退款资金来源
     * 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    private String refund_account;

    public Map<String,String> refundByTransactionId(RefundByTransactionId refundByTransactionId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Refund refund = new Refund();
        refund.setTransaction_id(refundByTransactionId.getTransactionId());
        refund.setOut_refund_no(refundByTransactionId.getOutRefundNo());
        refund.setTotal_fee(refundByTransactionId.getTotalFee());
        refund.setRefund_fee(refundByTransactionId.getRefundFee());
        refund.setRefund_fee_type(refundByTransactionId.getRefundFeeType());
        refund.setRefund_desc(refundByTransactionId.getRefundDesc());
        refund.setRefund_account(refundByTransactionId.getRefundAccount());
        return variableHelper.removeEmptyValue(refund);
    }

    public Map<String,String> refundByOutTradeNo(RefundByOutTradeNo refundByOutTradeNo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Refund refund = new Refund();
        refund.setOut_trade_no(refundByOutTradeNo.getOutTradeNo());
        refund.setOut_refund_no(refundByOutTradeNo.getOutRefundNo());
        refund.setTotal_fee(refundByOutTradeNo.getTotalFee());
        refund.setRefund_fee(refundByOutTradeNo.getRefundFee());
        refund.setRefund_fee_type(refundByOutTradeNo.getRefundFeeType());
        refund.setRefund_desc(refundByOutTradeNo.getRefundDesc());
        refund.setRefund_account(refundByOutTradeNo.getRefundAccount());
        return variableHelper.removeEmptyValue(refund);
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }
}
