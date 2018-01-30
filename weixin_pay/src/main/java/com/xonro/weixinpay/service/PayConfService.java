package com.xonro.weixinpay.service;

import com.xonro.weixinpay.bean.WxPayConf;

/**
 * @author Alex
 * @date 2018/1/30
 */
public interface PayConfService {
    /**
     * 获取缓存的公众支付配置
     * @return token
     */
    WxPayConf getConfFromCache();
}
