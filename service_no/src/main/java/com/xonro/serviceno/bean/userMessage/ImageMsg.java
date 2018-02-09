package com.xonro.serviceno.bean.userMessage;

/**
 * 图片消息
 * @author louie
 * @date 2018-2-8
 */
public class ImageMsg {
    public ImageMsg(){}
    /**
     * 图片链接
     */
    private String picUrl;

    /**
     * 图片消息媒体id
     */
    private String mediaId;

    public ImageMsg(String picUrl,String mediaId){
        this.picUrl = picUrl;
        this.mediaId = mediaId;
    }

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
