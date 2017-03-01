package com.ailk.eaap.op2.sso.main.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
public class UserDAOImpl implements UserDAO {
	
	private JdbcTemplate npsoaJdbcTemplate;
	
	private JdbcTemplate xrbJdbcTemplate;
	
/*	private static final String SEARCH_FLOW_DEF = "select busi_flowid,name" +
			" from npsoa.NPSOA_FLOW_DEF";
	
	private static final String SEARCH_MESSAGE = "select MSG,DESCRIPTOR " +
			" from xrb.ORIGINAL_MESSAGE_LOG " +
			" where XRB_EVENT_SEQ_ID = :eventId";

*/
	public void insertDeptUser(Long deptid,Long userid)
	{
		xrbJdbcTemplate.execute("insert into sys_dept_person values (seq_sys_dept_person.nextval,"+deptid+","+userid+")");
	}
	
	public int authCheck(Long sys_person_id)
	{
		int i =0 ;
		
		List<Map<String,Object>> result =  xrbJdbcTemplate.queryForList("select p.sys_person_id,count(r.sys_right_id) count"
										+	"	  from sys_person p left join sys_right r"
										+	"	 on p.sys_person_id = r.sys_person_id"
										+	"	 where p.sys_person_id = " + sys_person_id 
										+	"	group by p.sys_person_id  ");
		for(Map<String,Object> item : result)
		{
			if(item.get("count").toString().equals("0") || item.get("count").toString()=="0")
					{
				i= 1;
				break;
					}
			else
			{
				i= 2;
				break;
			}
			
		}
		return i;
	}
	
	public JdbcTemplate getNpsoaJdbcTemplate() {
		return npsoaJdbcTemplate;
	}

	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}

	public void setNpsoaJdbcTemplate(JdbcTemplate npsoaJdbcTemplate) {
		this.npsoaJdbcTemplate = npsoaJdbcTemplate;
	}

	public List<Map<String, Object>> getPageResult(String sql) {
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
		 
		return list;
	}

	public List<Map<String, Object>> getPersonId(String sql) {
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList(sql);
		 
		return list;
	}
	
	public int update(String sql) {
		int num = xrbJdbcTemplate.update(sql);
		return num;
	}
	
	
	public int getResultSize(String sql) {
		int count = xrbJdbcTemplate.queryForInt("select count(*) from (" + sql + ")");
		return count;
	}
	
	public void updatePassword(SYSPERSON bean) {
		xrbJdbcTemplate.update("update sys_person t set t.pwd_last_up_time=sysdate, t.password = '"+bean.getPassword()+ 
				"',t.last_pwd = '"+bean.getLastPassword()+"' where t.sys_person_id =  "+bean.getSysPersonId() );
	}

	public List<KeyValueBean> selectPassword(String sys_person_id) {
		List<KeyValueBean> list = null;
		try {
			List<Map<String, Object>> result = xrbJdbcTemplate.queryForList("select password,name" +
					" from sys_person " +
					" where sys_person_id="+sys_person_id );
			if(result != null && result.size() > 0){
				list = new ArrayList<KeyValueBean>();
				for(Map<String, Object> item : result){
					KeyValueBean bean = new KeyValueBean();
					bean.setKey(ConvertUtil.nullToSpace(item.get("password")));
					bean.setValue(ConvertUtil.nullToSpace(item.get("name")));
					list.add(bean);
				}
			}
		} catch (Exception e) {
		}
		return list;
	}
	 
	public List<Map<String, Object>> getPass(String sys_person_id){
		List<Map<String, Object>> list = xrbJdbcTemplate.queryForList("select name,password,last_pwd" +
				" from sys_person " +
				" where sys_person_id="+sys_person_id );
		return list;
	}
 	/*public List<SYSPERSON> getFlowList() {
		List<SYSPERSON> result = null;
		List<Map<String, Object>> list = npsoaJdbcTemplate.queryForList(SEARCH_FLOW_DEF);
		if(list != null && list.size() > 0){
			result = new ArrayList<SYSPERSON>();
			for(Map<String, Object> item : list){
				SYSPERSON bean = new SYSPERSON();
				bean.setFlowCd(ConvertUtil.nullToSpace(item.get("busi_flowid")));
				bean.setFlowNm(ConvertUtil.nullToSpace(item.get("name")));
				result.add(bean);
			}
		}
		return result;
	}*/

	 
 
}
