package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.dao.IBaseDAO;
import com.ailk.eaap.op2.sso.main.model.SysLogLogin;
import com.ailk.eaap.op2.sso.main.model.SysLogLoginExample;
import com.linkage.rainbow.ui.components.page.Page;

/**
 * null
 * ����:SYS_LOG_LOGIN
 * @author ��ӱ��
 * @abatorgenerated Wed Jan 27 16:39:13 CST 2010
 */
public interface SysLogLoginDAO extends IBaseDAO {
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