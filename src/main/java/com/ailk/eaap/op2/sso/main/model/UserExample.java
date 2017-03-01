package com.ailk.eaap.op2.sso.main.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;

public class UserExample extends BaseExampleObject {

    public UserExample() {
        super();
    }

    public UserExample setCriteriaWithSingleValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithSingleValue.add(map);
        return this;
    }

    public UserExample setCriteriaWithOrValue(String condition, Object value) {
        if(value==null){
            throw new RuntimeException( "");
        }
        Map map = new HashMap();
        map.put("condition",condition);
        map.put("value",value);
        criteriaWithOrValue.add(map);
        return this;
    }

    public UserExample setCriteriaWithExistsValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithExistsValue.add(map);
        return this;
    }

    public UserExample setCriteriaWithInValue(String iql, Object value) {
        if(value==null){
            throw new RuntimeException( "");
        }
        Map map = new HashMap();
        map.put("condition",iql);
        map.put("value",value);
        criteriaWithInValue.add(map);
        return this;
    }

    public UserExample andUserIdIsNull() {
        criteriaWithoutValue.add("t.USER_ID is null");
        return this;
    }

    public UserExample andUserIdIsNotNull() {
        criteriaWithoutValue.add("t.USER_ID is not null");
        return this;
    }

    public UserExample andUserIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID =", value, "userId");
        }
        return this;
    }

    public UserExample andUserIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID <>", value, "userId");
        }
        return this;
    }

    public UserExample andUserIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID >", value, "userId");
        }
        return this;
    }

    public UserExample andUserIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID >=", value, "userId");
        }
        return this;
    }

    public UserExample andUserIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID <", value, "userId");
        }
        return this;
    }

    public UserExample andUserIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.USER_ID <=", value, "userId");
        }
        return this;
    }

    public UserExample orUserIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID =", value );
        }
        return this;
    }

    public UserExample orUserIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID <>", value );
        }
        return this;
    }

    public UserExample orUserIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID >", value );
        }
        return this;
    }

    public UserExample orUserIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID >=", value );
        }
        return this;
    }

    public UserExample orUserIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID <", value );
        }
        return this;
    }

    public UserExample orUserIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.USER_ID <=", value );
        }
        return this;
    }

    public UserExample andUserIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.USER_ID in", values, "userId");
        }
        return this;
    }

    public UserExample andUserIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.USER_ID not in", values, "userId");
        }
        return this;
    }

    public UserExample andUserIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.USER_ID between", value1, value2, "userId");
        return this;
    }

    public UserExample andUserIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.USER_ID not between", value1, value2, "userId");
        return this;
    }

    public UserExample andRoleIdIsNull() {
        criteriaWithoutValue.add("t.ROLE_ID is null");
        return this;
    }

    public UserExample andRoleIdIsNotNull() {
        criteriaWithoutValue.add("t.ROLE_ID is not null");
        return this;
    }

    public UserExample andRoleIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID =", value, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID <>", value, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID >", value, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID >=", value, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID <", value, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ROLE_ID <=", value, "roleId");
        }
        return this;
    }

    public UserExample orRoleIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID =", value );
        }
        return this;
    }

    public UserExample orRoleIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID <>", value );
        }
        return this;
    }

    public UserExample orRoleIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID >", value );
        }
        return this;
    }

    public UserExample orRoleIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID >=", value );
        }
        return this;
    }

    public UserExample orRoleIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID <", value );
        }
        return this;
    }

    public UserExample orRoleIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ROLE_ID <=", value );
        }
        return this;
    }

    public UserExample andRoleIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ROLE_ID in", values, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ROLE_ID not in", values, "roleId");
        }
        return this;
    }

    public UserExample andRoleIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ROLE_ID between", value1, value2, "roleId");
        return this;
    }

    public UserExample andRoleIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ROLE_ID not between", value1, value2, "roleId");
        return this;
    }

    public UserExample andLoginNameIsNull() {
        criteriaWithoutValue.add("t.LOGIN_NAME is null");
        return this;
    }

    public UserExample andLoginNameIsNotNull() {
        criteriaWithoutValue.add("t.LOGIN_NAME is not null");
        return this;
    }

    public UserExample andLoginNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME =", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME <>", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME >", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME >=", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME <", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME <=", value, "loginName");
        }
        return this;
    }

    public UserExample orLoginNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME =", value );
        }
        return this;
    }

    public UserExample orLoginNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME <>", value );
        }
        return this;
    }

    public UserExample orLoginNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME >", value );
        }
        return this;
    }

    public UserExample orLoginNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME >=", value );
        }
        return this;
    }

    public UserExample orLoginNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME <", value );
        }
        return this;
    }

    public UserExample orLoginNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME <=", value );
        }
        return this;
    }

    public UserExample andLoginNameLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME like", value, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.LOGIN_NAME not like", value, "loginName");
        }
        return this;
    }

    public UserExample orLoginNameLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME like", value );
        }
        return this;
    }

    public UserExample orLoginNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.LOGIN_NAME not like", value );
        }
        return this;
    }

    public UserExample andLoginNameIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGIN_NAME in", values, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.LOGIN_NAME not in", values, "loginName");
        }
        return this;
    }

    public UserExample andLoginNameBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.LOGIN_NAME between", value1, value2, "loginName");
        return this;
    }

    public UserExample andLoginNameNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.LOGIN_NAME not between", value1, value2, "loginName");
        return this;
    }

    public UserExample andZhNameIsNull() {
        criteriaWithoutValue.add("t.ZH_NAME is null");
        return this;
    }

    public UserExample andZhNameIsNotNull() {
        criteriaWithoutValue.add("t.ZH_NAME is not null");
        return this;
    }

    public UserExample andZhNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME =", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME <>", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME >", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME >=", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME <", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME <=", value, "zhName");
        }
        return this;
    }

    public UserExample orZhNameEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME =", value );
        }
        return this;
    }

    public UserExample orZhNameNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME <>", value );
        }
        return this;
    }

    public UserExample orZhNameGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME >", value );
        }
        return this;
    }

    public UserExample orZhNameGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME >=", value );
        }
        return this;
    }

    public UserExample orZhNameLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME <", value );
        }
        return this;
    }

    public UserExample orZhNameLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME <=", value );
        }
        return this;
    }

    public UserExample andZhNameLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME like", value, "zhName");
        }
        return this;
    }

    public UserExample andZhNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ZH_NAME not like", value, "zhName");
        }
        return this;
    }

    public UserExample orZhNameLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME like", value );
        }
        return this;
    }

    public UserExample orZhNameNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ZH_NAME not like", value );
        }
        return this;
    }

    public UserExample andZhNameIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ZH_NAME in", values, "zhName");
        }
        return this;
    }

    public UserExample andZhNameNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ZH_NAME not in", values, "zhName");
        }
        return this;
    }

    public UserExample andZhNameBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ZH_NAME between", value1, value2, "zhName");
        return this;
    }

    public UserExample andZhNameNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ZH_NAME not between", value1, value2, "zhName");
        return this;
    }

    public UserExample andPhoneIsNull() {
        criteriaWithoutValue.add("t.PHONE is null");
        return this;
    }

    public UserExample andPhoneIsNotNull() {
        criteriaWithoutValue.add("t.PHONE is not null");
        return this;
    }

    public UserExample andPhoneEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE =", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE <>", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE >", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE >=", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE <", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE <=", value, "phone");
        }
        return this;
    }

    public UserExample orPhoneEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE =", value );
        }
        return this;
    }

    public UserExample orPhoneNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE <>", value );
        }
        return this;
    }

    public UserExample orPhoneGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE >", value );
        }
        return this;
    }

    public UserExample orPhoneGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE >=", value );
        }
        return this;
    }

    public UserExample orPhoneLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE <", value );
        }
        return this;
    }

    public UserExample orPhoneLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE <=", value );
        }
        return this;
    }

    public UserExample andPhoneLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE like", value, "phone");
        }
        return this;
    }

    public UserExample andPhoneNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PHONE not like", value, "phone");
        }
        return this;
    }

    public UserExample orPhoneLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE like", value );
        }
        return this;
    }

    public UserExample orPhoneNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PHONE not like", value );
        }
        return this;
    }

    public UserExample andPhoneIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PHONE in", values, "phone");
        }
        return this;
    }

    public UserExample andPhoneNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PHONE not in", values, "phone");
        }
        return this;
    }

    public UserExample andPhoneBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.PHONE between", value1, value2, "phone");
        return this;
    }

    public UserExample andPhoneNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.PHONE not between", value1, value2, "phone");
        return this;
    }

    public UserExample andEmailIsNull() {
        criteriaWithoutValue.add("t.EMAIL is null");
        return this;
    }

    public UserExample andEmailIsNotNull() {
        criteriaWithoutValue.add("t.EMAIL is not null");
        return this;
    }

    public UserExample andEmailEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL =", value, "email");
        }
        return this;
    }

    public UserExample andEmailNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL <>", value, "email");
        }
        return this;
    }

    public UserExample andEmailGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL >", value, "email");
        }
        return this;
    }

    public UserExample andEmailGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL >=", value, "email");
        }
        return this;
    }

    public UserExample andEmailLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL <", value, "email");
        }
        return this;
    }

    public UserExample andEmailLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL <=", value, "email");
        }
        return this;
    }

    public UserExample orEmailEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL =", value );
        }
        return this;
    }

    public UserExample orEmailNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL <>", value );
        }
        return this;
    }

    public UserExample orEmailGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL >", value );
        }
        return this;
    }

    public UserExample orEmailGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL >=", value );
        }
        return this;
    }

    public UserExample orEmailLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL <", value );
        }
        return this;
    }

    public UserExample orEmailLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL <=", value );
        }
        return this;
    }

    public UserExample andEmailLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL like", value, "email");
        }
        return this;
    }

    public UserExample andEmailNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.EMAIL not like", value, "email");
        }
        return this;
    }

    public UserExample orEmailLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL like", value );
        }
        return this;
    }

    public UserExample orEmailNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.EMAIL not like", value );
        }
        return this;
    }

    public UserExample andEmailIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.EMAIL in", values, "email");
        }
        return this;
    }

    public UserExample andEmailNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.EMAIL not in", values, "email");
        }
        return this;
    }

    public UserExample andEmailBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.EMAIL between", value1, value2, "email");
        return this;
    }

    public UserExample andEmailNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.EMAIL not between", value1, value2, "email");
        return this;
    }

    public UserExample andMobileIsNull() {
        criteriaWithoutValue.add("t.MOBILE is null");
        return this;
    }

    public UserExample andMobileIsNotNull() {
        criteriaWithoutValue.add("t.MOBILE is not null");
        return this;
    }

    public UserExample andMobileEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE =", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE <>", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE >", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE >=", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE <", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE <=", value, "mobile");
        }
        return this;
    }

    public UserExample orMobileEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE =", value );
        }
        return this;
    }

    public UserExample orMobileNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE <>", value );
        }
        return this;
    }

    public UserExample orMobileGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE >", value );
        }
        return this;
    }

    public UserExample orMobileGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE >=", value );
        }
        return this;
    }

    public UserExample orMobileLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE <", value );
        }
        return this;
    }

    public UserExample orMobileLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE <=", value );
        }
        return this;
    }

    public UserExample andMobileLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE like", value, "mobile");
        }
        return this;
    }

    public UserExample andMobileNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.MOBILE not like", value, "mobile");
        }
        return this;
    }

    public UserExample orMobileLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE like", value );
        }
        return this;
    }

    public UserExample orMobileNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.MOBILE not like", value );
        }
        return this;
    }

    public UserExample andMobileIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.MOBILE in", values, "mobile");
        }
        return this;
    }

    public UserExample andMobileNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.MOBILE not in", values, "mobile");
        }
        return this;
    }

    public UserExample andMobileBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.MOBILE between", value1, value2, "mobile");
        return this;
    }

    public UserExample andMobileNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.MOBILE not between", value1, value2, "mobile");
        return this;
    }

    public UserExample andAddressIsNull() {
        criteriaWithoutValue.add("t.ADDRESS is null");
        return this;
    }

    public UserExample andAddressIsNotNull() {
        criteriaWithoutValue.add("t.ADDRESS is not null");
        return this;
    }

    public UserExample andAddressEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS =", value, "address");
        }
        return this;
    }

    public UserExample andAddressNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS <>", value, "address");
        }
        return this;
    }

    public UserExample andAddressGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS >", value, "address");
        }
        return this;
    }

    public UserExample andAddressGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS >=", value, "address");
        }
        return this;
    }

    public UserExample andAddressLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS <", value, "address");
        }
        return this;
    }

    public UserExample andAddressLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS <=", value, "address");
        }
        return this;
    }

    public UserExample orAddressEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS =", value );
        }
        return this;
    }

    public UserExample orAddressNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS <>", value );
        }
        return this;
    }

    public UserExample orAddressGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS >", value );
        }
        return this;
    }

    public UserExample orAddressGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS >=", value );
        }
        return this;
    }

    public UserExample orAddressLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS <", value );
        }
        return this;
    }

    public UserExample orAddressLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS <=", value );
        }
        return this;
    }

    public UserExample andAddressLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS like", value, "address");
        }
        return this;
    }

    public UserExample andAddressNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.ADDRESS not like", value, "address");
        }
        return this;
    }

    public UserExample orAddressLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS like", value );
        }
        return this;
    }

    public UserExample orAddressNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.ADDRESS not like", value );
        }
        return this;
    }

    public UserExample andAddressIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ADDRESS in", values, "address");
        }
        return this;
    }

    public UserExample andAddressNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.ADDRESS not in", values, "address");
        }
        return this;
    }

    public UserExample andAddressBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ADDRESS between", value1, value2, "address");
        return this;
    }

    public UserExample andAddressNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.ADDRESS not between", value1, value2, "address");
        return this;
    }

    public UserExample andCityIdIsNull() {
        criteriaWithoutValue.add("t.CITY_ID is null");
        return this;
    }

    public UserExample andCityIdIsNotNull() {
        criteriaWithoutValue.add("t.CITY_ID is not null");
        return this;
    }

    public UserExample andCityIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID =", value, "cityId");
        }
        return this;
    }

    public UserExample andCityIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID <>", value, "cityId");
        }
        return this;
    }

    public UserExample andCityIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID >", value, "cityId");
        }
        return this;
    }

    public UserExample andCityIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID >=", value, "cityId");
        }
        return this;
    }

    public UserExample andCityIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID <", value, "cityId");
        }
        return this;
    }

    public UserExample andCityIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.CITY_ID <=", value, "cityId");
        }
        return this;
    }

    public UserExample orCityIdEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID =", value );
        }
        return this;
    }

    public UserExample orCityIdNotEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID <>", value );
        }
        return this;
    }

    public UserExample orCityIdGreaterThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID >", value );
        }
        return this;
    }

    public UserExample orCityIdGreaterThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID >=", value );
        }
        return this;
    }

    public UserExample orCityIdLessThan(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID <", value );
        }
        return this;
    }

    public UserExample orCityIdLessThanOrEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.CITY_ID <=", value );
        }
        return this;
    }

    public UserExample andCityIdIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.CITY_ID in", values, "cityId");
        }
        return this;
    }

    public UserExample andCityIdNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.CITY_ID not in", values, "cityId");
        }
        return this;
    }

    public UserExample andCityIdBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.CITY_ID between", value1, value2, "cityId");
        return this;
    }

    public UserExample andCityIdNotBetween(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.CITY_ID not between", value1, value2, "cityId");
        return this;
    }

    public UserExample andPasswordIsNull() {
        criteriaWithoutValue.add("t.PASSWORD is null");
        return this;
    }

    public UserExample andPasswordIsNotNull() {
        criteriaWithoutValue.add("t.PASSWORD is not null");
        return this;
    }

    public UserExample andPasswordEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD =", value, "password");
        }
        return this;
    }

    public UserExample andPasswordNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD <>", value, "password");
        }
        return this;
    }

    public UserExample andPasswordGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD >", value, "password");
        }
        return this;
    }

    public UserExample andPasswordGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD >=", value, "password");
        }
        return this;
    }

    public UserExample andPasswordLessThan(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD <", value, "password");
        }
        return this;
    }

    public UserExample andPasswordLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD <=", value, "password");
        }
        return this;
    }

    public UserExample orPasswordEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD =", value );
        }
        return this;
    }

    public UserExample orPasswordNotEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD <>", value );
        }
        return this;
    }

    public UserExample orPasswordGreaterThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD >", value );
        }
        return this;
    }

    public UserExample orPasswordGreaterThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD >=", value );
        }
        return this;
    }

    public UserExample orPasswordLessThan(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD <", value );
        }
        return this;
    }

    public UserExample orPasswordLessThanOrEqualTo(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD <=", value );
        }
        return this;
    }

    public UserExample andPasswordLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD like", value, "password");
        }
        return this;
    }

    public UserExample andPasswordNotLike(String value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.PASSWORD not like", value, "password");
        }
        return this;
    }

    public UserExample orPasswordLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD like", value );
        }
        return this;
    }

    public UserExample orPasswordNotLike(String value) {
        if(value!=null&&!value.equals("")){
            setCriteriaWithOrValue("t.PASSWORD not like", value );
        }
        return this;
    }

    public UserExample andPasswordIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PASSWORD in", values, "password");
        }
        return this;
    }

    public UserExample andPasswordNotIn(List values) {
        if(values!=null&&!values.isEmpty()){
            addCriterion("t.PASSWORD not in", values, "password");
        }
        return this;
    }

    public UserExample andPasswordBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.PASSWORD between", value1, value2, "password");
        return this;
    }

    public UserExample andPasswordNotBetween(String value1, String value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("");
        }
        addCriterion("t.PASSWORD not between", value1, value2, "password");
        return this;
    }
    
    public UserExample andCitygrpIdEqualTo(Integer value) {
    	 if(value!=null&&!value.equals("")){
             setCriteriaWithOrValue("r.citygrp_id =", value );
         }
         return this;
    }
    
    public UserExample andStatusEqualTo(Integer value) {
        if(value!=null&&!value.equals("")){
            addCriterion("t.STATUS =", value, "status");
        }
        return this;
    } 
    
}