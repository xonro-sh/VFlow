package com.xonro.weixinpay.bean;

/**
 * 交易保障请求参数模型
 * @author Alex
 * @date 2018/1/5
 */
public class ReportRequestParam {
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 接口URL
     */
    private String interfaceUrl;
    /**
     * 接口耗时
     */
    private Integer executeTime;
    /**
     * 返回状态码
     */
    private String returnCode;
    /**
     * 返回信息
     */
    private String returnMsg;
    /**
     * 业务结果
     */
    private String resultCode;
    /**
     * 错误代码
     */
    private String errCode;
    /**
     * 错误代码描述
     */
    private String errCodeDes;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 访问接口IP
     */
    private String userIp;
    /**
     * 商户上报时间
     */
    private String time;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public Integer getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class Builder {
        /**
         * 设备号
         */
        private String deviceInfo;
        /**
         * 接口URL
         */
        private String interfaceUrl;
        /**
         * 接口耗时
         */
        private Integer executeTime;
        /**
         * 返回状态码
         */
        private String returnCode;
        /**
         * 返回信息
         */
        private String returnMsg;
        /**
         * 业务结果
         */
        private String resultCode;
        /**
         * 错误代码
         */
        private String errCode;
        /**
         * 错误代码描述
         */
        private String errCodeDes;
        /**
         * 商户订单号
         */
        private String outTradeNo;
        /**
         * 访问接口IP
         */
        private String userIp;
        /**
         * 商户上报时间
         */
        private String time;

        public Builder(String interfaceUrl, Integer executeTime, String returnCode, String resultCode, String userIp){
            this.interfaceUrl = interfaceUrl;
            this.executeTime = executeTime;
            this.returnCode = returnCode;
            this.resultCode = resultCode;
            this.userIp = userIp;
        }

        public Builder deviceInfo(String val){
            deviceInfo = val;
            return this;
        }

        public Builder returnMsg(String val){
            returnMsg = val;
            return this;
        }

        public Builder errCode(String val){
            errCode = val;
            return this;
        }

        public Builder errCodeDes(String val){
            errCodeDes = val;
            return this;
        }

        public Builder outTradeNo(String val){
            outTradeNo = val;
            return this;
        }

        public Builder time(String val){
            time = val;
            return this;
        }


        public ReportRequestParam build() {
            return new ReportRequestParam(this);
        }
    }

    private ReportRequestParam(Builder builder) {
        deviceInfo = builder.deviceInfo;
        interfaceUrl = builder.interfaceUrl;
        executeTime = builder.executeTime;
        returnCode = builder.returnCode;
        returnMsg = builder.returnMsg;
        resultCode = builder.resultCode;
        errCode = builder.errCode;
        errCodeDes = builder.errCodeDes;
        outTradeNo = builder.outTradeNo;
        userIp = builder.userIp;
        time = builder.time;
    }

}
