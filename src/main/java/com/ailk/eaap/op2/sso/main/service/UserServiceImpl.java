package com.ailk.eaap.op2.sso.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.sso.main.dao.UserDAO;
import com.ailk.eaap.op2.sso.main.model.KeyValueBean;
import com.ailk.eaap.op2.sso.main.model.SYSPERSON;
import com.ailk.eaap.op2.sso.main.util.ConvertUtil;
import com.ailk.eaap.op2.sso.main.util.SqlUtil;
import com.opensymphony.xwork2.ActionContext;

public class UserServiceImpl implements IUserService {

	private UserDAO userDAO;
	private JdbcTemplate xrbJdbcTemplate;
	HttpServletRequest request;
	
	public UserServiceImpl(){
		
	}
	public int authCheck(Long sys_person_id) {
		return userDAO.authCheck(sys_person_id);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void doSearchFlow(SYSPERSON bean) {
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select distinct person.SYS_PERSON_ID , person.NAME ,person.MOBILE ,dept.DEPT_ID,"
				+ "       person.PASSWORD ,person.SYS_STATUS_ID ,person.EMAIL ,person.SYS_CARD_TYPE_ID ,person.CARD_NUMBER,dp.sys_dept_person_id,"
				+ "		 person.SYS_USER_TYPE ,person.SYS_ID_TYPE_ID ,dept.DEPT_NAME ,card.sys_card_type_name ,c.SYS_CUSTOMER_NAME ,it.SYS_ID_TYPE_NAME"
				+ "  from sys_person person, sys_dept dept ,sys_dept_person dp ,SYS_CARD_TYPE card ,SYS_CUSTOMER c ,sys_id_type it"
				+ " where person.sys_card_type_id = card.sys_card_type_id and it.SYS_ID_TYPE_ID in (2,3)"
				+ " and person.sys_person_id = dp.sys_person_id and dp.dept_id = dept.dept_id "
				+ " and c.sys_customer_id = dept.sys_customer_id and person.SYS_ID_TYPE_ID = it.SYS_ID_TYPE_ID";

		if ("notadmin".equals((String) ActionContext.getContext().getSession()
				.get("userright"))) {
			sql += " and dept.dept_id ='"
					+ ((SYSPERSON) ActionContext.getContext().getSession().get(
							"userbean")).getDeptId() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getSysPersonId())) {

			sql += " and person.SYS_PERSON_ID ='" + bean.getSysPersonId() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getPassword())) {
			sql += " and person.password ='" + bean.getPassword() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getEmail())) {
			sql += " and person.email ='" + bean.getEmail() + "'";
		}
		if (!ConvertUtil.isEmpty(bean.getName())) {
			sql += " and person.name like '%" + bean.getName() + "%'";
		}
		if (!ConvertUtil.isEmpty(bean.getCustomerName())) {
			sql += " and cus.SYS_CUSTOMER_NAME ='" + bean.getCustomerName()
					+ "'";
		}
		if (!ConvertUtil.isEmpty(bean.getDeptId())) {
			sql += " and dept.DEPT_ID ='" + bean.getDeptId() + "'";
		}
		if (!ConvertUtil.isEmpty(bean.getMobile())) {

			sql += " and person.mobile ='" + bean.getMobile() + "'";
		}
		Integer size = userDAO.getResultSize(sql);
		if (size > 0) {
			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
			List<Map<String, Object>> pageResult = userDAO
					.getPageResult(pageSql);
			this.convertResult(pageResult, list);
			bean.getPageBean().setTotalRecord(size.toString());
			String totalPage = SqlUtil.getPageSize(size,
					Integer.parseInt(bean.getPageBean().getPageCount()))
					.toString();
			bean.getPageBean().setTotalPage(totalPage);
		}
		bean.setSearchResult(list);
	}

	// 3-17 增加 数据域判断 获取部门下 员工信息方法
	public void doSearchFlow(SYSPERSON bean, String idType, String cusName) {
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select distinct person.SYS_PERSON_ID , person.NAME ,person.MOBILE ,dept.DEPT_ID,to_char(person.STATE_DATE,'yyyy-mm-dd hh24:mi:ss') STATE_DATE,"
				+ " person.PASSWORD ,person.SYS_STATUS_ID ,person.EMAIL ,person.SYS_CARD_TYPE_ID ,person.CARD_NUMBER,dp.sys_dept_person_id,"
				+ " person.SYS_USER_TYPE ,person.SYS_ID_TYPE_ID ,dept.DEPT_NAME ,card.sys_card_type_name ,c.SYS_CUSTOMER_ID,c.SYS_CUSTOMER_NAME ,it.SYS_ID_TYPE_NAME"
				+ " from sys_person person, sys_dept dept ,sys_dept_person dp ,SYS_CARD_TYPE card ,SYS_CUSTOMER c ,sys_id_type it"
				+ " where person.sys_card_type_id = card.sys_card_type_id and it.SYS_ID_TYPE_ID in (2,3)"
				+ " and person.sys_person_id = dp.sys_person_id and dp.dept_id = dept.dept_id "
				+ " and c.sys_customer_id = dept.sys_customer_id and person.SYS_ID_TYPE_ID = it.SYS_ID_TYPE_ID";

		if ((idType.equals("1"))
				|| (idType.equals("2") && cusName.equals("电信集团"))) {
			sql += " and 1=1";
		}
		if (idType.equals("2") && !cusName.equals("电信集团")) {
			sql += " and c.sys_customer_name = '" + cusName + "'";
		}
		if ("notadmin".equals((String) ActionContext.getContext().getSession()
				.get("userright"))) {
			sql += " and dept.dept_id ='"
					+ ((SYSPERSON) ActionContext.getContext().getSession().get(
							"userbean")).getDeptId() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getSysPersonId())) {

			sql += " and person.SYS_PERSON_ID ='" + bean.getSysPersonId() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getPassword())) {
			sql += " and person.password ='" + bean.getPassword() + "'";
		}

		if (!ConvertUtil.isEmpty(bean.getEmail())) {
			sql += " and person.email ='" + bean.getEmail() + "'";
		}
		if (!ConvertUtil.isEmpty(bean.getName())) {
			sql += " and person.name like '%" + bean.getName() + "%'";
		}
		if (!ConvertUtil.isEmpty(bean.getCustomerName())) {
			sql += " and c.SYS_CUSTOMER_NAME ='" + bean.getCustomerName() + "'";
		}
		if (!ConvertUtil.isEmpty(bean.getDeptId())) {
			sql += " and dept.DEPT_ID ='" + bean.getDeptId() + "'";
		}
		if (!ConvertUtil.isEmpty(bean.getMobile())) {

			sql += " and person.mobile ='" + bean.getMobile() + "'";
		}
		Integer size = userDAO.getResultSize(sql);
		if (size > 0) {
			String pageSql = SqlUtil.buildPageSql(sql, bean.getPageBean());
			List<Map<String, Object>> pageResult = userDAO
					.getPageResult(pageSql);
			this.convertResult(pageResult, list);
			bean.getPageBean().setTotalRecord(size.toString());
			String totalPage = SqlUtil.getPageSize(size,
					Integer.parseInt(bean.getPageBean().getPageCount()))
					.toString();
			bean.getPageBean().setTotalPage(totalPage);
		}
		bean.setSearchResult(list);
	}

	public void insertDeptUser(Long deptid, Long userid) {
		userDAO.insertDeptUser(deptid, userid);
	}

	public void LoginCheck(SYSPERSON bean) {
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select person.SYS_PERSON_ID , person.NAME ,person.MOBILE , "
				+ "       person.PASSWORD ,person.SYS_STATUS_ID ,person.EMAIL ,person.SYS_CARD_TYPE_ID ,person.CARD_NUMBER,"
				+ "		 person.SYS_USER_TYPE , dept.DEPT_NAME ,c.sys_customer_id ,c.sys_customer_name,person.SYS_ID_TYPE_ID,round(to_number(sysdate-pwd_last_up_time)*24*60) PWD_LAST_TIME"
				+ "  from sys_person person, sys_dept dept,sys_dept_person dp, sys_customer c "
				+ " where person.sys_person_id = dp.sys_person_id and dp.dept_id = dept.dept_id and dept.sys_customer_id = c.sys_customer_id";
		if (!ConvertUtil.isEmpty(bean.getCardNumber())) {

			// sql += " and person.SYS_PERSON_ID ='"+bean.getSysPersonId()+"'" ;
			sql += " and person.CARD_NUMBER ='" + bean.getCardNumber() + "'";
		}

		List<Map<String, Object>> pageResult = userDAO.getPageResult(sql);
		this.convertResult(pageResult, list);
		bean.setSearchResult(list);
	}

	private void convertResult(List<Map<String, Object>> result,
			List<SYSPERSON> list) {
		for (Map<String, Object> item : result) {
			SYSPERSON bean = new SYSPERSON();

			bean.setSysPersonId(ConvertUtil.nullToSpace(item
					.get("SYS_PERSON_ID")));
			if (!ConvertUtil.nullToSpace(item.get("SYS_PERSON_ID")).equals("")) {
				bean.setIsauth(""
						+ userDAO.authCheck(Long.valueOf(item.get(
								"SYS_PERSON_ID").toString())));

				int num = xrbJdbcTemplate
						.queryForInt("select count(*) from sys_right where SYS_PERSON_ID= "
								+ item.get("SYS_PERSON_ID"));
				if (num > 0) {
					bean.setIsauth("1");
				} else {
					bean.setIsauth("0");
				}
			}
			bean.setName(ConvertUtil.nullToSpace(item.get("NAME")));
			bean.setMobile(ConvertUtil.nullToSpace(item.get("MOBILE")));
			// 3-8
			bean.setSysCustomerId(ConvertUtil.nullToSpace(item
					.get("SYS_CUSTOMER_ID")));
			bean.setCustomerName(ConvertUtil.nullToSpace(item
					.get("SYS_CUSTOMER_NAME")));
			bean.setDeptId(ConvertUtil.nullToSpace(item.get("DEPT_ID")));
			bean.setPassword(ConvertUtil.nullToSpace(item.get("PASSWORD")));
			bean.setLastPassword(ConvertUtil.nullToSpace(item.get("LAST_PWD")));
			bean.setSysStatusId(ConvertUtil.nullToSpace(item
					.get("SYS_STATUS_ID")));

			bean.setDeptpersonid(ConvertUtil.nullToSpace(item
					.get("sys_dept_person_id")));

			bean.setEmail(ConvertUtil.nullToSpace(item.get("EMAIL")));
			bean.setSysCardTypeId(ConvertUtil.nullToSpace(item
					.get("SYS_CARD_TYPE_ID")));
			bean.setPassword(ConvertUtil.nullToSpace(item.get("password")));
			// alert by wanggm 2010-02-02
			bean.setSysIdTypeId(ConvertUtil.nullToSpace(item
					.get("SYS_ID_TYPE_ID")));
			bean.setSysIdTypeName(ConvertUtil.nullToSpace(item
					.get("SYS_ID_TYPE_NAME")));
			bean
					.setCardNumber(ConvertUtil.nullToSpace(item
							.get("CARD_NUMBER")));
			bean.setSysCardTypeName(ConvertUtil.nullToSpace(item
					.get("sys_card_type_name")));
			bean.setSysUserType(ConvertUtil.nullToSpace(item
					.get("SYS_USER_TYPE")));
			bean.setDeptName(ConvertUtil.nullToSpace(item.get("DEPT_NAME")));
			bean.setParentDeptId(ConvertUtil.nullToSpace(item
					.get("PARENT_DEPT_ID")));
			bean.setSynStateTime(ConvertUtil.nullToSpace(item
					.get("STATE_DATE")));
			bean.setPwdLastUpTime(ConvertUtil.nullToSpace(item
					.get("PWD_LAST_UP_TIME")));
			bean.setPwdLastTime(ConvertUtil.nullToSpace(item
					.get("PWD_LAST_TIME")));
			//用户是否需要填验证码
			bean.setMsgCheckCode(ConvertUtil.nullToSpace(item
					.get("SYS_USER_TYPE")));
			list.add(bean);
		}
	}

	public int updatePerson(SYSPERSON sysperson, String type) {
		String sql = "";
		if ("delete".equals(type)) {
			// int num = xrbJdbcTemplate.queryForInt("select count(*) from
			// SYS_DEPT_PERSON where SYS_PERSON_ID= " +
			// sysperson.getSysPersonId());

			int num = xrbJdbcTemplate
					.queryForInt("select count(p.sys_person_id) from  sys_person p ,sys_dept_person dp2 where "
							+ "			p.sys_person_id = dp2.sys_person_id "
							+ "			and p.sys_person_id =  (select dp.sys_person_id from sys_dept_person dp where dp.sys_dept_person_id = "
							+ sysperson.getDeptpersonid() + ")");

			if (num == 1) {
				sql = "delete from sys_person where SYS_PERSON_ID ="
						+ "(select dp.sys_person_id from sys_dept_person dp where dp.sys_dept_person_id ="
						+ sysperson.getDeptpersonid() + " )";
				xrbJdbcTemplate
						.update("delete from SYS_DEPT_PERSON where SYS_DEPT_PERSON_ID="
								+ sysperson.getDeptpersonid());
			} else {
				sql = "delete from SYS_DEPT_PERSON where SYS_DEPT_PERSON_ID="
						+ sysperson.getDeptpersonid();
			}
			userDAO.update(sql);
		} else if ("update".equals(type)
				&& !ConvertUtil.isEmpty(sysperson.getCardNumber())) {
			int t = userDAO
					.getResultSize("select * from  sys_person t where t.card_number = '"
							+ sysperson.getCardNumber()
							+ "' and t.SYS_PERSON_ID != "
							+ sysperson.getSysPersonId());
			if (t >= 1) {
				return 2;
			} else {
				sql = "update sys_person" + "   set sys_person_id = "
						+ sysperson.getSysPersonId();
				if (!ConvertUtil.isEmpty(sysperson.getName())) {
					sql += " ,  name ='" + sysperson.getName() + "'";
				}
				if (!ConvertUtil.isEmpty(sysperson.getEmail())) {
					sql += " , email ='" + sysperson.getEmail() + "'";
				}
				if (!ConvertUtil.isEmpty(sysperson.getMobile())) {
					sql += " , mobile = '" + sysperson.getMobile() + "'";
				}
				/***************************************************************
				 * if(!ConvertUtil.isEmpty(sysperson.getDeptId())) { sql += " ,
				 * DEPT_ID ='"+sysperson.getDeptId()+"'" ; }
				 **************************************************************/
				//密码不能修改
//				if (!ConvertUtil.isEmpty(sysperson.getPassword())) {
//					sql += " , password ='" + sysperson.getPassword() + "'";
//				}
				if (!ConvertUtil.isEmpty(sysperson.getCardNumber())) {
					sql += " , card_number ='" + sysperson.getCardNumber()
							+ "'";
				}
				if (!ConvertUtil.isEmpty(sysperson.getSysCardTypeId())) {
					sql += " , sys_card_type_id ='"
							+ sysperson.getSysCardTypeId() + "'";
				}
				if (!ConvertUtil.isEmpty(sysperson.getSysIdTypeId())) {
					sql += " , SYS_ID_TYPE_ID ='" + sysperson.getSysIdTypeId()
							+ "'";
				}
				if (!ConvertUtil.isEmpty(sysperson.getMsgCheckCode())) {
					sql += " , SYS_USER_TYPE ='" + sysperson.getMsgCheckCode()
							+ "'";
				}
				sql += " where sys_person_id =" + sysperson.getSysPersonId();

				userDAO.update(sql);
			}
		} else if ("add".equals(type)) {
			int t = userDAO
					.getResultSize("select * from  sys_person t where t.card_number = '"
							+ sysperson.getCardNumber() + "'");
			if (t >= 1) {
				return 1;
			} else {
				sql = "insert into sys_person" + "  (SYS_PERSON_ID,"
						+ "   NAME," + "   MOBILE," + "   PASSWORD,"
						+ "   sys_card_type_id," + "	  card_number,"
						+ "	  SYS_ID_TYPE_ID," + "   EMAIL,pwd_last_up_time,SYS_USER_TYPE)" + " values"
						+ " (seq_sys_person.nextval, '" + sysperson.getName()
						+ "','" + sysperson.getMobile() + "','"
						+ sysperson.getPassword() + "','"
						+ sysperson.getSysCardTypeId() + "','"
						+ sysperson.getCardNumber() + "','"
						+ sysperson.getSysIdTypeId() + "','"
						+ sysperson.getEmail() + "',sysdate,"+sysperson.getMsgCheckCode()+")";

				userDAO.update(sql);
			}
		}
		return 0;
	}

	// 1-12 根据 SYS_PERSON_ID 更新 SYS_DEPT_PERSON 方法
	public int updateDeptPerson(SYSPERSON sysperson, String customerId,
			String type) {
		String sql = "";
		if ("delete".equals(type)) {
			sql = "delete from SYS_DEPT_PERSON where SYS_PERSON_ID="
					+ sysperson.getSysPersonId() + " and DeptId ="
					+ sysperson.getDeptId();
		} else if ("update".equals(type)
				&& !ConvertUtil.isEmpty(sysperson.getSysPersonId())) {
			sql = "update SYS_DEPT_PERSON " + " set DEPT_ID = "
					+ sysperson.getDeptId();
			// +" set sys_person_id = "+sysperson.getSysPersonId() ;
			/**
			 * if(!ConvertUtil.isEmpty(sysperson.getDeptId())) { sql += "
			 * DEPT_ID ='"+sysperson.getDeptId()+"'" ; }
			 */
			sql += " where sys_person_id =" + sysperson.getSysPersonId();
		} else if ("add".equals(type)) {
			List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
			List<SYSPERSON> list = new ArrayList<SYSPERSON>();
			String sql1 = "select SYS_PERSON_ID "
					+
					// " from xrb.SYS_PERSON where name = '" +
					// sysperson.getName() +
					" from SYS_PERSON  where name = '" + sysperson.getName()
					+ "' and CARD_NUMBER ='" + sysperson.getCardNumber() + "'";
			Result = userDAO.getPageResult(sql1);
			this.convertResult(Result, list);
			List<SYSPERSON> personId = list;
			sql = "insert into SYS_DEPT_PERSON" + "  (SYS_DEPT_PERSON_ID,"
					+ "   DEPT_ID," + "   SYS_PERSON_ID )" + "  values"
					+ " (SEQ_SYS_DEPT_PERSON.nextval, '"
					+ sysperson.getDeptId() + "','"
					+ personId.get(0).getSysPersonId() + "')";
		}

		return userDAO.update(sql);
	}

	public List<SYSPERSON> getDeptList() {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select  dept_id , DEPT_NAME from sys_dept ";
		if ("notadmin".equals((String) ActionContext.getContext().getSession()
				.get("userright"))) {
			SYSPERSON sysperson = (SYSPERSON) ActionContext.getContext()
					.getSession().get("userbean");
			map.put("dept_id", ConvertUtil.nullToSpace(sysperson.getDeptId()));
			map.put("DEPT_NAME", ConvertUtil.nullToSpace(sysperson
					.getDeptName()));
			Result.add(map);
		} else {
			Result = userDAO.getPageResult(sql);
		}

		this.convertResult(Result, list);
		return list;
	}

	// 3-18 增加 数据域 判断 获取 部门信息 方法
	public List<SYSPERSON> getDeptList(Long cusId) {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select  dept_id , DEPT_NAME from sys_dept where SYS_CUSTOMER_ID = "
				+ cusId;
		if ("notadmin".equals((String) ActionContext.getContext().getSession()
				.get("userright"))) {
			SYSPERSON sysperson = (SYSPERSON) ActionContext.getContext()
					.getSession().get("userbean");
			map.put("dept_id", ConvertUtil.nullToSpace(sysperson.getDeptId()));
			map.put("DEPT_NAME", ConvertUtil.nullToSpace(sysperson
					.getDeptName()));
			Result.add(map);
		} else {
			Result = userDAO.getPageResult(sql);
		}

		this.convertResult(Result, list);
		return list;
	}

	public List<SYSPERSON> getCardList() {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select  sys_card_type_name , sys_card_type_id from SYS_CARD_TYPE ";
		Result = userDAO.getPageResult(sql);
		this.convertResult(Result, list);
		return list;
	}

	// alert by wanggm 2010-02-02
	public List<SYSPERSON> getIdTypeList() {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select  SYS_ID_TYPE_ID , SYS_ID_TYPE_NAME from SYS_ID_TYPE where SYS_ID_TYPE_ID in (2,3) ";
		Result = userDAO.getPageResult(sql);
		this.convertResult(Result, list);
		return list;
	}

	public List<SYSPERSON> getUnAuthedDeptList() {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		String sql = "select * from sys_dept d where d.dept_id not in (select distinct dr.dept_id from sys_dept_role dr)";
		if ("notadmin".equals((String) ActionContext.getContext().getSession()
				.get("userright"))) {
			SYSPERSON sysperson = (SYSPERSON) ActionContext.getContext()
					.getSession().get("userbean");
			map.put("dept_id", ConvertUtil.nullToSpace(sysperson.getDeptId()));
			map.put("DEPT_NAME", ConvertUtil.nullToSpace(sysperson
					.getDeptName()));
			Result.add(map);
		} else {
			Result = userDAO.getPageResult(sql);
		}

		this.convertResult(Result, list);
		return list;
	}

	public void updatePassword(SYSPERSON bean) {
		userDAO.updatePassword(bean);
	}

	public void updatePasswordById(SYSPERSON bean) {
		userDAO.updatePassword(bean);
	}

	public String doloadPassword(String sys_person_id) {
		String jsonObj = "";
		StringBuffer temp = new StringBuffer("");
		List<KeyValueBean> pass = new ArrayList<KeyValueBean>();
		pass = userDAO.selectPassword(sys_person_id);
		temp = temp.append("{pass:[");
		temp = temp.append(ConvertUtil.convertValuleListToJsonObj(pass));
		temp = temp.append("]}");
		jsonObj = temp.toString();
		return jsonObj;
	}

	public List<SYSPERSON> getPassword(String sys_person_id) {
		List<Map<String, Object>> Result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SYSPERSON> list = new ArrayList<SYSPERSON>();
		Result = userDAO.getPass(sys_person_id);
		this.convertResult(Result, list);
		return list;
	}

	public JdbcTemplate getXrbJdbcTemplate() {
		return xrbJdbcTemplate;
	}

	public void setXrbJdbcTemplate(JdbcTemplate xrbJdbcTemplate) {
		this.xrbJdbcTemplate = xrbJdbcTemplate;
	}
}
