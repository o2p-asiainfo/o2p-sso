package com.ailk.eaap.op2.sso.main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;

public class FileRead {

	private static final String FOLDNAME = "C:"+File.separator+"CRM_CTG_USER";
	private static Logger log = Logger.getLog(FileRead.class);
	//private static final String FILENAME = "CTG1001.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		//List list = reateObjectFile(FILENAME);
	}
	
	
	//读取文件	
	 public static SYSPERSON reateObjectFile(String file)
	 {		 
	   File fileObject = new File(FOLDNAME + File.separator + file); //用路径名生成源文件
	   SYSPERSON lsf = null;	
	   //路径名生成的文件存在时
	   if (fileObject.exists())
	   {
		   FileInputStream fis = null;
		   ObjectInputStream ois = null;
			   try {
				   fis = new FileInputStream(fileObject); //读文件
				   ois = new ObjectInputStream(fis); //读对象  
				   lsf = (SYSPERSON) ois.readObject();			
                   if(lsf != null){
                   }
                   else{
                   }
				   
			   }catch (ClassNotFoundException e) {
				   log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
					
				}catch (IOException e1) {
					log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),null));
				}finally{
					if(null != ois){
						try {
							ois.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
					}
					if(null !=  fis){
						try {
							fis.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
					}
				}
		   }
	   else{
	   }
	   return lsf;
	 }
}
