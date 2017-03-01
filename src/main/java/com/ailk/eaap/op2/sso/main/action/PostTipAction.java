package com.ailk.eaap.op2.sso.main.action;

import java.io.UnsupportedEncodingException;

import com.ailk.eaap.op2.sso.framework.action.BaseAction;
import com.ailk.eaap.op2.sso.main.model.SvcTipBean;
import com.ailk.eaap.op2.sso.main.service.ITipService;

public class PostTipAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ITipService bbsPostTipService;
	private String title;
	private String content;
	private String kind;

	public String postTip() {
		SvcTipBean bean = new SvcTipBean();
		bean.setKind(kind);
		bean.setTitle(title);
		bean.setContent(content);
		bean.setIpAddr(request.getRemoteAddr());
		bean.setUserId("123");
		bbsPostTipService.doPostTips(bean);
		return SUCCESS;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setBbsPostTipService(ITipService bbsPostTipService) {
		this.bbsPostTipService = bbsPostTipService;
	}
	public String change(String str)
	{
	   try {
		return new String(str.getBytes("iso-8859-1"),"UTF-8");
	} catch (UnsupportedEncodingException e) {
		return "";
	}
	}

	
}
