package com.xonro.weixinpay.bean;

/**
 * 交易保障结果模型
 * @author Alex
 * @date 2018/1/5
 */
public class ReportResult {
    private String return_code;
    private String return_msg;
    private String result_code;

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
}
