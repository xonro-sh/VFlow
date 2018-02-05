package com.xonro.serviceno.service.impl;

import com.xonro.serviceno.bean.WechatArticlesMessage;
import com.xonro.serviceno.bean.WechatMediaMessage;
import com.xonro.serviceno.bean.WechatMessage;
import com.xonro.serviceno.bean.user.UserInfo;
import com.xonro.serviceno.dao.UserRepository;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.MessageService;
import com.xonro.serviceno.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    /**
     * 解析微信平台推送的消息
     *
     * @param xmlMessage 微信平台post的消息xml
     * @return 是否成功 返回""或者SUCCESS都为成功
     */
    @Override
    public String parseMessage(String xmlMessage) {
        Map<String,String> params;
        try {
            params = ServiceNoHelper.xmlToMap(xmlMessage);
            String msgType = params.get("MsgType");
            String wechatId = "gh_5438f14fcf6f";
            String openId = params.get("FromUserName");
            //事件推送
            if (msgType.equals(WechatEnums.MSG_TYPE_EVENT.getValue())){
                String event = params.get("Event");
                //订阅消息
                if (event.equals(WechatEnums.MSG_TYPE_SUBSCRIBE.getValue())){
                    userRepository.save(userService.getUserInfo(openId));
                }
                //取消订阅
                else if (event.equals(WechatEnums.MSG_TYPE_UNSUBSCRIBE.getValue())){
                    UserInfo userInfo = userRepository.findByOpenid(openId);
                    if (userInfo != null){
                        userInfo.setSubscribe(userService.getUserInfo(openId).getSubscribe());
                        userRepository.save(userInfo);
                    }

                } else {
                    System.err.println(""+replyMessage(WechatEnums.MSG_TYPE_TEXT.getValue(), openId, wechatId, "我爱中国"));
                    return replyMessage(WechatEnums.MSG_TYPE_TEXT.getValue(), openId, wechatId, "我爱中国");
                }

            }
            //文本消息
            else if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
                System.err.println(""+replyMessage(WechatEnums.MSG_TYPE_NEWS.getValue(), openId, wechatId, "我爱中国"));
                return replyMessage(WechatEnums.MSG_TYPE_NEWS.getValue(), openId, wechatId, "我爱中国");
            }
            //图片消息
            else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE)){

            }
            else {

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
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, ""))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getExtMessageXml(new WechatMediaMessage("2ISU_cKchVgMoCS_kC5a3Z46hcrYrN2cU3VImUIK9LKUjB0PeWa5ZjfjO3D4hAWf"),"image");
            return xml2==null?null:xml1.concat(xml2);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue())){
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "")));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_MUSIC.getValue())){
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, ""))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getExtMessageXml(new WechatMediaMessage("测试音乐消息", "TaylorSwift", "http://url.cn/5gwf6be", "http://url.cn/5gwf6be", "kDYbKqfRkWtL1upUgPWg35poCbXpGdYkhSIyCB5jTpjMsytS8PQaaih8ML8nXgBt"),msgType);
            return xml2==null?null:xml1.concat(xml2);
        } else {
            WechatArticlesMessage wechatArticlesMessage = new WechatArticlesMessage("111", "测试1", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515586204344&di=07bc4720448ac27002f945959bebd443&imgtype=0&src=http%3A%2F%2Fmp3.qiyipic.com%2Fimage%2F20170727%2F96%2Fae%2Fh_1037091770_h_601_400_400.jpg", "www.baidu.com");
            WechatArticlesMessage wechatArticlesMessage1 = new WechatArticlesMessage("222", "测试2", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516180979&di=76ad1dd7ac4d97ed1e7d6c85b7d25e6a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201502%2F10%2F20150210221954_f4zKK.thumb.700_0.jpeg", "www.baidu.com");
            List<WechatArticlesMessage> wechatArticlesMessages = new ArrayList<>();
            wechatArticlesMessages.add(wechatArticlesMessage);
            wechatArticlesMessages.add(wechatArticlesMessage1);
            //多条图文消息
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new WechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "2"))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getArticlesXml(wechatArticlesMessages);

            return xml1.concat(xml2);
        }
    }
}
