package com.ailk.eaap.op2.sso.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class SysCardTypeDaoImpl implements SysCardTypeDao 
{
	private JdbcTemplate xrbJdbcTemplate;
	
	public List<Map<String, Object>> getCardTypes() {
		List<Map<String, Object>> rows = xrbJdbcTemplate.queryForList("select * from sys_card_type ");   
		return rows;
	}

	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}

}
