package com.xonro.serviceno.bean.config;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公众号配置bean
 * @author Alex
 * @date 2018/1/25
 */
@Entity
@Table(name="xr_wechat_servicenoconf")
public class ServiceNoConf implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     * 开发者ID
     */
    private String appId;
    /**
     * 开发者密码
     */
    private String appSecret;
    /**
     * 令牌
     */
    private String token;
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Column(name = "app_secret")
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
