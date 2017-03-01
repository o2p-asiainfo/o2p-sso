package com.ailk.eaap.op2.sso.framework.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ailk.eaap.op2.sso.framework.dao.IBaseDAO;
import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;
import com.linkage.rainbow.ui.components.page.Page;

public class BaseServiceImpl implements IBaseService
{
	public final Log log = LogFactory.getLog(BaseServiceImpl.class);
	
	public final Log dblog = LogFactory.getLog("business");

	public IBaseDAO baseDAO;

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public IBaseDAO getBaseDAO() {
		return this.baseDAO;
	}

	public BaseServiceImpl() {

	}

	public int deleteByExample(BaseExampleObject example) {
		return getBaseDAO().deleteByExample(example);
	}

	public int deleteByPrimaryKey(Object object) {
		return getBaseDAO().deleteByPrimaryKey(object);
	}

	public void insert(Object object) {
		getBaseDAO().insert(object);
	}

	public List selectByExample(BaseExampleObject example) {
		return getBaseDAO().selectByExample(example);
	}

	public Object selectByPrimaryKey(Object object) {
		return getBaseDAO().selectByPrimaryKey(object);
	}

	public Integer selectCountByExample(BaseExampleObject example) {
		return getBaseDAO().selectCountByExample(example);
	}

	public Page selectPageByExample(BaseExampleObject example) {
		return getBaseDAO().selectPageByExample(example);
	}

	public int updateByPrimaryKey(Object object) {
		return getBaseDAO().updateByPrimaryKey(object);
	}

	public int updateByPrimaryKeySelective(Object object) {
		return getBaseDAO().updateByPrimaryKeySelective(object);
	}
	
}