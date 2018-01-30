package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.config.ServiceNoConf;
import com.xonro.serviceno.dao.ServiceNoConfRepository;
import com.xonro.serviceno.service.ServiceNoConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/30
 */
@Service
public class ServiceNoConfServiceImpl implements ServiceNoConfService{
    private ServiceNoConf conf;

    @Autowired
    private ServiceNoConfRepository serviceNoConfRepository;

    /**
     * 获取缓存的公众号配置
     *
     * @return token
     */
    @Override
    public ServiceNoConf getConfFromCache(){
        return getConf();
    }

    private ServiceNoConf getConf(){
        List<ServiceNoConf> serviceNoConf = serviceNoConfRepository.findAll();
        return serviceNoConf.size()==0?null:serviceNoConf.get(0);
    }
}
