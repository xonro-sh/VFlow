package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 下载对账单模型
 * @author Alex
 * @date 2018/1/4
 */
@Component
public class DownloadBill implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();
    /**
     * 对账单日期
     */
    private String  bill_date;
    /**
     * 账单类型
     * ALL，返回当日所有订单信息，默认值
     * SUCCESS，返回当日成功支付的订单
     * REFUND，返回当日退款订单
     * RECHARGE_REFUND，返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）
     */
    private String  bill_type;
    /**
     * 压缩账单
     */
    private String  tar_type;

    public Map<String, String> getDownloadBillByDateAndType(String billDate, String billType) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DownloadBill downloadBill = new DownloadBill();
        downloadBill.setBill_date(billDate);
        downloadBill.setBill_type(billType);

        return variableHelper.removeEmptyValue(downloadBill);
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getTar_type() {
        return tar_type;
    }

    public void setTar_type(String tar_type) {
        this.tar_type = tar_type;
    }
}
