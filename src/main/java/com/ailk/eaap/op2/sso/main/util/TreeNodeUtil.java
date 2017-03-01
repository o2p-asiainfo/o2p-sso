package com.ailk.eaap.op2.sso.main.util;

import com.ailk.eaap.op2.sso.main.model.SysFunction;


public class TreeNodeUtil {
	public static String createJsArray(SysFunction[] tn , String dtree) {   
        if(tn == null || tn.length == 0 ){   
            return "";   
        }   
        StringBuffer contents = new StringBuffer(100);   
        contents.append("new dTree('"+dtree+"');");   
        for (int i = 0; i < tn.length; i++) {    
            contents.append("\n");   
            contents.append(dtree+".add('");   
            contents.append(tn[i].getFunctionId());   
            contents.append("','");   
            contents.append(tn[i].getParentFunctionId());   
            contents.append("','");   
            contents.append(tn[i].getFunctionName());   
            if(tn[i].getUrl()!=null && !tn[i].getUrl().equals(""))
            {
            	contents.append("','");   
                contents.append(tn[i].getUrl()); 
                contents.append("','");
                contents.append("','");
                contents.append("");
            }
            contents.append("');");   
        }   
        System.out.println(contents);
        return contents.toString();   
    }   

}
