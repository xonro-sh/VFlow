package com.xonro.weixinpay.service.impl;

import com.xonro.weixinpay.bean.WxPayConf;
import com.xonro.weixinpay.dao.WxPayConfRepository;
import com.xonro.weixinpay.service.PayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/30
 */
@Service
public class PayConfServiceImpl implements PayConfService{
    @Autowired
    private WxPayConfRepository wxPayConfRepository;
    /**
     * 获取缓存的公众支付配置
     *
     * @return token
     */
    @Override
    public WxPayConf getConfFromCache(){
        return getConf();
    }

    private WxPayConf getConf(){
        List<WxPayConf> wxPayConfs = wxPayConfRepository.findAll();
        return wxPayConfs.size() == 0?null:wxPayConfs.get(0);
    }
}
