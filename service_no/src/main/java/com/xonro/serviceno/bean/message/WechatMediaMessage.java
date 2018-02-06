package com.xonro.serviceno.bean.message;

import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @date 2018/1/10
 */
public class WechatMediaMessage {
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
    private String musicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String hQMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String thumbMediaId;

    public WechatMediaMessage(String mediaId) {
        this.mediaId = mediaId;
    }

    public WechatMediaMessage(String mediaId, String title, String description) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    public WechatMediaMessage(String title, String description, String musicUrl, String hQMusicUrl, String thumbMediaId) {
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicURL(String musicUrl) {
        this.musicUrl = musicUrl;
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
}
