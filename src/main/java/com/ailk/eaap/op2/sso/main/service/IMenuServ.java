package com.ailk.eaap.op2.sso.main.service;

import java.util.List;
import com.ailk.eaap.op2.sso.main.bo.MenuInfo;
import com.ailk.eaap.op2.sso.main.bo.MenuInfoBO;

/**
 * 加载主页面菜单项serv
 * @author zhaobl
 *
 */
public interface IMenuServ{ 
 	
// 	/**
//	 * ��ݵ�¼��ȡ�ò˵���Ϣ
//	 * @param PersonId ��Ա��ʶ
//	 * @param parentMenuId �ϼ��˵����������ѯ�û���һ���˵�
//	 * @return
//	 */
//	public List<MenuInfo> queryMenuInfoByPersonId(Integer PersonId,Integer parentMenuId) ;
//	public List<MenuInfo> queryMenuInfoAndChildrenByPersonId(Integer PersonId,Integer parentMenuId);
	
	/**
     * 查询主页面菜单项
     * @param sysPersonId
     * @param parentMenuId
     * @param businessSystemId
     * @return
     */
    public List<MenuInfoBO> queryMenuInfoBO(String sysPersonId, String parentMenuId, String businessSystemId,String type,String language,Integer tenantId);
}
