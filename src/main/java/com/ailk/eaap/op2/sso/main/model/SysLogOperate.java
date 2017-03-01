package com.ailk.eaap.op2.sso.main.model;

import java.io.Serializable;

/**
 * 操作日志
 * @author zhaobl
 *
 */
public class SysLogOperate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sysLogOperateId;

	private String sysPersonId;

	private String functionId;

	private String modelName;

	private String className;

	private String methodName;
	
	private String opType;
	
	private String opSum;
	
	private String opDesc;
	
	private String tableList;
	
	private String paramName;

	private String operateTime;

	private String sqlExpr;
	
	private Integer tenantId;

	public String getSysLogOperateId() {
		return sysLogOperateId;
	}

	public void setSysLogOperateId(String sysLogOperateId) {
		this.sysLogOperateId = sysLogOperateId;
	}

	public String getSysPersonId() {
		return sysPersonId;
	}

	public void setSysPersonId(String sysPersonId) {
		this.sysPersonId = sysPersonId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpSum() {
		return opSum;
	}

	public void setOpSum(String opSum) {
		this.opSum = opSum;
	}

	public String getOpDesc() {
		return opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String getTableList() {
		return tableList;
	}

	public void setTableList(String tableList) {
		this.tableList = tableList;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getSqlExpr() {
		return sqlExpr;
	}

	public void setSqlExpr(String sqlExpr) {
		this.sqlExpr = sqlExpr;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

}