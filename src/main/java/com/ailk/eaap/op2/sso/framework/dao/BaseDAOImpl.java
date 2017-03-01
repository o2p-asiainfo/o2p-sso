package com.ailk.eaap.op2.sso.framework.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;
import com.linkage.rainbow.ui.components.page.Page;

public class BaseDAOImpl extends SqlMapClientDaoSupport implements IBaseDAO {
	public final Log log = LogFactory.getLog(getClass());

	public final String PK_KEY = "pk";

	public final String COLUMN_LIST = "columnList";

	public final String TABLE_NAME = "tableName";

	private final String BASE_NAMESPACE = "Base";

	public BaseDAOImpl() {
		super();
	}

	//@TableLog(pkName = "", tableName = "")
	public void insert(Object object) {
		super.getSqlMapClientTemplate().insert(
				object.getClass().getSimpleName() + ".insert", object);
	}

	public int deleteByExample(BaseExampleObject example) {
		String className = example.getClass().getSimpleName();
		int i = className.lastIndexOf("Example");
		if (i > -1) {
			className = className.substring(0, i);
		}
		return super.getSqlMapClientTemplate().delete(
				className + ".deleteByExample", example);
	}

	public int deleteByPrimaryKey(Object object) {
		return super.getSqlMapClientTemplate().delete(
				object.getClass().getSimpleName() + ".deleteByPrimaryKey",
				object);
	}

	public List selectByExample(BaseExampleObject example) {
		String className = example.getClass().getSimpleName();
		int i = className.lastIndexOf("Example");
		if (i > -1) {
			className = className.substring(0, i);
		}
		return super.getSqlMapClientTemplate().queryForList(
				className + ".selectByExample", example);
	}

	public Object selectByPrimaryKey(Object object) {
		return super.getSqlMapClientTemplate().queryForObject(
				object.getClass().getSimpleName() + ".selectByPrimaryKey",
				object);
	}

	public Integer selectCountByExample(BaseExampleObject example) {
		String className = example.getClass().getSimpleName();
		int i = className.lastIndexOf("Example");
		if (i > -1) {
			className = className.substring(0, i);
		}
		Integer total = (Integer) getSqlMapClientTemplate().queryForObject(
				className + ".selectCountByExample", example);
		return total;
	}

	public Page selectPageByExample(BaseExampleObject example) {
		String className = example.getClass().getSimpleName();
		int i = className.lastIndexOf("Example");
		if (i > -1) {
			className = className.substring(0, i);
		}
		List list = getSqlMapClientTemplate().queryForList(
				className + ".selectPageByExample", example);
		Integer total = (Integer) getSqlMapClientTemplate().queryForObject(
				className + ".selectCountByExample", example);
		return null;
//		return new PageImp(list, total, example.getPageNumber(), example
//				.getPageSize());
	}

	public int updateByPrimaryKey(Object object) {
		return super.getSqlMapClientTemplate().update(
				object.getClass().getSimpleName() + ".updateByPrimaryKey",
				object);
	}

	public int updateByPrimaryKeySelective(Object object) {
		return super.getSqlMapClientTemplate().update(
				object.getClass().getSimpleName()
						+ ".updateByPrimaryKeySelective", object);
	}

	public Date getSysDate() {
		Date date = (Date) getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".getSysDate");
		return date;
	}

	public int getSeq(String seqName) {
		Integer date = (Integer) getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".getSeq", seqName);
		return date;
	}
	
	public int getStatus(String status_val,String status_table, String layer_id,Integer layerval) {
		Map map = new HashMap();
		map.put("layer_id", layer_id);
		map.put("layerval", layerval);
		map.put("status_val",status_val);
		map.put("status_table",status_table);	
		Integer date = (Integer) getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".getStatus",map);
		return date;
	}

	public void createTable(String tableName) {
		getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".createTable", tableName);
	}

	public void createTable(String tableName, String pkcol) {
		this.createTable(tableName);
		this.addTablePk(tableName, pkcol);
	}

	public void addTableColumn(String tableName, String colName,
			String colType, String nullAble) {
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put("colName", colName);
		map.put("colType", colType);
		map.put("nullAble", nullAble);
		getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".addTableColumn", map);
	}

	public void renameTableColumn(String tableName, String colName,
			String newColName) {
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put("colName", colName);
		map.put("newColName", newColName);
		getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".renameTableColumn", map);
	}

	public void modifyTableColumn(String tableName, String colName,
			String colType, String nullAble) {
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put("colName", colName);
		map.put("colType", colType);
		map.put("nullAble", nullAble);
		getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".modifyTableColumn", map);
	}

	public void addTablePk(String tableName, String pkcol) {
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put("colName", pkcol);
		getSqlMapClientTemplate().queryForObject(
				BASE_NAMESPACE + ".addTablePk", map);
	}

	public List selectDynamicTable(String tableName) {
		List list = getSqlMapClientTemplate().queryForList(
				BASE_NAMESPACE + ".selectDynamicTable", tableName);
		return list;
	}


	public void insertDynamicTable(String tableName, List list) {
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put(COLUMN_LIST, list);
		getSqlMapClientTemplate().insert(
				BASE_NAMESPACE + ".insertDynamicTable", map);
	}

	public void insertDynamicTable(String tableName, Map paramMap) {
		List list = new ArrayList();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			Object value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value);
			list.add(map);
		}
		this.insertDynamicTable(tableName, list);
	}

	public void updateDynamicTable(String tableName, Map paramMap, Map pk) {
		List list = new ArrayList();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			Object value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value);
			list.add(map);
		}
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put(COLUMN_LIST, list);
		map.put(PK_KEY, pk);
		getSqlMapClientTemplate().update(
				BASE_NAMESPACE + ".updateDynamicTable", map);
	}

}