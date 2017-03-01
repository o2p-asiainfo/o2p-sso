package com.ailk.eaap.op2.sso.main.util;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class JSONUtils {
	public static String getArrayStringFromJson(String jsonStr) {  
	    XMLSerializer xmlSerializer = new XMLSerializer();  
	    JSON json = JSONObject.fromObject(jsonStr);
	    String xml = xmlSerializer.write(json);
	    return xml;  
	}  
	public static void main(String[] args) {
	}
}
