package com.xonro.serviceno.bean.custom;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/11
 */
public class CustomArticlesMessage {
    private String title;
    private String description;
    private String url;
    private String picurl;

    public CustomArticlesMessage(String title, String description, String url, String picurl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
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
    @JSONField(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @JSONField(name = "picurl")
    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
