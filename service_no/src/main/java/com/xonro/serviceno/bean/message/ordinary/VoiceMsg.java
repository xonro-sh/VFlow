package com.xonro.serviceno.bean.message.ordinary;

/**
 * 语音消息
 * @author louie
 * @date 2018-2-8
 */
public class VoiceMsg extends OrdinaryMessage{
    /**
     * 语音消息媒体id
     */
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private String format;

    /**
     * 语音识别结果，UTF8编码
     * 需开启语音识别可获得
     */
    private String recognition;

    public VoiceMsg(String mediaId,String format,String recognition){
        this.mediaId = mediaId;
        this.format = format;
        this.recognition = recognition;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getFormat() {
        return format;
    }

    public String getRecognition() {
        return recognition;
    }
}
