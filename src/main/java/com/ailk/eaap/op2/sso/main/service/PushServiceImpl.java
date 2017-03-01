package com.ailk.eaap.op2.sso.main.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ailk.eaap.op2.sso.main.dao.ListenerDao;
import com.ailk.eaap.op2.sso.main.model.RequestContractBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.model.UnifinedAuthBean;

public class PushServiceImpl implements PushService {
	private ListenerDao listenerDao;

	private UnifinedAuthBean unifiedAuthBean;

	private SysLogLoginService sysLogLoginService;

	public SysLogLoginService getSysLogLoginService() {
		return sysLogLoginService;
	}

	public void setSysLogLoginService(SysLogLoginService sysLogLoginService) {
		this.sysLogLoginService = sysLogLoginService;
	}

	public UnifinedAuthBean getUnifiedAuthBean() {
		return unifiedAuthBean;
	}

	public void setUnifiedAuthBean(UnifinedAuthBean unifiedAuthBean) {
		this.unifiedAuthBean = unifiedAuthBean;
	}

	public ListenerDao getListenerDao() {
		return listenerDao;
	}

	public void setListenerDao(ListenerDao listenerDao) {
		this.listenerDao = listenerDao;
	}

	public String packageUnifinedXml(SYSPERSON person) {
		StringBuffer req = new StringBuffer(RequestContractBean.TCPCONT);
		req.insert(492, UnifinedAuthBean.SVCCONT);
		String request = req.toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		unifiedAuthBean.setTransactionId(listenerDao.obtainRequestheader()
				.getSrcSysId()
				+ dateFormat.format(new Date())
				+ listenerDao.obtainSerialNumber());
		request = request.replace("busCode",
				listenerDao.obtainRequestheader().getBusCode()).replace(
				"serviceCode",
				listenerDao.obtainRequestheader().getServiceCode()).replace(
				"serviceContractVer",
				listenerDao.obtainRequestheader().getServiceContractVer())
				.replace("actionCode",
						listenerDao.obtainRequestheader().getActionCode())
				.replace("transactionID", unifiedAuthBean.getTransactionId())
				.replace("serviceLevel",
						listenerDao.obtainRequestheader().getServiceLevel())
				.replace("srcOrgID",
						listenerDao.obtainRequestheader().getSrcOrgId())
				.replace("srcSysID",
						listenerDao.obtainRequestheader().getSrcSysId())
				.replace("srcSysSign",
						listenerDao.obtainRequestheader().getSrcSysSign())
				.replace("dstOrgID",
						listenerDao.obtainRequestheader().getDstOrgId())
				.replace("dstSysID",
						listenerDao.obtainRequestheader().getDstSysId())
				.replace("reqTime", date.format(new Date()).toString())
				.replace("accountType", UnifinedAuthBean.ACCOUNTTYPE).replace(
						"accountID", person.getCardNumber()).replace("pWDType",
						UnifinedAuthBean.PWDTYPE).replace("password",
						person.getPassword());
		return request;
	}

}
