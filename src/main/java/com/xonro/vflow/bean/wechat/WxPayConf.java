package com.xonro.vflow.bean.wechat;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 微信支付配置bean
 * @author Alex
 * @date 2018/1/25
 */
@Entity
@Table(name="xr_wechat_wxpayconf")
public class WxPayConf {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     * 微信支付商户号
     */
    private String mchId;
    /**
     * API密钥
     */
    private String apiKey;
    /**
     * 微信通知地址
     */
    private String notifyUrl;
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "mch_id")
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    @Column(name = "api_key")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Column(name = "notify_url")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
