package com.xmljson;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * Created by Newx on 2017/3/20.
 */
public class XmlJsonUtil {
    public static JSON xml2json(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        if ((!xml.contains("&lt;")) && (!xml.contains("&gt;")) && (!xml.contains("&amp;")) && (!xml.contains("&apos;")) && (!xml.contains("&apos;"))) {
            xml = xml.replace("&", "&amp;");
        }
        JSON json = xmlSerializer.read(xml);
        return json;
    }

    public static String json2xml(String rootName, String elementName, String arrayName, String json) {
        JSONObject jobj = JSONObject.fromObject(json);
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName(rootName);
        xmlSerializer.setElementName(elementName);
        xmlSerializer.setArrayName(arrayName);
        xmlSerializer.setTypeHintsEnabled(false);
        xmlSerializer.setTypeHintsCompatibility(false);
        String xml = xmlSerializer.write(jobj);
        return xml;
    }
}