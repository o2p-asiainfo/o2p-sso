package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysLogLogin;
import com.ailk.eaap.op2.sso.main.model.SysLogLoginExample;
import com.linkage.rainbow.ui.components.page.Page;

public interface SysLogLoginService {
	Integer insert(SysLogLogin record);

    int updateByPrimaryKey(SysLogLogin record);

    int updateByPrimaryKeySelective(SysLogLogin record);

    List selectByExample(SysLogLogin example);

    Page selectPageByExample(SysLogLoginExample example);

    Integer selectCountByExample(SysLogLoginExample example);

    SysLogLogin selectByPrimaryKey(Integer sysLoginId);

    int deleteByExample(SysLogLoginExample example);

    int deleteByPrimaryKey(SysLogLogin key);
    
    int updateLog(SysLogLogin record);
    
    Integer insertErrorNumber(SysLogLogin record);
}
