package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.WechatArticlesMessage;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/1/9
 */
@Service
public class MessageServiceImpl implements MessageService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 解析微信平台推送的消息
     *
     * @param xmlMessage 微信平台post的消息xml
     * @return 是否成功 返回""或者SUCCESS都为成功
     */
    @Override
    public String parseMessage(String xmlMessage) {
        logger.error("---------已接收微信事件--------");
        Map<String,String> params;
        try {
            params = ServiceNoHelper.xmlToMap(xmlMessage);
            String msgType = params.get("MsgType");
            String wechatId = "gh_5438f14fcf6f";
            String openId = "ov_650REgDNUfqiGwL8JbF5Ntsk8";
            //事件推送
            if (msgType.equals(WechatEnums.MSG_TYPE_EVENT.getValue())){

            }
            //文本消息
            else if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
                System.err.println(""+replyMessage(msgType, openId, wechatId, "我爱中国"));
                return replyMessage(msgType, openId, wechatId, "我爱中国");
            }
            //图片消息
            else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE)){

            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }

    public String replyMessage(String msgType, String openId, String wechatId, String content){
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())) {
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, content)));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue()) || msgType.equals(WechatEnums.MSG_TYPE_VOICE.getValue())) {
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "")));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue())){
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "", "", "")));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue())){
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "", "", "", "", "")));
        } else {
            WechatMessage wechatMessage = new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "");
            //多条图文消息
            List<WechatArticlesMessage> wechatArticlesMessages = new ArrayList<>();
            return ServiceNoHelper.beanToxml(wechatMessage).concat( ServiceNoHelper.getArticlesXml(wechatArticlesMessages));
        }
    }
}
