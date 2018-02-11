package com.xonro.serviceno;

import com.xonro.serviceno.bean.message.ordinary.OrdinaryMessage;
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
        OrdinaryMessage message = new MessageParser(xmlString).parse();
        System.out.println(message.getClass());
        System.out.println(message.getFromUserName());
    }


}
