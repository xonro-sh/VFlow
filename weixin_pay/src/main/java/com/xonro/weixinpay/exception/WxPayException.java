package com.xonro.weixinpay.exception;

/**
 * 微信支付异常类
 * @author louie
 * @date 2018-1-3
 */
public class WxPayException extends Exception {
    private String errorCode;
    private String errorMsg;

    public WxPayException(){
        super();
    }

    public WxPayException(String errorCode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }
}
