package com.ailk.eaap.op2.sso.framework.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseExampleObject extends BaseModel{
	public BaseExampleObject() {
		criteriaWithoutValue = new ArrayList();
		criteriaWithSingleValue = new ArrayList();
		criteriaWithListValue = new ArrayList();
		criteriaWithBetweenValue = new ArrayList();
		criteriaWithExistsValue = new ArrayList();
		criteriaWithInValue = new ArrayList();
		criteriaWithOrValue = new ArrayList();
		criteriaWithOutExistsValue = new ArrayList();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(this);
			
		}
	}

	public void clearQuery() {
		criteriaWithoutValue.clear();
		criteriaWithSingleValue.clear();
		criteriaWithListValue.clear();
		criteriaWithBetweenValue.clear();
		criteriaWithExistsValue.clear();
		criteriaWithInValue.clear();
		criteriaWithOrValue.clear();
		criteriaWithOutExistsValue.clear();
	}

	public List criteriaWithoutValue;

	public List criteriaWithSingleValue;

	public List criteriaWithOrValue;

	public List criteriaWithExistsValue;
	
	public List criteriaWithOutExistsValue;

	public List criteriaWithInValue;

	public List criteriaWithListValue;

	public List criteriaWithBetweenValue;

	public List getCriteriaWithoutValue() {
		return criteriaWithoutValue;
	}

	public List getCriteriaWithSingleValue() {
		return criteriaWithSingleValue;
	}

	public List getCriteriaWithOrValue() {
		return criteriaWithOrValue;
	}

	public List getCriteriaWithExistsValue() {
		return criteriaWithExistsValue;
	}

	public List getCriteriaWithInValue() {
		return criteriaWithInValue;
	}

	public List getCriteriaWithListValue() {
		return criteriaWithListValue;
	}

	public List getCriteriaWithBetweenValue() {
		return criteriaWithBetweenValue;
	}
	
	public List getCriteriaWithOutExistsValue() {
		return criteriaWithOutExistsValue;
	}

	public void addCriterion(String condition, Object value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property
					+ " cannot be null");
		}
		Map map = new HashMap();
		map.put("condition", condition);
		map.put("value", value);
		criteriaWithSingleValue.add(map);
	}

	public void addCriterion(String condition, List values, String property) {
		if (values == null || values.size() == 0) {
			throw new RuntimeException("Value list for " + property
					+ " cannot be null or empty");
		}
		Map map = new HashMap();
		map.put("condition", condition);
		map.put("values", values);
		criteriaWithListValue.add(map);
	}

	public void addCriterion(String condition, Object value1, Object value2,
			String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property
					+ " cannot be null");
		}
		List list = new ArrayList();
		list.add(value1);
		list.add(value2);
		Map map = new HashMap();
		map.put("condition", condition);
		map.put("values", list);
		criteriaWithBetweenValue.add(map);
	}

	private String orderByClause;

	private Integer startPos;

	private Integer endPos;

	private Integer pageNumber;

	private Integer pageSize;

	public List oredCriteria = new ArrayList();

	public List getOredCriteria() {
		return oredCriteria;
	}

	public void or(Object criteria) {
		oredCriteria.add(criteria);
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setStartPos(Integer startPos) {
		this.startPos = startPos;
	}

	public Integer getStartPos() {
		return startPos;
	}

	public void setEndPos(Integer endPos) {
		this.endPos = endPos;
	}

	public Integer getEndPos() {
		return endPos;
	}

	@Override
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public Integer getPageNumber() {
		return pageNumber;
	}

	@Override
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPosition(Integer pageNumber, Integer pageSize) {
		if (pageNumber == null || pageNumber < 1) {
			this.pageNumber = 1;
		} else {
			this.pageNumber = pageNumber;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 1;
		} else {
			this.pageSize = pageSize;
		}
		startPos = (this.pageNumber - 1) * this.pageSize + 1;
		endPos = startPos + pageSize - 1;
	}

}
