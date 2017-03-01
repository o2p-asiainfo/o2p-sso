//package com.ailk.eaap.op2.sso.main.memcached;
//
//import java.util.ArrayList;
//import java.util.concurrent.TimeoutException;
//
//import net.rubyeye.xmemcached.exception.MemcachedException;
//
//import org.apache.log4j.Logger;
//
//import com.ailk.eaap.op2.common.EAAPException;
//import com.ailk.eaap.op2.loginFilter.bo.UserInfo;
//import com.ailk.eaap.op2.memcache.manage.service.IMemcacheManageServ;
//import com.ailk.eaap.op2.sso.framework.context.SpringBeanInvoker;
//import com.ailk.eaap.op2.sso.main.bo.ClientMemcachedInfo;
///**
// * 
// * @Package com.ailk.eaap.op2.sso.main.memcached
// * @ClassName: MemOperator
// * @Description: TODO(缓存服务器存储数据)
// * @author sun
// * @date 2013-6-4 下午05:52:00
// */
//public class MemOperator {
//	
//	private static Logger logger = Logger.getLogger("logserver");
//	private static IMemcacheManageServ memcacheServ = (IMemcacheManageServ) SpringBeanInvoker.getBean("memcacheManageServ");
//	/**
//	 * @Title: gets
//	 * @Description: TODO
//	 * @param client
//	 * @param key
//	 * @return GetsResponse<Object>	
//	 * @throws TimeoutException
//	 * @throws InterruptedException
//	 * @throws MemcachedException
//	 */
//	public static Object gets(String key) {
//		Object value = null;
//		try {
//			value = memcacheServ.getKey(key);
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		return value;
//	}
//
//	public static void add(String key, int exp, ClientMemcachedInfo clientMemInfo)throws EAAPException {
//		try {
//			ArrayList<ClientMemcachedInfo> listMem = new  ArrayList<ClientMemcachedInfo>();
//			listMem.add(clientMemInfo);
//			Object key2 = memcacheServ.getKey(key);
//			if(key2 == null){
//				memcacheServ.addObj(key, exp, listMem);
//			}else{
//				ArrayList<ClientMemcachedInfo> list  = (ArrayList<ClientMemcachedInfo>)key2;
//				list.add(clientMemInfo);
//				replace(key, exp, list);
//			}
//		} catch (EAAPException e) {
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}
//	
//	//zhaobl
//	public static void add(String key, int time, UserInfo userInfo)throws EAAPException {
//		memcacheServ.addObj(key, time, userInfo);
//	}
//	//zhaobl
//	public static void delete(String key) throws EAAPException{
//		memcacheServ.removeKey(key);
//	}
//	
//	public static void add(String key, int time, final String value)throws EAAPException {
//		try {
//			
//			memcacheServ.addObj(key, time, value);
//		} catch (EAAPException e) {
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}
//	public static void replace(String key, int time, Object obj)throws EAAPException {
//		try {
//			memcacheServ.replace(key, time, obj);
//		} catch (EAAPException e) {
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}
//	public static void replaceByKey(String key, int time)throws EAAPException {
//		try {
//			memcacheServ.replaceByKey(key, time);
//		} catch (EAAPException e) {
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}
//	public static void main(String args[]){
//		try {
//			MemOperator.add("123", 0, "123");
//		} catch (EAAPException e) {
//			e.printStackTrace();
//		}
//		Object gets = MemOperator.gets("123");
//		System.out.println(gets);
//	}
//}
