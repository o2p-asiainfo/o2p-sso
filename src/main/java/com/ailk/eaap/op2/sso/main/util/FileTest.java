package com.ailk.eaap.op2.sso.main.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.model.SYSPERSON;

//import bss.common.util.LoginedStaffInfo;

public class FileTest 
{
	//private static final String FILENAME = "1234567890.txt";

	private static Logger log = Logger.getLog(FileTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//LoginedStaffInfo loginedStaffInfo = new LoginedStaffInfo();
		//newFolder(FOLDNAME);
		//createObjectFile(FILENAME,loginedStaffInfo);
		//deleteFile(FILENAME);		
	}
	//创建指定目录
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
				log.debug(folderPath+ "目录创建成功");
			}
			else{
				log.debug("目录已经存在");
			}
		} catch (Exception e) {
			log.debug("新建目录操作出错");
		}
	}
	
	//读取文件	
	 public static void createObjectFile(String file,SYSPERSON loginedStaffInfo)
	 {		 
	   File fileObject = new File(SystemUtil.FOLDNAME + File.separator + file); //用路径名生成源文件
	   
//	   loginedStaffInfo.setAreaId(new Integer(1));
//	   loginedStaffInfo.setAreaName("电信集团");
//	   loginedStaffInfo.setStaffId(new Long(1));
//	   loginedStaffInfo.setStaffName("ctg1001");
//	   loginedStaffInfo.setStaffOrgId(new Long(1));
//	   loginedStaffInfo.setStaffOrgName("集团");
//	   
	   //路径名生成的文件不存在时
	   if (!fileObject.exists())
	   {
		   FileOutputStream fout = null;
		   ObjectOutputStream oos = null;
			try {
				fileObject.createNewFile();//新建一个文件
				fout  = new FileOutputStream(fileObject);//新建文件输出流
			    oos =new ObjectOutputStream(fout); //新建对象输出流
			    oos.writeObject(loginedStaffInfo);
			    
			    oos.flush();
				oos.close();
			    }catch (IOException e1) {
			    	log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),null));
				}finally{
					if(null != oos){
						try {
							oos.flush();
							oos.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
						
					}
					if(null != fout){
						try {
							fout.flush();
							fout.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
					}
				}
		   }else{
			   FileInputStream fis = null;
			   ObjectInputStream ois = null;
			   try {
			    fis = new FileInputStream(fileObject); //读文件
			    ois = new ObjectInputStream(fis); //读对象  
			    SYSPERSON lsf = (SYSPERSON) ois.readObject();			
				
			   }catch (ClassNotFoundException e) {
				   log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
					
				}catch (IOException e1) {
					log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),null));
				}finally{
					if(null !=  ois){
						try {
							ois.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
					}
					if(null != fis){
						try {
							fis.close();
						} catch (IOException e) {
							log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
						}
					}
				}
		   }
	 }
	 
	 /**   
		 * 删除文件，可以是单个文件或文件夹   
		 * @param   fileName    待删除的文件名   
		 * @return 文件删除成功返回true,否则返回false   
		 */
		public static boolean delete(String fileName) {
			File file = new File(fileName);
			if (!file.exists()) {
				return false;
			} else {
				if (file.isFile()) {
					return deleteFile(fileName);
				} else {
					return deleteDirectory(fileName);
				}
			}
		}

		/**   
		 * 删除单个文件   
		 * @param   fileName    被删除文件的文件名   
		 * @return 单个文件删除成功返回true,否则返回false   
		 */
		public static boolean deleteFile(String fileName) {
			File file = new File(SystemUtil.FOLDNAME + File.separator + fileName);
			if (file.isFile() && file.exists()) {
				file.delete();
				return true;
			} else {
				return false;
			}
		}

		/**   
		 * 删除目录（文件夹）以及目录下的文件   
		 * @param   dir 被删除目录的文件路径   
		 * @return  目录删除成功返回true,否则返回false   
		 */
		public static boolean deleteDirectory(String dir) {
			//如果dir不以文件分隔符结尾，自动添加文件分隔符    
			if (!dir.endsWith(File.separator)) {
				dir = dir + File.separator;
			}
			File dirFile = new File(dir);
			//如果dir对应的文件不存在，或者不是一个目录，则退出    
			if (!dirFile.exists() || !dirFile.isDirectory()) {
				return false;
			}
			boolean flag = true;
			//删除文件夹下的所有文件(包括子目录)    
			File[] files = dirFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				//删除子文件    
				if (files[i].isFile()) {
					flag = deleteFile(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				}
				//删除子目录    
				else {
					flag = deleteDirectory(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				}
			}

			if (!flag) {
				return false;
			}
			//删除当前目录,如果要删除目录请加上这一行   
			if (dirFile.delete()) {
				return true;
			} else {
				return false;
			}
		}
}
