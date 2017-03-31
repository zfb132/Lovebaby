package com.whuzfb.lovebaby;

import android.provider.DocumentsContract;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by zfb15 on 2017/3/29.
 */

public class DomReader {
    public static String readXml(InputStream inputStream)throws Exception{
        List<HashMap<String,String>> data=null;
        DocumentBuilderFactory fac=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=fac.newDocumentBuilder();
        //生成Dom数
        Document doc=builder.parse(inputStream);

        //获取文档的根节点
        //Element root=doc.getDocumentElement();

        //获取节点名称为book的节点
        NodeList nodes=doc.getElementsByTagName("book");


        return nodes.toString();
    }
}
