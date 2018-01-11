package com.xonro.serviceno.bean.custom;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.serviceno.enums.WechatEnums;

import java.util.List;

/**
 * 客服消息主体
 * @author Alex
 * @date 2018/1/11
 */
public class CustomMessageMain {
    private String toUser;
    private String msgType;
    private CustomMessage text;
    private CustomMessage image;
    private CustomMessage voice;
    private CustomMessage video;
    private CustomMessage music;
    private CustomMessage news;
    private CustomMessage mpnews;
    private CustomMessage wxcard;
    private CustomMessage miniprogrampage;
    private CustomMessage customservice;
    public CustomMessageMain(String toUser, String msgType, String text, boolean isKf, String kfAccount) {

        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())) {
            this.text = new CustomMessage(text, msgType);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue())) {
            this.image = new CustomMessage(text, msgType);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VOICE.getValue())) {
            this.voice = new CustomMessage(text, msgType);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_MPNEWS.getValue())) {
            this.mpnews = new CustomMessage(text, msgType);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())) {
            this.wxcard = new CustomMessage(text, msgType);
        }
        this.toUser = toUser;
        this.msgType = msgType;
        if (isKf) {
            this.customservice = new CustomMessage(true, kfAccount);
        }
    }

    public CustomMessageMain(String toUser, String msgType, CustomMessage customMessage, boolean isKf, String kfAccount) {
        if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue())){
            this.video = customMessage;
        } else if (msgType.equals(WechatEnums.MSG_TYPE_MUSIC.getValue())){
            this.music = customMessage;
        }
        this.toUser = toUser;
        this.msgType = msgType;
        if (isKf) {
            this.customservice = new CustomMessage(true, kfAccount);
        }
    }

    public CustomMessageMain(String toUser, String msgType, CustomMessage customMessage, boolean isKf, String kfAccount,String title){
        if (msgType.equals(WechatEnums.MSG_TYPE_MINIPROGRAMPAGE.getValue())){
            this.miniprogrampage = customMessage;
        }
        this.toUser = toUser;
        this.msgType = msgType;
        if (isKf) {
            this.customservice = new CustomMessage(true, kfAccount);
        }

    }
    public CustomMessageMain(String toUser, String msgType, List<CustomArticlesMessage> customArticlesMessages, boolean isKf, String kfAccount) {
        if (msgType.equals(WechatEnums.MSG_TYPE_NEWS.getValue())){
            this.news = new CustomMessage(customArticlesMessages);
        }
        this.toUser = toUser;
        this.msgType = msgType;
        if (isKf) {
            this.customservice = new CustomMessage(true, kfAccount);
        }

    }
    public CustomMessage getCustomservice() {
        return customservice;
    }

    public void setCustomservice(CustomMessage customservice) {
        this.customservice = customservice;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public CustomMessage getText() {
        return text;
    }

    public void setText(CustomMessage text) {
        this.text = text;
    }

    public CustomMessage getImage() {
        return image;
    }

    public void setImage(CustomMessage image) {
        this.image = image;
    }

    public CustomMessage getVoice() {
        return voice;
    }

    public void setVoice(CustomMessage voice) {
        this.voice = voice;
    }

    public CustomMessage getVideo() {
        return video;
    }

    public void setVideo(CustomMessage video) {
        this.video = video;
    }

    public CustomMessage getMusic() {
        return music;
    }

    public void setMusic(CustomMessage music) {
        this.music = music;
    }

    public CustomMessage getNews() {
        return news;
    }

    public void setNews(CustomMessage news) {
        this.news = news;
    }

    public CustomMessage getMpnews() {
        return mpnews;
    }

    public void setMpnews(CustomMessage mpnews) {
        this.mpnews = mpnews;
    }

    public CustomMessage getWxcard() {
        return wxcard;
    }

    public void setWxcard(CustomMessage wxcard) {
        this.wxcard = wxcard;
    }

    public CustomMessage getMiniprogrampage() {
        return miniprogrampage;
    }

    public void setMiniprogrampage(CustomMessage miniprogrampage) {
        this.miniprogrampage = miniprogrampage;
    }
}
