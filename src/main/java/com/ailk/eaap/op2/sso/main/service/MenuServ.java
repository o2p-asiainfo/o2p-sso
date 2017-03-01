package com.ailk.eaap.op2.sso.main.service;

import java.util.List;
import com.ailk.eaap.op2.sso.main.bo.MenuInfo;
import com.ailk.eaap.op2.sso.main.bo.MenuInfoBO;
import com.ailk.eaap.op2.sso.main.dao.IMenuDAO;


public class MenuServ implements IMenuServ {
    private IMenuDAO menuDAO;
    
    public List<MenuInfoBO> queryMenuInfoBO(String sysPersonId,
			String parentMenuId, String businessSystemId,String type,String language,Integer tenantId) {
    	List<MenuInfoBO> menuInfoBOList = menuDAO.queryMenuInfoBO(sysPersonId, parentMenuId, businessSystemId,type,language,tenantId);
    	return menuInfoBOList;
	}

    /*
    public List<MenuInfo> queryMenuInfoByPersonId(Integer PersonId,Integer parentMenuId){
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setPersonId(PersonId);
		menuInfo.setParentMenuId(parentMenuId);
		return menuDAO.queryMenuInfo(menuInfo);
    }
    public List<MenuInfo> queryMenuInfoAndChildrenByPersonId(Integer PersonId,Integer parentMenuId){
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setPersonId(PersonId);
		menuInfo.setParentMenuId(parentMenuId);
		
		List<MenuInfo> menuInfos =  menuDAO.queryMenuInfo(menuInfo);
		for(MenuInfo mi:menuInfos){
			List<MenuInfo> childrenMenuInfos = this.queryMenuInfoAndChildrenByPersonId(PersonId, mi.getMenuId());
			if(childrenMenuInfos.size()>0){
				mi.setChildrenMenus(childrenMenuInfos);
				
			}
		}
		return menuInfos;
    }
	*/
    
    public IMenuDAO getMenuDAO() {
        return menuDAO;
    }

    public void setMenuDAO(IMenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

}
