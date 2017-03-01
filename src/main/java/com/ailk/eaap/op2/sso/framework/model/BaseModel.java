package com.ailk.eaap.op2.sso.framework.model;

import java.lang.reflect.Method;

public class BaseModel implements java.io.Serializable{
	private static final long serialVersionUID = 5908541403136088925L;

	public BaseModel() {
		
	}
	private String indesId ;
	private Integer indes;
	private Integer indexId;
	private String[] checkid;
	
	private String indicator;
	/**
	 * 页面总数(分页时使用)
	 */
	private Integer pageNumber=1;

	/**
	 * 页面显示的记录数(分页时使用)
	 */
	private Integer pageSize=15;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    public Object getValueByName(String name) {
        if (name == null || name.trim().equals("")) {
            return null;
        }
        name = name.trim();
        String methodName;
        if (name.length() > 1) {
            methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            methodName = "get" + name.toUpperCase();
        }
        try {
            Method method = this.getClass().getMethod(methodName, new Class[0]);
            return method.invoke(this, new Object[0]);
        } catch (java.lang.NoSuchMethodException nme) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void setValueByName(String fieldName, Object value) {
        if (fieldName != null && !fieldName.trim().equals("")) {
            fieldName = fieldName.trim();
            String methodName;
            if (fieldName.length() > 1) {
                methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            } else {
                methodName = "set" + fieldName.toUpperCase();
            }
            try {
                Method method = this.getClass().getMethod(methodName, value.getClass());
                method.invoke(this, value);
            } catch (java.lang.NoSuchMethodException nme) {
            } catch (Exception e) {
            }
        }
    }

	public String[] getCheckid() {
		return checkid;
	}

	public void setCheckid(String[] checkid) {
		this.checkid = checkid;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Integer getIndes() {
		return indes;
	}

	public void setIndes(Integer indes) {
		this.indes = indes;
	}

	public String getIndesId() {
		return indesId;
	}

	public void setIndesId(String indesId) {
		this.indesId = indesId;
	}
}
