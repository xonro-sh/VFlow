package com.xonro.weixinpay.enums;

/**
 * @author Alex
 * @date 2018/1/2
 */
public enum WechatEnum {
    /**
     * 返回状态码成功
     */
    RETURN_CODE_SUCCESS("SUCCESS", "返回成功");
    WechatEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
