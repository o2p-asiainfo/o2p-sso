package com.ailk.eaap.op2.sso.main.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.NpDataBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;

public class ConvertUtil {
	private static final Map<String,String> ISENDMAP = new HashMap<String, String>();
	
    static {        
    	ISENDMAP.put("B", "开始");        
    	ISENDMAP.put("E", "结束");   
    }
	private static final Map<String,String> INOROUTMAP = new HashMap<String, String>();
	
    static {       
    	INOROUTMAP.put("0", "携出");   
    	INOROUTMAP.put("1", "携入");        
    	INOROUTMAP.put("2", "归属");   
    }
	private static final Map<String,String> PROVINCEMAP = new HashMap<String, String>();
	
    static {       
    	PROVINCEMAP.put("609902", "天津");   
    	PROVINCEMAP.put("600403", "海南");        
    	PROVINCEMAP.put("100000", "集团");   
    }
	private static final Map<String,String> FLOWMAP = new HashMap<String, String>();
	
    static {       
    	FLOWMAP.put("1", "携入申请流程");   
    	FLOWMAP.put("2", "携出申请流程");        
    	FLOWMAP.put("3", "携入生效流程");   
    	FLOWMAP.put("4", "携出生效流程");   
    	FLOWMAP.put("5", "携入注销流程");   
    	FLOWMAP.put("6", "归属注销流程");   
    	FLOWMAP.put("7", "携出方注销流程");   
    	FLOWMAP.put("8", "携入方取消流程");   
    	FLOWMAP.put("9", "携出方取消流程");   
    	FLOWMAP.put("10", "CSMS携入方取消流程");   
    	FLOWMAP.put("11", "CSMS携出方取消流程");   
    	FLOWMAP.put("12", "携出停机流程");   
    	FLOWMAP.put("13", "携入方停机流程");   
    	FLOWMAP.put("14", "携出恢复流程");   
    	FLOWMAP.put("15", "携入恢复流程");   
    	FLOWMAP.put("16", "审计流程");   
    }
    
	private static final Map<String,String> ENDDESPMAP = new HashMap<String, String>();
	
    static {       
    	ENDDESPMAP.put("1", "正常在途");   
    	ENDDESPMAP.put("2", "正常结束");        
    	ENDDESPMAP.put("3", "异常结束");   
    }
	public static final boolean isEmpty(String value){
		return value == null || "".equals(value) ? true : false;
	}
	public static final String nullToSpace(Object value){
		return value == null ?  "" : value.toString();
	}
	public static String convertIsEndCd(String isEndCd){
		return ISENDMAP.containsKey(isEndCd) ? ISENDMAP.get(isEndCd) : "";
	}
	public static String convertInOrOutCd(String inOrOutCd){
		return INOROUTMAP.containsKey(inOrOutCd) ? INOROUTMAP.get(inOrOutCd) : "";
	}
	public static String convertProvinceCd(String code){
		return PROVINCEMAP.containsKey(code) ? PROVINCEMAP.get(code) : "";
	}
	public static String convertFlowCd(String code){
		return FLOWMAP.containsKey(code) ? FLOWMAP.get(code) : "";
	}
	public static String convertStateCd(String code){
		return ENDDESPMAP.containsKey(code) ? ENDDESPMAP.get(code) : "";
	}
	public static String convertPageBeanToJsonObj(PageBean bean){
		//jsonResult = "{user:[{name:'aaa',sex:'man'},{name:'bbb',sex:'woman'}]}";
		StringBuffer buffer = new StringBuffer("");
		buffer.append("pageBean:[{");
		buffer.append("totalRecord:'").append(nullToSpace(bean.getTotalRecord())).append("',");
		buffer.append("totalPage:'").append(nullToSpace(bean.getTotalPage())).append("',");
		buffer.append("pageCount:'").append(nullToSpace(bean.getPageCount())).append("',");
		buffer.append("currentPage:'").append(nullToSpace(bean.getCurrentPage())).append("'");
		buffer.append("}]");
		return buffer.toString();
	}
	public static String convertNplistToJsonObj(List<NpDataBean> list){
		StringBuffer buffer = new StringBuffer("");
		buffer.append("npCodeList:[");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				NpDataBean item = list.get(i);
				buffer.append("{npCode:'").append(nullToSpace(item.getNpCode())).append("',");
				buffer.append("npCodeList:'").append(nullToSpace(item.getNpCodeList())).append("',");
				buffer.append("startTime:'").append(nullToSpace(item.getStartTime())).append("',");
				buffer.append("endTime:'").append(nullToSpace(item.getEndTime())).append("'}");
				if(i != list.size()-1){
					buffer.append(",");
				}
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	public static String convertValuleListToJsonObj(List<KeyValueBean> list){
		StringBuffer buffer = new StringBuffer("");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				KeyValueBean item = list.get(i);
				buffer.append("{key:'").append(nullToSpace(item.getKey())).append("',");
				buffer.append("value:'").append(nullToSpace(item.getValue())).append("'}");
				if(i != list.size()-1){
					buffer.append(",");
				}
			}
		}
		return buffer.toString();
	}
	/**  
     * 格式化XML文件  
     * @param xml  
     * @return  
     * @throws DocumentException   
     * @throws IOException   
     */  
    public static String formatXml(String xml){            
        String returnValue = "";
		try {
			Document document = DocumentHelper.parseText(xml);     
			//创建输出格式   
			OutputFormat format = OutputFormat.createPrettyPrint();        
			//制定输出xml的编码类型   
			format.setEncoding("UTF-8");             
			StringWriter writer = new StringWriter();   
			//创建一个文件输出流   
			XMLWriter xmlwriter = new XMLWriter( writer, format );   
			//将格式化后的xml串写入到文件   
			xmlwriter.write(document);    
			returnValue = writer.toString();   
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}                 
        //返回编译后的字符串格式   
         return returnValue;   
    }  
}
