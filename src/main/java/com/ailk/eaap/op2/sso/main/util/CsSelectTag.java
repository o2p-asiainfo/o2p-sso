package com.ailk.eaap.op2.sso.main.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.SelectTag;
import com.opensymphony.xwork2.util.ValueStack;

public class CsSelectTag extends SelectTag {

	private static final long serialVersionUID = -8254388419429219363L;

	protected String dataType = null;

	protected String msg = null;

	protected String max = null;

	protected String min = null;

	protected String require = null;

	protected String to = null;

	protected String accept = null;

	protected String format = null;

	protected String regexp = null;

	protected String operator = null;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new CsSelect(stack, req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		CsSelect textField = ((CsSelect) component);
		textField.setAccept(accept);
		textField.setDataType(dataType);
		textField.setMsg(msg);
		textField.setFormat(format);
		textField.setMax(max);
		textField.setRequire(require);
		textField.setFormat(format);
		textField.setMin(min);
		textField.setTo(to);
		textField.setRegexp(regexp);
		textField.setOperator(operator);
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
