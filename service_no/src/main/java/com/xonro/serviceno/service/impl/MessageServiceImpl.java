package com.xonro.serviceno.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.serviceno.bean.message.Message;
import com.xonro.serviceno.bean.message.event.EventMessage;
import com.xonro.serviceno.bean.message.event.LocationEvent;
import com.xonro.serviceno.bean.message.ordinary.*;
import com.xonro.serviceno.bean.message.relpy.WechatArticlesMessage;
import com.xonro.serviceno.bean.message.relpy.WechatMediaMessage;
import com.xonro.serviceno.bean.message.relpy.ReplyWechatMessage;
import com.xonro.serviceno.bean.user.UserInfo;
import com.xonro.serviceno.dao.MessageRepository;
import com.xonro.serviceno.dao.UserRepository;
import com.xonro.serviceno.enums.WechatEnums;
import com.xonro.serviceno.helper.MessageParser;
import com.xonro.serviceno.helper.ServiceNoHelper;
import com.xonro.serviceno.service.MessageService;
import com.xonro.serviceno.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private MessageRepository messageRepository;
    /**
     * 解析微信平台推送的消息
     * @param xmlMessage 微信平台post的消息xml
     * @return 是否成功 返回""或者SUCCESS都为成功
     */
    @Override
    public String parseMessage(String xmlMessage) {
        try {
            OrdinaryMessage ordinaryMessage = new MessageParser(xmlMessage).parse();
            String msgType = ordinaryMessage.getMsgType();

            switch (msgType){
                //文本消息
                case "text":{
                    return accessTextMessage(ordinaryMessage);
                }

                //图片消息
                case "image":{
                    return accessImageMessage((ImageMsg) ordinaryMessage);
                }

                //语音消息
                case "voice":{
                    return accessVoiceMessage((VoiceMsg) ordinaryMessage);
                }

                //视频消息
                case "video":{
                    return accessVideoMessage((VideoMsg) ordinaryMessage);
                }

                //地理位置消息
                case "location":{
                    return accessLocationMessage((LocationMsg) ordinaryMessage);
                }

                //链接消息
                case "link":{
                    return accessLinkMessage((LinkMsg) ordinaryMessage);
                }

                //事件推送
                case "event":{
                    return accessEventMessage((EventMessage) ordinaryMessage);
                }
                default:return "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }

    /**
     * 处理文本消息，后续将扩展根据一定规则响应对应信息
     * 消息匹配规则将在后台系统实现
     * @param ordinaryMessage
     * @return
     */
    private String accessTextMessage(OrdinaryMessage ordinaryMessage){
        // TODO: 2018-2-11 接收文本消息并做处理
        return "";
    }

    /**
     * 处理图片消息
     * @param imageMsg
     * @return
     */
    private String accessImageMessage(ImageMsg imageMsg){
        // TODO: 2018-2-11 接收图片消息并做处理
        return "";
    }

    /**
     * 处理语音消息
     * @param voiceMsg
     * @return
     */
    private String accessVoiceMessage(VoiceMsg voiceMsg){
        // TODO: 2018-2-11 接收语音消息并处理

        return "";
    }

    /**
     * 处理视频消息
     * @param videoMsg
     * @return
     */
    private String accessVideoMessage(VideoMsg videoMsg){
        // TODO: 2018-2-11 接收视频消息并做响应处理

        return "";
    }

    /**
     * 处理位置消息
     * @param locationMsg
     * @return
     */
    private String accessLocationMessage(LocationMsg locationMsg){
        // TODO: 2018-2-11 接收位置消息并做处理

        return "";
    }

    /**
     * 处理链接消息
     * @param linkMsg
     * @return
     */
    private String accessLinkMessage(LinkMsg linkMsg){
        // TODO: 2018-2-11 接收链接消息并做处理
        
        return "";
    }

    /**
     * 处理事件消息
     * @param eventMessage
     * @return
     */
    private String accessEventMessage(EventMessage eventMessage){
        String eventType = eventMessage.getEvent();
        String userOpenId = eventMessage.getFromUserName();

        //用户订阅事件：1、保存用户信息；2、根据配置响应关注后信息
        if (eventType.equals(WechatEnums.EVENT_SUBSCRIBE.getValue())){
            userRepository.save(userService.getUserInfo(userOpenId));

            //获取系统配置消息
            List<Message> messages = messageRepository.findByType(WechatEnums.MSG_TYPE_SECOND.getValue());
            if (messages.size()!=0){
                //如果有效
                if ( messages.get(0).isActive()){
                    String wechatId = "gh_5438f14fcf6f";
                    return replyMessage(WechatEnums.MSG_TYPE_TEXT.getValue(), userOpenId, wechatId,  messages.get(0).getContent());
                }
            }
        }

        //取消订阅，更新用户状态
        if (eventType.equals(WechatEnums.EVENT_UNSUBSCRIBE.getValue())){
            UserInfo userInfo = userRepository.findByOpenid(userOpenId);
            if (userInfo != null){
                userInfo.setSubscribe(userService.getUserInfo(userOpenId).getSubscribe());
                userRepository.save(userInfo);
            }
        }

        //上报地理位置事件
        if (eventType.equals(WechatEnums.EVENT_LOCATION.getValue())){
            LocationEvent locationEvent = (LocationEvent) eventMessage;

            // TODO: 2018-2-11 处理地理位置信息
        }

        // TODO: 2018-2-11 其他类型事件信息均可从eventMessage中获取

        return "";
    }



    public String replyMessage(String msgType, String openId, String wechatId, String content){
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())) {
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new ReplyWechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, content)));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue()) || msgType.equals(WechatEnums.MSG_TYPE_VOICE.getValue())) {
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new ReplyWechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, ""))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getExtMessageXml(new WechatMediaMessage("2ISU_cKchVgMoCS_kC5a3Z46hcrYrN2cU3VImUIK9LKUjB0PeWa5ZjfjO3D4hAWf"),"image");
            return xml2==null?null:xml1.concat(xml2);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue())){
            return ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new ReplyWechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "")));
        } else if (msgType.equals(WechatEnums.MSG_TYPE_MUSIC.getValue())){
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new ReplyWechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, ""))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getExtMessageXml(new WechatMediaMessage("测试音乐消息", "TaylorSwift", "http://url.cn/5gwf6be", "http://url.cn/5gwf6be", "kDYbKqfRkWtL1upUgPWg35poCbXpGdYkhSIyCB5jTpjMsytS8PQaaih8ML8nXgBt"),msgType);
            return xml2==null?null:xml1.concat(xml2);
        } else {
            WechatArticlesMessage wechatArticlesMessage = new WechatArticlesMessage("111", "测试1", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515586204344&di=07bc4720448ac27002f945959bebd443&imgtype=0&src=http%3A%2F%2Fmp3.qiyipic.com%2Fimage%2F20170727%2F96%2Fae%2Fh_1037091770_h_601_400_400.jpg", "www.baidu.com");
            WechatArticlesMessage wechatArticlesMessage1 = new WechatArticlesMessage("222", "测试2", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516180979&di=76ad1dd7ac4d97ed1e7d6c85b7d25e6a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201502%2F10%2F20150210221954_f4zKK.thumb.700_0.jpeg", "www.baidu.com");
            List<WechatArticlesMessage> wechatArticlesMessages = new ArrayList<>();
            wechatArticlesMessages.add(wechatArticlesMessage);
            wechatArticlesMessages.add(wechatArticlesMessage1);
            //多条图文消息
            String xml1 = ServiceNoHelper.xmlInitialUppercase(ServiceNoHelper.beanToxml(new ReplyWechatMessage(openId, wechatId, System.currentTimeMillis()/1000, msgType, "2"))).replace("</xml>", "");
            String xml2 = ServiceNoHelper.getArticlesXml(wechatArticlesMessages);

            return xml1.concat(xml2);
        }
    }
}
