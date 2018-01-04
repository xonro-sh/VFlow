package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 查询退款模型
 * @author Alex
 * @date 2018/1/4
 */
@Component
public class RefundQuery implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();
    /**
     * 微信订单号
     * 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
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
     * 微信退款单号
     */
    private String refund_id;
    /**
     * 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     */
    private String offset;

    public Map<String,String> getRefundQueryByTransactionId(String transactionId, String offset) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        RefundQuery refundQuery = new RefundQuery();
        refundQuery.setTransaction_id(transactionId);
        refundQuery.setOffset(offset);

        return variableHelper.removeEmptyValue(refundQuery);
    }

    public Map<String,String> getRefundQueryByOutTradeNo(String outTradeNo, String offset) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        RefundQuery refundQuery = new RefundQuery();
        refundQuery.setOut_trade_no(outTradeNo);
        refundQuery.setOffset(offset);

        return variableHelper.removeEmptyValue(refundQuery);
    }

    public Map<String,String> getRefundQueryByOutRefundNo(String outRefundNo, String offset) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        RefundQuery refundQuery = new RefundQuery();
        refundQuery.setOut_refund_no(outRefundNo);
        refundQuery.setOffset(offset);

        return variableHelper.removeEmptyValue(refundQuery);
    }

    public Map<String,String> getRefundQueryByRefundId(String refundId, String offset) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        RefundQuery refundQuery = new RefundQuery();
        refundQuery.setRefund_id(refundId);
        refundQuery.setOffset(offset);

        return variableHelper.removeEmptyValue(refundQuery);
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

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
