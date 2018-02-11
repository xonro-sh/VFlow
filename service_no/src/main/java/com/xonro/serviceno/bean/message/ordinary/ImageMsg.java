package com.xonro.serviceno.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片消息
 * @author louie
 * @date 2018-2-8
 */
public class ImageMsg extends OrdinaryMessage {
    /**
     * 图片链接
     */
    @JSONField(name = "PicUrl")
    private String picUrl;

    /**
     * 图片消息媒体id
     */
    @JSONField(name = "MediaId")
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImageMsg{" +
                "picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
