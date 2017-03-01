package com.ailk.eaap.op2.sso.main.action;



import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.linkage.rainbow.ui.struts.BaseAction;

public class ProjectJumpAction extends BaseAction {

	/**
	 * 
	 */
	Logger log = Logger.getLog(this.getClass());
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		log.debug(this.getText("eaap.op2.sso.login.jump"));
		return SUCCESS;
	}
}
