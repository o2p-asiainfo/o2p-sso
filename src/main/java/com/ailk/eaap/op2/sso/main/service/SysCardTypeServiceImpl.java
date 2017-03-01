package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.main.dao.SysCardTypeDao;
import com.ailk.eaap.op2.sso.main.model.SysCardType;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;

public class SysCardTypeServiceImpl implements SysCardTypeService {

	private SysCardTypeDao sysCardTypeDao; 
	public List<SysCardType> getCardTypes()
	{
		 List<SysCardType> list = new ArrayList<SysCardType>();
		 List<Map<String,Object>> Result = sysCardTypeDao.getCardTypes();
		 this.convertResult(Result, list);
		 return list ;
	}
	
	private void convertResult(List<Map<String,Object>> result,List<SysCardType> list)
	{
		for(Map<String,Object> item : result)
		{
			SysCardType bean = new SysCardType();
			bean.setSysCardTypeId(Long.valueOf(ConvertUtil.nullToSpace(item.get("SYS_CARD_TYPE_ID"))));
			bean.setSysCardTypeName(ConvertUtil.nullToSpace(item.get("SYS_CARD_TYPE_NAME")));
			list.add(bean);
		}
	}

	public SysCardTypeDao getSysCardTypeDao() {
		return sysCardTypeDao;
	}

	public void setSysCardTypeDao(SysCardTypeDao sysCardTypeDao) {
		this.sysCardTypeDao = sysCardTypeDao;
	}
}
