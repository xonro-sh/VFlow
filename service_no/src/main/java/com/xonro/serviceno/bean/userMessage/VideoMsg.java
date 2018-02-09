package com.xonro.serviceno.bean.userMessage;

/**
 * 视频消息/小视频消息
 * @author louie
 * @date 2018-2-8
 */
public class VideoMsg {
    /**
     * 视频消息媒体id
     */
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id
     */
    private String thumbMediaId;

    public VideoMsg(String mediaId,String thumbMediaId){
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }
}
