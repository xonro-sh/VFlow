package com.xonro.serviceno.helper;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息解析器
 * @author louie
 * @date 2018-2-8
 */
public class MessegeParser {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 微信推送的xml格式消息全文
     */
    private String xmlMessage;

    /**
     * 解析后的消息数据
     */
    private Map<String,String> messageData;

    /**
     * 解析消息数据
     * @return
     */
    public MessegeParser parse() throws DocumentException {
        messageData = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(xmlMessage);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();

            for (Element element : elements) {
                messageData.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return this;
    }

    public String getToUser(){
        return messageData.get("ToUserName");

    }

    /**
     * 获取消息类型
     * @return
     */
    public String getMessageType(){
        return messageData.get("MsgType");
    }

    /**
     * 获取消息创建时间
     * @return
     */
    public Integer getCreateTime(){
        return Integer.valueOf(messageData.get("CreateTime"));
    }

    /**
     * 获取消息发送者
     * @return
     */
    public String getFromUser(){
        return messageData.get("FromUserName");
    }

    /**
     * 获取文本消息内容
     * @return
     */
    public String getContent(){
        return messageData.get("Content");
    }

    /**
     * 获取消息id
     * @return
     */
    public String getMeesageId(){
        return messageData.get("MsgId");
    }


    public MessegeParser(String xmlMessage){
        this.xmlMessage = xmlMessage;
    }




}
