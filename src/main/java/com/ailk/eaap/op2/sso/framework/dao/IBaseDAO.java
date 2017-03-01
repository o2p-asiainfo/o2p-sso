package com.ailk.eaap.op2.sso.framework.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;
import com.linkage.rainbow.ui.components.page.Page;

public interface IBaseDAO {
	void insert(Object object);

	int updateByPrimaryKey(Object object);

	int updateByPrimaryKeySelective(Object object);

	List selectByExample(BaseExampleObject example);

	Page selectPageByExample(BaseExampleObject example);

	Integer selectCountByExample(BaseExampleObject example);

	Object selectByPrimaryKey(Object object);

	int deleteByExample(BaseExampleObject example);
	
	public int getStatus(String status_val,String status_table,String layer_id,Integer layerval);  

	int deleteByPrimaryKey(Object object);

	public Date getSysDate();

	public int getSeq(String seqName);

	public void createTable(String tableName);

	public void addTablePk(String tableName, String pkcolumnName);

	public void createTable(String tableName, String pkcol);

	public void addTableColumn(String tableName, String colName,
			String colType, String nullAble);

	public void renameTableColumn(String tableName, String colName,
			String newcolName);

	public void modifyTableColumn(String tableName, String colName,
			String colType, String nullAble);

	public List selectDynamicTable(String tableName);

	public void insertDynamicTable(String tableName, List list);

	public void insertDynamicTable(String tableName, Map paramMap);

	public void updateDynamicTable(String tableName, Map paramMap, Map pk);
}