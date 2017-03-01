package com.ailk.eaap.op2.sso.main.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;

public class SysLogLoginExample extends BaseExampleObject {

    public SysLogLoginExample() {
        super();
    }
    
    private Integer sysLoginId;
	private Integer sysPersonId;
	private String name;
	private String endtime;
	private String starttime;

	private String cardNumber;
	
    public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public SysLogLoginExample setCriteriaWithSingleValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithSingleValue.add(map);
        return this;
    }

    public SysLogLoginExample setCriteriaWithOrValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithOrValue.add(map);
        return this;
    }

    public SysLogLoginExample setCriteriaWithExistsValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithExistsValue.add(map);
        return this;
    }

    public SysLogLoginExample setCriteriaWithInValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithInValue.add(map);
        return this;
    }

    public SysLogLoginExample andSysLoginIdIsNull() {
        criteriaWithoutValue.add("t.SYS_LOGIN_ID is null");
        return this;
    }

    public SysLogLoginExample andSysLoginIdIsNotNull() {
        criteriaWithoutValue.add("t.SYS_LOGIN_ID is not null");
        return this;
    }

    public SysLogLoginExample andSysLoginIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID =", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID <>", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID >", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID >=", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID <", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOGIN_ID <=", value, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID =", value );
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID <>", value );
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID >", value );
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID >=", value );
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID <", value );
        }
        return this;
    }

    public SysLogLoginExample orSysLoginIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOGIN_ID <=", value );
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_LOGIN_ID in", values, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_LOGIN_ID not in", values, "sysLoginId");
        }
        return this;
    }

    public SysLogLoginExample andSysLoginIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_LOGIN_ID between", value1, value2, "sysLoginId");
        return this;
    }

    public SysLogLoginExample andSysLoginIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_LOGIN_ID not between", value1, value2, "sysLoginId");
        return this;
    }

    public SysLogLoginExample andSysPersonIdIsNull() {
        criteriaWithoutValue.add("t.SYS_PERSON_ID is null");
        return this;
    }

    public SysLogLoginExample andSysPersonIdIsNotNull() {
        criteriaWithoutValue.add("t.SYS_PERSON_ID is not null");
        return this;
    }

    public SysLogLoginExample andSysPersonIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID =", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <>", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID >", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID >=", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <=", value, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID =", value );
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <>", value );
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID >", value );
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID >=", value );
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <", value );
        }
        return this;
    }

    public SysLogLoginExample orSysPersonIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <=", value );
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_PERSON_ID in", values, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_PERSON_ID not in", values, "sysPersonId");
        }
        return this;
    }

    public SysLogLoginExample andSysPersonIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_PERSON_ID between", value1, value2, "sysPersonId");
        return this;
    }

    public SysLogLoginExample andSysPersonIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_PERSON_ID not between", value1, value2, "sysPersonId");
        return this;
    }

    public SysLogLoginExample andLoginTimeIsNull() {
        criteriaWithoutValue.add("t.LOGIN_TIME is null");
        return this;
    }

    public SysLogLoginExample andLoginTimeIsNotNull() {
        criteriaWithoutValue.add("t.LOGIN_TIME is not null");
        return this;
    }

    public SysLogLoginExample andLoginTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME =", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME <>", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME >", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME >=", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME <", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_TIME <=", value, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME =", value );
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME <>", value );
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME >", value );
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME >=", value );
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME <", value );
        }
        return this;
    }

    public SysLogLoginExample orLoginTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_TIME <=", value );
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGIN_TIME in", values, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGIN_TIME not in", values, "loginTime");
        }
        return this;
    }

    public SysLogLoginExample andLoginTimeBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.LOGIN_TIME between", value1, value2, "loginTime");
        return this;
    }

    public SysLogLoginExample andLoginTimeNotBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.LOGIN_TIME not between", value1, value2, "loginTime");
        return this;
    }

    public SysLogLoginExample andLogoutTimeIsNull() {
        criteriaWithoutValue.add("t.LOGOUT_TIME is null");
        return this;
    }

    public SysLogLoginExample andLogoutTimeIsNotNull() {
        criteriaWithoutValue.add("t.LOGOUT_TIME is not null");
        return this;
    }

    public SysLogLoginExample andLogoutTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME =", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME <>", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME >", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME >=", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME <", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGOUT_TIME <=", value, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME =", value );
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME <>", value );
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME >", value );
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME >=", value );
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME <", value );
        }
        return this;
    }

    public SysLogLoginExample orLogoutTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGOUT_TIME <=", value );
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGOUT_TIME in", values, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGOUT_TIME not in", values, "logoutTime");
        }
        return this;
    }

    public SysLogLoginExample andLogoutTimeBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.LOGOUT_TIME between", value1, value2, "logoutTime");
        return this;
    }

    public SysLogLoginExample andLogoutTimeNotBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.LOGOUT_TIME not between", value1, value2, "logoutTime");
        return this;
    }

    public SysLogLoginExample andIpIsNull() {
        criteriaWithoutValue.add("t.IP is null");
        return this;
    }

    public SysLogLoginExample andIpIsNotNull() {
        criteriaWithoutValue.add("t.IP is not null");
        return this;
    }

    public SysLogLoginExample andIpEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP =", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP <>", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP >", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP >=", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP <", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP <=", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample orIpEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP =", value );
        }
        return this;
    }

    public SysLogLoginExample orIpNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP <>", value );
        }
        return this;
    }

    public SysLogLoginExample orIpGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP >", value );
        }
        return this;
    }

    public SysLogLoginExample orIpGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP >=", value );
        }
        return this;
    }

    public SysLogLoginExample orIpLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP <", value );
        }
        return this;
    }

    public SysLogLoginExample orIpLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP <=", value );
        }
        return this;
    }

    public SysLogLoginExample andIpLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP like", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.IP not like", value, "ip");
        }
        return this;
    }

    public SysLogLoginExample orIpLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP like", value );
        }
        return this;
    }

    public SysLogLoginExample orIpNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.IP not like", value );
        }
        return this;
    }

    public SysLogLoginExample andIpIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.IP in", values, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.IP not in", values, "ip");
        }
        return this;
    }

    public SysLogLoginExample andIpBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.IP between", value1, value2, "ip");
        return this;
    }

    public SysLogLoginExample andIpNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.IP not between", value1, value2, "ip");
        return this;
    }

	public Integer getSysPersonId() {
		return sysPersonId;
	}

	public void setSysPersonId(Integer sysPersonId) {
		this.sysPersonId = sysPersonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Integer getSysLoginId() {
		return sysLoginId;
	}

	public void setSysLoginId(Integer sysLoginId) {
		this.sysLoginId = sysLoginId;
	}
}