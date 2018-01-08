package com.xonro.serviceno.enums;

/**
 * @author Alex
 * @date 2018/1/8
 */
public enum WechatEnums {
    /**微信服务url枚举*/
    URL_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential","获取accessToken"),
    URL_QRCODE_CREATE("https://api.weixin.qq.com/cgi-bin/qrcode/create?","临时二维码请求"),
    URL_OAUTHOR_TOKEN("https://api.weixin.qq.com/sns/oauth2/access_token?","通过code换取网页授权access_token"),
    URL_JSAPI_TICKET("https://api.weixin.qq.com/cgi-bin/ticket/getticket?","获取jsapi_ticket"),

    /**消息类型常量枚举*/
    MSG_TYPE_TEXT("text","文本消息"),
    MSG_TYPE_IMAGE("image","图片消息"),
    MSG_TYPE_VOICE("voice","语音消息"),
    MSG_TYPE_VIDEO("video","视频消息"),
    MSG_TYPE_LINK("link","链接消息"),
    MSG_TYPE_EVENT("event","事件推送"),

    /**事件类型常量枚举*/
    EVENT_SUBSCRIBE("subscribe","订阅"),
    EVENT_UNSUBSCRIBE("unsubscribe","取消订阅"),
    EVENT_SCAN("SCAN","扫码"),
    EVENT_LOCATION("LOCATION","上报地理位置"),
    EVENT_CLICK("CLICK","点击菜单拉取消息时的事件推送"),
    EVENT_VIEW("VIEW","点击菜单跳转链接时的事件推送"),

    ;

    private WechatEnums(String value,String desc){
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
