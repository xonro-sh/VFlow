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
    private String mediaId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 音乐链接
     */
    private String musicURL;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String hQMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String thumbMediaId;
    /**
     * 图文消息个数，限制为8条以内
     */
    private String ArticleCount;

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
        } else if (this.msgType.equals(WechatEnums.MSG_TYPE_NEWS.getValue())) {
            this.ArticleCount = content;
        } else {
            this.mediaId = content;
        }
    }

    /**
     * 视频消息
     * @param toUserName 接收方帐号（收到的OpenID）
     * @param fromUserName	开发者微信号
     * @param createTime 	消息创建时间 （整型）
     * @param msgType 	text
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
     * @param title 视频消息的标题
     * @param description 视频消息的描述
     */
    public WechatMessage(String toUserName, String fromUserName, Long createTime, String msgType, String mediaId, String title, String description) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    /**
     * 音乐消息
     * @param toUserName 接收方帐号（收到的OpenID）
     * @param fromUserName	开发者微信号
     * @param createTime 	消息创建时间 （整型）
     * @param msgType 	text
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicURL 音乐链接
     * @param hQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    public WechatMessage(String toUserName, String fromUserName, Long createTime, String msgType, String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
        this.title = title;
        this.description = description;
        this.musicURL = musicURL;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }
}
