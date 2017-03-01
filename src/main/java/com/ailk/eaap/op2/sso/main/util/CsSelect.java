package com.ailk.eaap.op2.sso.main.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Select;

import com.opensymphony.xwork2.util.ValueStack;

public class CsSelect extends Select {

	public CsSelect(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		super(arg0, arg1, arg2);
	}

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
	public void evaluateExtraParams() {
		super.evaluateExtraParams();
		if (dataType != null) {
			addParameter("dataType", dataType);
		}
		if (msg != null) {
			addParameter("msg", msg);
		}
		if (max != null) {
			addParameter("max", max);
		}
		if (min != null) {
			addParameter("min", min);
		}
		if (require != null) {
			addParameter("require", require);
		}
		if (to != null) {
			addParameter("to", to);
		}
		if (accept != null) {
			addParameter("accept", accept);
		}
		if (format != null) {
			addParameter("format", format);
		}
		if (regexp != null) {
			addParameter("regexp", regexp);
		}
		if (operator != null) {
			addParameter("operator", operator);
		}

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
