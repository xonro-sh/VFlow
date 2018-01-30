package com.xonro.serviceno.service;

import com.xonro.serviceno.bean.config.ServiceNoConf;

/**
 * @author Alex
 * @date 2018/1/30
 */
public interface ServiceNoConfService {
    /**
     * 获取缓存的公众号配置
     * @return token
     */
    ServiceNoConf getConfFromCache();
}
