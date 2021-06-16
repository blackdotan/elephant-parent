package com.blackdotan.elephant.payment.weixin.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/10.
 */
public class WXTools {


    /**
     * 微信随机串
     * @return
     */
    public static String noncestr() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    /**
     * 微信签名
     * @param data
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> data, String secret) throws Exception {
        Map<String, String> tree = new TreeMap<>(data);
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : tree.entrySet()) {
            if(entry.getValue()!=null || !entry.getValue().equals("")) {
                buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        buffer.append("key").append("=").append(secret);
        System.out.println(buffer.toString());
        return md5(buffer.toString()).toUpperCase();
    }

    /**
     * 时间戳
     * @return
     */
    public static String timestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


    private static final String PREFIX_XML = "<xml>";

    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 生成xml格式
     * @param parm
     * @param isAddCDATA
     * @return
     */
    @Deprecated
    public static String xmlformat(Map<String, String> parm, boolean isAddCDATA) {
        StringBuffer strbuff = new StringBuffer(PREFIX_XML);
        if (parm.size() > 0) {
            for (Map.Entry<String, String> entry : parm.entrySet()) {
                strbuff.append("<").append(entry.getKey()).append(">");
                if( "attach".equalsIgnoreCase(entry.getKey())){
                    strbuff.append(PREFIX_CDATA);
                    if (entry.getValue()!=null && !entry.getValue().equals("") ) {
                        strbuff.append(entry.getValue());
                    }
                    strbuff.append(SUFFIX_CDATA);
                } else {
                    strbuff.append(entry.getValue());
                }
                strbuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strbuff.append(SUFFIX_XML).toString();
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return String
     */
    public static String maptoxml(Map<String, String> data, String sign) {
        Map<String, String> tree = new TreeMap<>(data);
        Element root = new Element("xml");
        Document doc = new Document();
        // 参数
        for(String k : tree.keySet()) {
            Element child = new Element(k);
//            child.addContent(new CDATA(tree.get(k)));
            child.addContent(tree.get(k));
            root.addContent(child);
        }
        // 新增签名
        Element element = new Element("sign");
        element.addContent(sign);
        root.addContent(element);
        doc.setRootElement(root);
        XMLOutputter xmOut = new XMLOutputter(Format.getPrettyFormat());
        return xmOut.outputString(doc);
    }

    /**
     * xml转map
     * @param strXML
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmltomap(String strXML) throws Exception {
        InputStream stream = null;
        stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(stream);
        Element root = document.getRootElement();
        if (!root.getName().equals("xml")) {
            throw new Exception("XML root \""+ root.getName() +"\" is invalid");
        }
        List<Element> list = root.getChildren();
        HashMap<String, String> data = new HashMap<String, String>();
        for (Element node : list) {
            data.put(node.getName(), node.getTextTrim());
        }
        return data;
    }

    public static String md5(String s) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("md5");
        byte[] array = md.digest(s.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
