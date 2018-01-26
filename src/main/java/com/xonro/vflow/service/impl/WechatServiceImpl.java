package com.xonro.vflow.service.impl;

import com.xonro.vflow.bean.BaseResponse;
import com.xonro.vflow.bean.wechat.ServiceNoConf;
import com.xonro.vflow.bean.wechat.WxPayConf;
import com.xonro.vflow.dao.wechatdao.ServiceNoConfRepository;
import com.xonro.vflow.dao.wechatdao.WxPayConfRepository;
import com.xonro.vflow.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信服务接口实现
 * @author Alex
 * @date 2018/1/25
 */
@Service
public class WechatServiceImpl implements WechatService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServiceNoConfRepository serviceNoConfRepository;

    @Autowired
    private WxPayConfRepository wxPayConfRepository;
    /**
     * 更新微信服务号配置
     *
     * @param serviceNoConf 服务号配置实体
     * @return 结果
     */
    @Override
    public BaseResponse updateServiceNoConf(ServiceNoConf serviceNoConf) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            serviceNoConfRepository.save(serviceNoConf);
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 获取微信服务号配置
     *
     * @return 结果
     */
    @Override
    public BaseResponse getServiceNoConf() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            List<ServiceNoConf> serviceNoConfList = serviceNoConfRepository.findAll();
            baseResponse.setData(null);
            if (serviceNoConfList.size()!=0){
                baseResponse.setData(serviceNoConfList.get(0));
            }
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 更新微信支付配置
     *
     * @param wxPayConf 微信支付配置实体
     * @return 结果模型
     */
    @Override
    public BaseResponse updateWxPayConf(WxPayConf wxPayConf) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            wxPayConfRepository.save(wxPayConf);
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 获取微信支付配置
     *
     * @return 结果模型
     */
    @Override
    public BaseResponse getWxPayConf() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            List<WxPayConf> wxPayConfList = wxPayConfRepository.findAll();
            baseResponse.setData(null);
            if (wxPayConfList.size()!=0){
                baseResponse.setData(wxPayConfList.get(0));
            }
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
