package com.ailk.eaap.op2.sso.framework.service;

import java.util.List;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;
import com.linkage.rainbow.ui.components.page.Page;

public interface IBaseService {

    void insert(Object object);

    int updateByPrimaryKey(Object object);

    int updateByPrimaryKeySelective(Object object);

    List selectByExample(BaseExampleObject example);

    Page selectPageByExample(BaseExampleObject example);

    Integer selectCountByExample(BaseExampleObject example);

    Object selectByPrimaryKey(Object object);

    int deleteByExample(BaseExampleObject example);

    int deleteByPrimaryKey(Object object);
}