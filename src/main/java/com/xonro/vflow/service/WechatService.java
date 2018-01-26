package com.xonro.vflow.service;

import com.xonro.vflow.bean.BaseResponse;
import com.xonro.vflow.bean.wechat.ServiceNoConf;
import com.xonro.vflow.bean.wechat.WxPayConf;

/**
 * 微信服务接口
 * @author Alex
 * @date 2018/1/25
 */
public interface WechatService {
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

    /**
     * 更新微信支付配置
     * @param wxPayConf 微信支付配置实体
     * @return 结果模型
     */
    BaseResponse updateWxPayConf(WxPayConf wxPayConf);

    /**
     * 获取微信支付配置
     * @return 结果模型
     */
    BaseResponse getWxPayConf();
}
