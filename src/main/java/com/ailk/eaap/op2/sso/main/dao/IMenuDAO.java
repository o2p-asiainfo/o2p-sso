package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import com.ailk.eaap.op2.sso.main.bo.MenuInfoBO;
/**
 * 主页面菜单DAO
 * @author zhaobl
 *
 */
public interface IMenuDAO { 
    
//    /**
//     * �˵���Ϣ��ѯ
//     * @param menuBean
//     * @return
//     */
//    public List<MenuInfo> queryMenuInfo(MenuInfo menuBean);
    /**
     * 查询主页面菜单项
     * @param sysPersonId
     * @param parentMenuId
     * @param businessSystemId
     * @return
     */
    public List<MenuInfoBO> queryMenuInfoBO(String sysPersonId, String parentMenuId, String businessSystemId,String type,String language,Integer tenantId);
}
