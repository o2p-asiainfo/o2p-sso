package com.ailk.eaap.op2.sso.main.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import com.ailk.eaap.op2.sso.main.dao.ListenerDao;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.model.UnifiedResponseBean;
import com.ailk.eaap.op2.sso.main.util.WebServiceClient;

public class UnifiedAuthImpl implements IUnifiedAuth {
	private PushService pushService;
	private PollService pollService;
	private ListenerDao listenerDao;

	public ListenerDao getListenerDao() {
		return listenerDao;
	}

	public void setListenerDao(ListenerDao listenerDao) {
		this.listenerDao = listenerDao;
	}

	public Map<String, UnifiedResponseBean> exchange(SYSPERSON person) {
		Map<String, UnifiedResponseBean> map = new HashMap<String, UnifiedResponseBean>();
		try {
			WebServiceClient wc = new WebServiceClient();
			String returnXml = wc.sendMessage(listenerDao.obtainUrl(),
					pushService.packageUnifinedXml(person));
			return pollService.parseXml(returnXml);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// map.put("er", "dsfdf");
		return map;
	}

	public PushService getPushService() {
		return pushService;
	}

	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}

	public PollService getPollService() {
		return pollService;
	}

	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}

}
