package com.xonro.serviceno.bean.custom;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.serviceno.enums.WechatEnums;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/11
 */
public class CustomMessage {
    private String content;
    private String mediaId;
    private String thumbMediaId;
    private String title;
    private String description;
    private String musicUrl;
    private String hqMusicUrl;
    private String cardId;
    private String appId;
    private String pagePath;
    private String kfAccount;
    private List<CustomArticlesMessage> articles;

    public CustomMessage() {
    }
    public CustomMessage(boolean isKf, String kfAccount){
        if (isKf) {
            this.kfAccount = kfAccount;
        }
    }
    public CustomMessage(String content, String msgType) {
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            this.content = content;
        } else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue())){
            this.mediaId = content;
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VOICE.getValue())) {
            this.mediaId = content;
        } else if (msgType.equals(WechatEnums.MSG_TYPE_MPNEWS.getValue())) {
            this.mediaId = content;
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())) {
            this.cardId = content;
        }

    }

    public CustomMessage(String thumbMediaId, String appId, String pagePath) {
        this.thumbMediaId = thumbMediaId;
        this.appId = appId;
        this.pagePath = pagePath;
    }

    public CustomMessage(String mediaId, String thumbMediaId, String title, String description) {
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
        this.title = title;
        this.description = description;
    }

    public CustomMessage(String thumbMediaId, String title, String description, String musicUrl, String hqMusicUrl) {
        this.thumbMediaId = thumbMediaId;
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hqMusicUrl = hqMusicUrl;
    }



    public CustomMessage(List<CustomArticlesMessage> customArticlesMessages) {
        this.articles = customArticlesMessages;
    }

    @JSONField(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JSONField(name = "media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @JSONField(name = "thumb_media_id")
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JSONField(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JSONField(name = "musicurl")
    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @JSONField(name = "hqmusicurl")
    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    @JSONField(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @JSONField(name = "appid")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JSONField(name = "pagepath")
    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    @JSONField(name = "kf_account")
    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    @JSONField(name = "articles")
    public List<CustomArticlesMessage> getArticles() {
        return articles;
    }

    public void setArticles(List<CustomArticlesMessage> articles) {
        this.articles = articles;
    }
}
