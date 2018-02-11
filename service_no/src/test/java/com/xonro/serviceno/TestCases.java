package com.xonro.serviceno;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xonro.serviceno.bean.message.ordinary.OrdinaryMsg;
import com.xonro.serviceno.helper.MessageParser;
import org.dom4j.DocumentException;
import org.junit.Test;

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


}
