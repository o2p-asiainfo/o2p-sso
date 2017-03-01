package com.ailk.eaap.op2.sso.main.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

public class ReadProperties {
	private static Logger log = Logger.getLog(ReadProperties.class);
	 public String getFileContent(String Str)
	    {
	    	Properties props = new Properties();
			InputStream in = null;
	    	try {
	    		in = getClass().getClassLoader().getResourceAsStream("url.properties");
	    		props.load(in);
				if(null !=  in){
					in.close();
				}
	    	} 
	    	catch (IOException e) 
	    	{
	    		log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
	    	}finally{
	    		if(null !=  in){
					try {
						in.close();
					} catch (IOException e) {
						log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
					}
				}
	    	}
	    	String StrValue = props.getProperty(Str);
	    	return StrValue ;
	    }

	public static void main(String [] args)
	{
		ReadProperties trp = new ReadProperties();	
	}

}
