package com.xonro.serviceno.enums;

/**
 * @author Alex
 * @date 2018/1/8
 */
public enum WechatEnums {
    /**微信服务url枚举*/
    URL_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential","获取accessToken"),
    URL_OAUTHOR_TOKEN("https://api.weixin.qq.com/sns/oauth2/access_token?","通过code换取网页授权access_token"),
    URL_JSAPI_TICKET("https://api.weixin.qq.com/cgi-bin/ticket/getticket?","获取jsapi_ticket"),
    URL_CUSTOMSERVICE_ADD("https://api.weixin.qq.com/customservice/kfaccount/add?","添加客服账号请求"),
    URL_CUSTOMSERVICE_UPDATE("https://api.weixin.qq.com/customservice/kfaccount/update?","修改客服账号请求"),
    URL_CUSTOMSERVICE_DEL("https://api.weixin.qq.com/customservice/kfaccount/del?","删除客服账号请求"),
    URL_CUSTOMSERVICE_UPLOADHEADIMG("https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?","设置客服帐号的头像请求"),
    URL_CUSTOMSERVICE_GETKFLIST("https://api.weixin.qq.com/cgi-bin/customservice/getkflist?","获取所有的客服请求"),
    URL_CUSTOMSERVICE_SEND("https://api.weixin.qq.com/cgi-bin/message/custom/send?","客服接口-发消息请求"),
    URL_CUSTOMSERVICE_TYPING("https://api.weixin.qq.com/cgi-bin/message/custom/typing?","客服输入状态请求"),
    URL_MASSMESSAGE_SENDALL("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?","根据标签进行群发"),
    URL_MASSMESSAGE_SENDBYOPENID("https://api.weixin.qq.com/cgi-bin/message/mass/send?","根据openid进行群发"),
    URL_MASSMESSAGE_DELETE("https://api.weixin.qq.com/cgi-bin/message/mass/delete?","删除群发请求"),
    URL_MASSMESSAGE_PREVIEW("https://api.weixin.qq.com/cgi-bin/message/mass/preview?","预览接口请求"),
    URL_MASSMESSAGE_GET("https://api.weixin.qq.com/cgi-bin/message/mass/get?","查询群发消息发送状态请求"),
    URL_MASSMESSAGE_GETMASSSPEED("https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?","获取群发速度"),
    URL_MASSMESSAGE_SETMASSSPEED("https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?","设置群发速度"),
    URL_MENU_CREATE("https://api.weixin.qq.com/cgi-bin/menu/create?","菜单创建请求"),
    URL_MENU_GET("https://api.weixin.qq.com/cgi-bin/menu/get?","菜单查询请求"),
    URL_MENU_DEL("https://api.weixin.qq.com/cgi-bin/menu/delete?","菜单删除请求"),
    URL_MENU_ADDCONDITIONAL("https://api.weixin.qq.com/cgi-bin/menu/addconditional?","创建个性化菜单请求"),
    URL_MENU_DELCONDITIONAL("https://api.weixin.qq.com/cgi-bin/menu/delconditional?","删除个性化菜单请求"),
    URL_MENU_TRYMATCH("https://api.weixin.qq.com/cgi-bin/menu/trymatch?","测试个性化菜单匹配请求"),
    URL_MENU_CURRENTSELFMENU("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?","获取自定义菜单配置请求"),
    URL_USER_INFO("https://api.weixin.qq.com/cgi-bin/user/info?","通过OpenID来获取用户基本信息"),
    URL_QRCODE_CREATE("https://api.weixin.qq.com/cgi-bin/qrcode/create?","获取带参数的二维码"),
    URL_QRCODE_IMAGE("https://mp.weixin.qq.com/cgi-bin/showqrcode?","用ticket换取二维码图片"),

    /**消息类型常量枚举*/
    MSG_TYPE_TEXT("text","文本消息"),
    MSG_TYPE_IMAGE("image","图片消息"),
    MSG_TYPE_VOICE("voice","语音消息"),
    MSG_TYPE_VIDEO("video","视频消息"),
    MSG_TYPE_MPVIDEO("mpvideo","视频消息(群发)"),
    MSG_TYPE_MUSIC("music","音乐消息"),
    MSG_TYPE_NEWS("news","图文消息"),
    MSG_TYPE_MPNEWS("mpnews","图文消息(点击跳转到图文消息页面)"),
    MSG_TYPE_WXCARD("wxcard","卡券消息"),
    MSG_TYPE_MINIPROGRAMPAGE("miniprogrampage","小程序卡片消息"),
    MSG_TYPE_LINK("link","链接消息"),
    MSG_TYPE_EVENT("event","事件推送"),
    MSG_TYPE_TYPING("Typing","对用户下发”正在输入“状态 "),
    MSG_TYPE_CANCELTYPING("CancelTyping","取消对用户的”正在输入“状态"),

    /**事件类型常量枚举*/
    EVENT_SUBSCRIBE("subscribe","订阅"),
    EVENT_UNSUBSCRIBE("unsubscribe","取消订阅"),
    EVENT_SCAN("SCAN","扫码"),
    EVENT_LOCATION("LOCATION","上报地理位置"),
    EVENT_CLICK("CLICK","点击菜单拉取消息时的事件推送"),
    EVENT_VIEW("VIEW","点击菜单跳转链接时的事件推送"),

    /**菜单类型常量枚举*/
    MENU_CLICK("click", "点击推事件"),
    MENU_VIEW("view", "跳转URL"),
    MENU_SCANPUSH("scancode_push", "扫码推事件"),
    MENU_SCANMSG("scancode_waitmsg", "扫码推事件且弹出消息接收中"),
    MENU_VIEWLIMITED("view_limited", "跳转图文消息"),

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
