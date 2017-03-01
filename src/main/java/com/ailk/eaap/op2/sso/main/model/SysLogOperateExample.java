package com.ailk.eaap.op2.sso.main.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;

public class SysLogOperateExample extends BaseExampleObject {

	private Integer sysPersonId;
	private String name;
	private String starttime;
	private String endtime;
	
	//2011-10-10添加
	private String cardNumber;
	private String className;
	private String propertyDesc;
	private String methodName = "查询";
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getPropertyDesc() {
		return propertyDesc;
	}

	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
    public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public SysLogOperateExample() {
        super();
    }

    public SysLogOperateExample setCriteriaWithSingleValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithSingleValue.add(map);
        return this;
    }

    public SysLogOperateExample setCriteriaWithOrValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithOrValue.add(map);
        return this;
    }

    public SysLogOperateExample setCriteriaWithExistsValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithExistsValue.add(map);
        return this;
    }

    public SysLogOperateExample setCriteriaWithInValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "value ����Ϊ�գ�");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithInValue.add(map);
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdIsNull() {
        criteriaWithoutValue.add("t.SYS_LOG_OPERATE_ID is null");
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdIsNotNull() {
        criteriaWithoutValue.add("t.SYS_LOG_OPERATE_ID is not null");
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID =", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID <>", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID >", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID >=", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID <", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_LOG_OPERATE_ID <=", value, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID =", value );
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID <>", value );
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID >", value );
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID >=", value );
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID <", value );
        }
        return this;
    }

    public SysLogOperateExample orSysLogOperateIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_LOG_OPERATE_ID <=", value );
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_LOG_OPERATE_ID in", values, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_LOG_OPERATE_ID not in", values, "sysLogOperateId");
        }
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_LOG_OPERATE_ID between", value1, value2, "sysLogOperateId");
        return this;
    }

    public SysLogOperateExample andSysLogOperateIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_LOG_OPERATE_ID not between", value1, value2, "sysLogOperateId");
        return this;
    }

    public SysLogOperateExample andSysPersonIdIsNull() {
        criteriaWithoutValue.add("t.SYS_PERSON_ID is null");
        return this;
    }

    public SysLogOperateExample andSysPersonIdIsNotNull() {
        criteriaWithoutValue.add("t.SYS_PERSON_ID is not null");
        return this;
    }

    public SysLogOperateExample andSysPersonIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID =", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <>", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID >", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID >=", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.SYS_PERSON_ID <=", value, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID =", value );
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <>", value );
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID >", value );
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID >=", value );
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <", value );
        }
        return this;
    }

    public SysLogOperateExample orSysPersonIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.SYS_PERSON_ID <=", value );
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_PERSON_ID in", values, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.SYS_PERSON_ID not in", values, "sysPersonId");
        }
        return this;
    }

    public SysLogOperateExample andSysPersonIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_PERSON_ID between", value1, value2, "sysPersonId");
        return this;
    }

    public SysLogOperateExample andSysPersonIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.SYS_PERSON_ID not between", value1, value2, "sysPersonId");
        return this;
    }

    public SysLogOperateExample andFunctionIdIsNull() {
        criteriaWithoutValue.add("t.FUNCTION_ID is null");
        return this;
    }

    public SysLogOperateExample andFunctionIdIsNotNull() {
        criteriaWithoutValue.add("t.FUNCTION_ID is not null");
        return this;
    }

    public SysLogOperateExample andFunctionIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID =", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID <>", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID >", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID >=", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID <", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.FUNCTION_ID <=", value, "functionId");
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID =", value );
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID <>", value );
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID >", value );
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID >=", value );
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID <", value );
        }
        return this;
    }

    public SysLogOperateExample orFunctionIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.FUNCTION_ID <=", value );
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.FUNCTION_ID in", values, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.FUNCTION_ID not in", values, "functionId");
        }
        return this;
    }

    public SysLogOperateExample andFunctionIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.FUNCTION_ID between", value1, value2, "functionId");
        return this;
    }

    public SysLogOperateExample andFunctionIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.FUNCTION_ID not between", value1, value2, "functionId");
        return this;
    }

    public SysLogOperateExample andClassNameIsNull() {
        criteriaWithoutValue.add("t.CLASS_NAME is null");
        return this;
    }

    public SysLogOperateExample andClassNameIsNotNull() {
        criteriaWithoutValue.add("t.CLASS_NAME is not null");
        return this;
    }

    public SysLogOperateExample andClassNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME =", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME <>", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME >", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME >=", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME <", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME <=", value, "className");
        }
        return this;
    }

    public SysLogOperateExample orClassNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME =", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME <>", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME >", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME >=", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME <", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME <=", value );
        }
        return this;
    }

    public SysLogOperateExample andClassNameLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME like", value, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CLASS_NAME not like", value, "className");
        }
        return this;
    }

    public SysLogOperateExample orClassNameLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME like", value );
        }
        return this;
    }

    public SysLogOperateExample orClassNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CLASS_NAME not like", value );
        }
        return this;
    }

    public SysLogOperateExample andClassNameIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.CLASS_NAME in", values, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.CLASS_NAME not in", values, "className");
        }
        return this;
    }

    public SysLogOperateExample andClassNameBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.CLASS_NAME between", value1, value2, "className");
        return this;
    }

    public SysLogOperateExample andClassNameNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.CLASS_NAME not between", value1, value2, "className");
        return this;
    }

    public SysLogOperateExample andMethodNameIsNull() {
        criteriaWithoutValue.add("t.METHOD_NAME is null");
        return this;
    }

    public SysLogOperateExample andMethodNameIsNotNull() {
        criteriaWithoutValue.add("t.METHOD_NAME is not null");
        return this;
    }

    public SysLogOperateExample andMethodNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME =", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME <>", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME >", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME >=", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME <", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME <=", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample orMethodNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME =", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME <>", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME >", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME >=", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME <", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME <=", value );
        }
        return this;
    }

    public SysLogOperateExample andMethodNameLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME like", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.METHOD_NAME not like", value, "methodName");
        }
        return this;
    }

    public SysLogOperateExample orMethodNameLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME like", value );
        }
        return this;
    }

    public SysLogOperateExample orMethodNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.METHOD_NAME not like", value );
        }
        return this;
    }

    public SysLogOperateExample andMethodNameIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.METHOD_NAME in", values, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.METHOD_NAME not in", values, "methodName");
        }
        return this;
    }

    public SysLogOperateExample andMethodNameBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.METHOD_NAME between", value1, value2, "methodName");
        return this;
    }

    public SysLogOperateExample andMethodNameNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.METHOD_NAME not between", value1, value2, "methodName");
        return this;
    }

    public SysLogOperateExample andParamNameIsNull() {
        criteriaWithoutValue.add("t.PARAM_NAME is null");
        return this;
    }

    public SysLogOperateExample andParamNameIsNotNull() {
        criteriaWithoutValue.add("t.PARAM_NAME is not null");
        return this;
    }

    public SysLogOperateExample andParamNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME =", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME <>", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME >", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME >=", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME <", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME <=", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample orParamNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME =", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME <>", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME >", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME >=", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME <", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME <=", value );
        }
        return this;
    }

    public SysLogOperateExample andParamNameLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME like", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PARAM_NAME not like", value, "paramName");
        }
        return this;
    }

    public SysLogOperateExample orParamNameLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME like", value );
        }
        return this;
    }

    public SysLogOperateExample orParamNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PARAM_NAME not like", value );
        }
        return this;
    }

    public SysLogOperateExample andParamNameIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PARAM_NAME in", values, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PARAM_NAME not in", values, "paramName");
        }
        return this;
    }

    public SysLogOperateExample andParamNameBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.PARAM_NAME between", value1, value2, "paramName");
        return this;
    }

    public SysLogOperateExample andParamNameNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.PARAM_NAME not between", value1, value2, "paramName");
        return this;
    }

    public SysLogOperateExample andOperateTimeIsNull() {
        criteriaWithoutValue.add("t.OPERATE_TIME is null");
        return this;
    }

    public SysLogOperateExample andOperateTimeIsNotNull() {
        criteriaWithoutValue.add("t.OPERATE_TIME is not null");
        return this;
    }

    public SysLogOperateExample andOperateTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME =", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME <>", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME >", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME >=", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME <", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.OPERATE_TIME <=", value, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME =", value );
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeNotEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME <>", value );
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeGreaterThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME >", value );
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeGreaterThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME >=", value );
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeLessThan(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME <", value );
        }
        return this;
    }

    public SysLogOperateExample orOperateTimeLessThanOrEqualTo(Date value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.OPERATE_TIME <=", value );
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.OPERATE_TIME in", values, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.OPERATE_TIME not in", values, "operateTime");
        }
        return this;
    }

    public SysLogOperateExample andOperateTimeBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.OPERATE_TIME between", value1, value2, "operateTime");
        return this;
    }

    public SysLogOperateExample andOperateTimeNotBetween(Date value1, Date value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between ��ѯ��ֵ����Ϊ��");
        }
        addCriterion("t.OPERATE_TIME not between", value1, value2, "operateTime");
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
}