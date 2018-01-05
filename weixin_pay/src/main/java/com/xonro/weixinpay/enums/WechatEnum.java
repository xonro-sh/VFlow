package com.xonro.weixinpay.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/1/2
 */
public enum WechatEnum {
    /**
     * 返回状态码成功
     */
    RETURN_CODE_SUCCESS("SUCCESS", "返回成功"),
    BILL_TYPE_ALL("ALL", "返回当日所有订单信息，默认值"),
    BILL_TYPE_SUCCESS("SUCCESS", "返回当日成功支付的订单"),
    BILL_TYPE_REFUND("REFUND", "返回当日退款订单"),
    BILL_TYPE_RECHARGE_REFUND("RECHARGE_REFUND", "返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）"),
    ALGORITHM_MODE_PADDING("AES/ECB/PKCS7Padding", "加解密算法/工作模式/填充方式"),
    ALGORITHM_AES("AES","密钥算法");
    WechatEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    WechatEnum(Map<String, String> keyMap, String desc){
        this.keyMap = keyMap;
        this.desc = desc;
    }

    private String value;
    private String desc;



    private Map<String, String> keyMap;
    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
    public Map<String, String> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, String> keyMap) {
        this.keyMap = keyMap;
    }
}
