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
    RETURN_CODE_SUCCESS("SUCCESS", "返回成功");
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
