package com.ailk.eaap.op2.sso.main.util;

import com.ailk.eaap.op2.sso.main.bo.MenuInfo;
import com.ailk.eaap.op2.sso.main.vo.MainMenuInfoVO;
import com.ailk.eaap.op2.sso.main.vo.TreeMenuInfoVO;

public class BOToVOUtils {
	public static MainMenuInfoVO getMainMenuInfoVO(MenuInfo menuInfo){
		MainMenuInfoVO mvo = new MainMenuInfoVO();
		mvo.setId(menuInfo.getMenuId()+"");
		mvo.setName(menuInfo.getMenuName());
		
		return mvo;
	}
	public static TreeMenuInfoVO getTreeMenuInfoVO(MenuInfo menuInfo){
		TreeMenuInfoVO treeVO = new TreeMenuInfoVO();
		treeVO.setId(menuInfo.getMenuId()+"");
		treeVO.setName(menuInfo.getMenuName());
		treeVO.setPid(menuInfo.getParentMenuId()+"");
		treeVO.setHref(menuInfo.getHref());
		treeVO.setIcon("../resource/blue/images/ico/dept.gif");
		if((menuInfo.getChildrenMenus() == null) || menuInfo.getChildrenMenus().size()==0){
			treeVO.setParent(false);
			treeVO.setClick();
		}else{
			treeVO.setParent(true);
		}
		return treeVO;
	}
}
