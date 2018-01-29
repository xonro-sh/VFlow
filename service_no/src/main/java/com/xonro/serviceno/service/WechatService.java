package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.BaseResponse;
import com.xonro.serviceno.bean.QrCode;
import com.xonro.serviceno.bean.config.ServiceNoConf;

/**
 * 公众平台提供的相关服务
 * @author louie
 * @date 2018-1-12
 */
public interface WechatService {

    /**
     * 创建含有参数的二维码
     * @param expireSeconds 二维码有效时间，以秒为单位，最大不超过2592000（即30天）,为0或小于0时创建永久二维码。
     * @param sceneValue 生成的二维码所含的参数信息
     * @return 微信返回的二维码信息
     */
    public QrCode createQrCode(Long expireSeconds,Object sceneValue);

    /**
     * 获取公众平台生成的二维码图片
     * @param ticket 获取图片的凭证，通过创建二维码接口获取
     * @return 二维码图片的字节流
     */
    public byte[] getQrCodeImage(String ticket);

    /**
     * 更新微信服务号配置
     * @param serviceNoConf 服务号配置实体
     * @return 结果
     */
    BaseResponse updateServiceNoConf(ServiceNoConf serviceNoConf);

    /**
     * 获取微信服务号配置
     * @return 结果
     */
    BaseResponse getServiceNoConf();
}
