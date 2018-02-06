package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.serviceno.bean.BaseResponse;
import com.xonro.serviceno.bean.CreateQrCode;
import com.xonro.serviceno.bean.QrCode;
import com.xonro.serviceno.bean.config.ServiceNoConf;
import com.xonro.serviceno.bean.message.Message;
import com.xonro.serviceno.dao.MessageRepository;
import com.xonro.serviceno.dao.ServiceNoConfRepository;
import com.xonro.serviceno.exception.WechatException;
import com.xonro.serviceno.helper.UrlBuilder;
import com.xonro.serviceno.service.WechatService;
import com.xonro.serviceno.web.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author louie
 * @date 2018-1-12
 */
@Service
public class WechatServiceImpl implements WechatService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;

    @Autowired
    private ServiceNoConfRepository serviceNoConfRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public QrCode createQrCode(Long expireSeconds, Object sceneValue) {
        try {
            // 1、获取含有参数二维码
            CreateQrCode createQrCode = new CreateQrCode(expireSeconds,sceneValue);
            JSONObject jsonObject = new RequestExecutor(urlBuilder.buildCreateQrCodeUrl())
                    .execute(JSON.toJSONString(createQrCode))
                    .getResponseAsObject(JSONObject.class);
            String ticket = jsonObject.getString("ticket");
            long reExpireSeconds = jsonObject.getLong("expire_seconds");
            String url = jsonObject.getString("url");

            // 2、通过ticket换取二维码图片
            return new QrCode(
                    reExpireSeconds,System.currentTimeMillis()/1000,url,
                    new RequestExecutor(urlBuilder.buildQrCodeImageUrl(ticket)).downloadFile()
            );
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (WechatException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public byte[] getQrCodeImage(String ticket) {


        return new byte[0];
    }



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
     * 获取缓存的公众号配置
     *
     * @return
     */
    @Override
    @Cacheable(value = "getConfFromCache")
    public ServiceNoConf getConfFromCache(){
        return getConf();
    }

    /**
     * 获取消息配置
     * @param type 消息类型（大类）
     * @return 结果
     */
    @Override
    public BaseResponse getMessageConf(String type) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            List<Message> messages = messageRepository.findByType(type);
            baseResponse.setData(null);
            if (messages.size()!=0){
                baseResponse.setData(messages.get(0));
            }
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 更新消息设置
     * @param message 信息对象
     * @return 结果
     */
    @Override
    public BaseResponse updateMessageConf(Message message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            messageRepository.save(message);
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    private ServiceNoConf getConf(){
        List<ServiceNoConf> serviceNoConf = serviceNoConfRepository.findAll();
        return serviceNoConf.size()==0?null:serviceNoConf.get(0);
    }


}
