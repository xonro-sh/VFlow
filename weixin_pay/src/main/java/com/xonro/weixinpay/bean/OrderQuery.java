package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 查询订单模型
 * @author Alex
 * @date 2018/1/3
 */
@Component
public class OrderQuery implements Serializable{
    @Autowired
    private VariableHelper variableHelper;

    /**
     * 微信订单号，建议优先使用
     */
    private String transaction_id;
    /**
     * 商户订单号，与微信订单号二选一
     */
    private String out_trade_no;

    public Map<String,String> getOrderQueryByTransactionId(String transactionId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setTransaction_id(transactionId);
        return variableHelper.removeEmptyValue(orderQuery);
    }

    public Map<String, String> getOrderQueryByOutTradeNo(String outTradeNo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOut_trade_no(outTradeNo);

        return variableHelper.removeEmptyValue(orderQuery);

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
}
