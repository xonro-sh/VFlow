package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 关闭订单模型
 * @author Alex
 * @date 2018/1/3
 */
@Component
public class CloseOrder implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();
    /**
     * 商户订单
     */
    private String  out_trade_no;

    public Map<String,String> getCloseOrderByOutTradeNo(String outTradeNo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CloseOrder closeOrder = new CloseOrder();
        closeOrder.setOut_trade_no(outTradeNo);
        return variableHelper.removeEmptyValue(closeOrder);
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
