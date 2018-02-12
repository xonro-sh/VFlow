package com.xonro.serviceno;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xonro.serviceno.bean.message.ordinary.NewsItem;
import com.xonro.serviceno.bean.message.ordinary.NewsMsg;
import com.xonro.serviceno.bean.message.ordinary.OrdinaryMsg;
import com.xonro.serviceno.helper.MessageParser;
import com.xonro.serviceno.service.MessageService;
import com.xonro.serviceno.service.impl.MessageServiceImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author louie
 * @date 2018-2-9
 */
public class TestCases {

    @Test
    public void testToModel() throws DocumentException {
        String xmlString = "<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[image]]></MsgType>" +
                "<PicUrl><![CDATA[this is a url]]></PicUrl>" +
                "<MediaId><![CDATA[media_id]]></MediaId>" +
                "<MsgId>1234567890123456</MsgId></xml>";
        OrdinaryMsg message = new MessageParser(xmlString).parse();
        System.out.println(message.getClass());
        System.out.println(message.getFromUserName());
    }

    @Test
    public void testXStream() throws DocumentException {
        String xmlString = "<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[image]]></MsgType>" +
                "<PicUrl><![CDATA[this is a url]]></PicUrl>" +
                "<MediaId></MediaId>" +
                "<MsgId>1234567890123456</MsgId></xml>";
        OrdinaryMsg message = new MessageParser(xmlString).parse();

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        System.out.println(xStream.toXML(message));
    }

    @Test
    public void testNewsMsg2Xml(){
        NewsMsg newsMsg = new NewsMsg();
        newsMsg.setArticleCount(2);
        newsMsg.setFromUserName("fromUser");
        newsMsg.setToUserName("toUser");
        newsMsg.setMsgType("news");
        newsMsg.setCreateTime((System.currentTimeMillis()/1000)+"");

        List<NewsItem> items = new ArrayList<>();
        NewsItem newsItem = new NewsItem();
        newsItem.setDescription("description");
        newsItem.setPicUrl("picUrl");
        newsItem.setTitle("title");
        newsItem.setUrl("url");

        items.add(newsItem);

        newsMsg.setArticles(items);

        MessageService service = new MessageServiceImpl();
        System.out.println(service.replyMessage(newsMsg));
    }


}
