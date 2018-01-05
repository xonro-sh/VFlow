package com.xonro.weixinpay.bean;

import com.xonro.weixinpay.helper.VariableHelper;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 交易保障模型
 * @author Alex
 * @date 2018/1/5
 */
public class Report implements Serializable{
    private VariableHelper variableHelper = new VariableHelper();
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 接口URL
     */
    private String interface_url;
    /**
     * 接口耗时
     */
    private Integer execute_time;
    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 业务结果
     */
    private String result_code;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 访问接口IP
     */
    private String user_ip;
    /**
     * 商户上报时间
     */
    private String time;

    public Map<String,String> getReport(ReportRequestParam reportRequestParam) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Report report = new Report();
        return variableHelper.removeEmptyValue(report);

    }
    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getInterface_url() {
        return interface_url;
    }

    public void setInterface_url(String interface_url) {
        this.interface_url = interface_url;
    }

    public Integer getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(Integer execute_time) {
        this.execute_time = execute_time;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
