package com.xonro.serviceno.bean;

/**
 * 微信公众平台生成含有参数的二维码
 * @author louie
 * @date 2018-1-12
 */
public class QrCode {
    public QrCode(){}

    public QrCode(long expire_seconds,long createTime,String url,byte[] qrCode){
        this.createTime = createTime;
        this.expire_seconds = expire_seconds;
        this.url = url;
        this.qrCode = qrCode;
    }
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private long expire_seconds;

    /**
     * 二维码创建时的时间戳，单位为秒
     */
    private long createTime;

    /**
     * 二维码图片解析后的地址
     */
    private String url;

    /**
     * 二维码图片字节码
     */
    private byte[] qrCode;

    public long getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }
}
