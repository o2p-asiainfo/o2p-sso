package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.dao.IBaseDAO;
import com.ailk.eaap.op2.sso.main.model.SysLogOperate;
import com.ailk.eaap.op2.sso.main.model.SysLogOperateExample;
import com.linkage.rainbow.ui.components.page.Page;

/**
 * null
 * ����:SYS_LOG_OPERATE
 * @author ��ӱ��
 * @abatorgenerated Wed Jan 27 16:39:13 CST 2010
 */
public interface SysLogOperateDAO extends IBaseDAO {
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