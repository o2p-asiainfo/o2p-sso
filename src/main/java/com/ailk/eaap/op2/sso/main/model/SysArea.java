package com.ailk.eaap.op2.sso.main.model;

/**
 * 地区表/省份表(SYS_AREA)
 * @author zhaobl
 *
 */
public class SysArea implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long sysAreaId;//区域编号
	private String sysAreaName;//区域名称
	private Long parentAreaId;//父区域编号

	public Long getSysAreaId() {
		return this.sysAreaId;
	}

	public void setSysAreaId(Long sysAreaId) {
		this.sysAreaId = sysAreaId;
	}

	public String getSysAreaName() {
		return this.sysAreaName;
	}

	public void setSysAreaName(String sysAreaName) {
		this.sysAreaName = sysAreaName;
	}

	public Long getParentAreaId() {
		return this.parentAreaId;
	}

	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

}