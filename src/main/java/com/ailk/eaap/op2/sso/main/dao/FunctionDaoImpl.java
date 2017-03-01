package com.ailk.eaap.op2.sso.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ailk.eaap.op2.sso.main.model.TreeModel;


public class FunctionDaoImpl extends SqlMapClientDaoSupport implements FunctionDao {
    private JdbcTemplate npsoaJdbcTemplate;
	
	private JdbcTemplate xrbJdbcTemplate;
	public List<Map<String, Object>> getAllFunction(String sql) {
		   
		 List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql) ;
		    
		return list;
	}
	public int getResultSize(String sql) {
		int count = xrbJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
		return count;
	}
	
	public List getFuntionByfuntionaname(String funtionname)
	{
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(funtionname);
		return list;
	}
	public boolean checkHaveParentId(String parentId){
		 String sqlCount = "select  count(distinct function_id)  "
				+"from  sys_function where function_id = "+parentId
				+"  and sys_status_id = 1" ;
		int count = xrbJdbcTemplate.queryForInt(sqlCount);
		return count>=1 ;
   }
	
	public List<Map<String, Object>> getPageResult(String sql) {
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
		 
		return list;
	}
	
	public int update(String sql) {
		int num = xrbJdbcTemplate.update(sql);
		return num;
	}
	//--------add by Guangshu.Mi-------
	public List<TreeModel> queryMainTreeModel(String personId,String lan){
		return this.queryTreeModel("1", personId, lan);
	}
	public List<TreeModel> queryTreeModel(String parentId,String personId,String lan){
			String queryTreeModelSql;
			if("zh".equals(lan)){
				queryTreeModelSql = "select  function_id,function_name,url  from sys_function where business_system_id = 1 and parent_function_id=?  and function_id in (select distinct function_id  from SYS_RIGHT  where sys_person_id =? and sys_role_type_id = 1)  order by shownum";
			}else{
				queryTreeModelSql = "select  function_id,function_name||\'_en\' function_name,url  from sys_function where business_system_id = 1 and parent_function_id=?  and function_id in (select distinct function_id  from SYS_RIGHT  where sys_person_id =? and sys_role_type_id = 1)  order by shownum";
			}
			List<TreeModel> treeModels = xrbJdbcTemplate.query(queryTreeModelSql,new Object[]{parentId,personId},new TreeModelRowMapper());
			for(TreeModel treeModel:treeModels){
				if(treeModel.isLeaf()){
					treeModel.setQtitle(treeModel.getQtitle()+"&info="+personId);
				}
			}
		return treeModels;
	}

	class TreeModelRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int i) throws SQLException {
			TreeModel tm = new TreeModel();
			tm.setId(rs.getString("function_id"));
			tm.setText(rs.getString("function_name"));
			tm.setQtitle(rs.getString("url"));
			tm.setLeaf(true);
			return tm;
		}
	}
	//-------end by Guangshu.Mi-----
	
	public FunctionDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JdbcTemplate getNpsoaJdbcTemplate() {
		return npsoaJdbcTemplate;
	}

	public void setNpsoaJdbcTemplate(JdbcTemplate npsoaJdbcTemplate) {
		this.npsoaJdbcTemplate = npsoaJdbcTemplate;
	}

	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}
	public List<Map<String, Object>> getBusinessSystem(String sql) {
		  
		 List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql) ;
		    
		return list;
	}

}
