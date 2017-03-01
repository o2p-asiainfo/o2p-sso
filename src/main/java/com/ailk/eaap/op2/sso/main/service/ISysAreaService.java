package com.ailk.eaap.op2.sso.main.service;

import java.util.List;

import com.ailk.eaap.op2.sso.main.model.SysArea;
/**
 * 
 * @author zhaobl
 *
 */
public interface ISysAreaService {
	/**
	 * 查询全部地区信息
	 * @return
	 */
	public List<SysArea> queryAll(); 
}
