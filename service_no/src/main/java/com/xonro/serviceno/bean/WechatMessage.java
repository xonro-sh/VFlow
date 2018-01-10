package com.xonro.serviceno.bean;

import com.xonro.serviceno.enums.WechatEnums;

import java.util.List;

/**
 * 被动回复消息bean
 * @author Alex
 * @date 2018/1/9
 */
public class WechatMessage {
    /**
     * 接收方帐号（收到的OpenID）
     */
    private String toUserName;
    /**
     * 开发者微信号
     */
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long createTime;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String content;
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    /**
     * 图文消息个数，限制为8条以内
     */
    private String articleCount;

    public WechatMessage() {
    }
    /**
     * 文本消息 图片消息 语音消息
     * @param toUserName 接收方帐号（收到的OpenID）
     * @param fromUserName	开发者微信号
     * @param createTime 	消息创建时间 （整型）
     * @param msgType 	text
     * @param content 内容
     */
    public WechatMessage(String toUserName, String fromUserName, Long createTime, String msgType, String content) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
        if (this.msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())) {
            this.content = content;
        }
        if (this.msgType.equals(WechatEnums.MSG_TYPE_NEWS.getValue())) {
            this.articleCount = content;
        }
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }
}
