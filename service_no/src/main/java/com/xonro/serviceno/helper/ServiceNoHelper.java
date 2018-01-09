package com.xonro.serviceno.helper;

import com.thoughtworks.xstream.XStream;
import com.xonro.serviceno.bean.WechatArticlesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/1/9
 */
public class ServiceNoHelper {
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        HashMap data = new HashMap();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        ByteArrayInputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();

        for(int idx = 0; idx < nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if(node.getNodeType() == 1) {
                Element element = (Element)node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }

        try {
            stream.close();
        } catch (Exception var10) {
            ;
        }

        return data;
    }

    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("xml");
        document.appendChild(root);
        Iterator tf = data.keySet().iterator();

        while(tf.hasNext()) {
            String transformer = (String)tf.next();
            String source = (String)data.get(transformer);
            if(source == null) {
                source = "";
            }

            source = source.trim();
            Element writer = document.createElement(transformer);
            writer.appendChild(document.createTextNode(source));
            root.appendChild(writer);
        }

        TransformerFactory tf1 = TransformerFactory.newInstance();
        Transformer transformer1 = tf1.newTransformer();
        DOMSource source1 = new DOMSource(document);
        transformer1.setOutputProperty("encoding", "UTF-8");
        transformer1.setOutputProperty("indent", "yes");
        StringWriter writer1 = new StringWriter();
        StreamResult result = new StreamResult(writer1);
        transformer1.transform(source1, result);
        String output = writer1.getBuffer().toString();

        try {
            writer1.close();
        } catch (Exception var12) {
            ;
        }

        return output;
    }

    /**
     * bean转xml
     * @param object 对象
     * @return xml
     */
    public static String beanToxml(Object object){
        XStream xstream = new XStream();
        xstream.alias("xml", object.getClass());
        xstream.aliasField("com.xonro.serviceno.bean.WechatArticlesMessage", object.getClass(), "item");
        return xstream.toXML(object);
    }

    /**
     * xml首字母大写
     * @param xmlMessage xml信息
     * @return 格式化后的xml
     */
    public static String xmlInitialUppercase(String xmlMessage){
        try {
            Map<String, String> xmlMap = xmlToMap(xmlMessage);
            System.err.println(""+xmlMap);
            Map<String, String> xmlMapNew = new HashMap<>();
            for (String key: xmlMap.keySet()){
                xmlMapNew.put(captureName(key), xmlMap.get(key));
            }
            System.err.println("111");
            return mapToXml(xmlMapNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getArticlesXml(List<WechatArticlesMessage> articlesMessageList){
        StringBuffer xmlnodes = new StringBuffer();
        if (articlesMessageList != null && articlesMessageList.size() > 0) {
            xmlnodes.append("<Articles>");
            for (int i = 0; i < articlesMessageList.size(); i++) {
                WechatArticlesMessage wechatArticlesMessage = articlesMessageList.get(i);
                String xmlMessage = "<Title><![CDATA["+wechatArticlesMessage.getTitle()+"]]></Title>\n" +
                        "<Description><![CDATA["+wechatArticlesMessage.getUrl()+"]]></Description>\n" +
                        "<PicUrl><![CDATA["+wechatArticlesMessage.getPicUrl()+"]]></PicUrl>\n" +
                        "<Url><![CDATA["+wechatArticlesMessage.getUrl()+"]]></Url>\n";
                xmlnodes.append("<item>");
                xmlnodes.append(xmlMessage);
                xmlnodes.append("</item>");
            }
            xmlnodes.append("</Articles>");
        }

        return xmlnodes.toString();
    }

    /**
     * 首字母大写
     * @param name 字段
     * @return 首字母大写的字段
     */
    private static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
