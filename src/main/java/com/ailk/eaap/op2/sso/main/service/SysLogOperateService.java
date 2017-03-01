package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.service.IBaseService;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.SysLogOperateExample;
import com.linkage.rainbow.ui.components.page.Page;

public interface SysLogOperateService extends IBaseService{
	 Integer insert(SysLogOperate record);

	    int updateByPrimaryKey(SysLogOperate record);

	    int updateByPrimaryKeySelective(SysLogOperate record);

	    List selectByExample(SysLogOperateExample example);

	    Page selectPageByExample(SysLogOperateExample example);

	    Integer selectCountByExample(SysLogOperateExample example);

	    SysLogOperate selectByPrimaryKey(Integer sysLogOperateId);

	    int deleteByExample(SysLogOperateExample example);

	    int deleteByPrimaryKey(SysLogOperate key);
}
