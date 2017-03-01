package com.ailk.eaap.op2.sso.main.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import com.ailk.eaap.op2.sso.framework.model.BaseExampleObject;
import com.ailk.eaap.op2.sso.framework.util.DateUtil;
import com.ailk.eaap.op2.sso.framework.util.ObjectUtil;
import com.ailk.eaap.op2.sso.framework.util.StringUtil;

public class ExampleUtil {
	// "忽略"标志
	public static final String IGNORE = "IGNORE";

	// "等于"标志
	public static final String EQUALS = "and_EqualTo";

	// "不等于"标志
	public static final String NOT_EQUALS = "and_NotEqualTo";

	// "大于"标志
	public static final String GREATER_THAN = "and_GreaterThan";

	// "大于等于"标志
	public static final String GREATER_THAN_OR_EQUAL = "and_GreaterThanOrEqualTo";

	// "少于"标志
	public static final String LESS_THAN = "and_LessThan";

	// "少于等于"标志
	public static final String LESS_THAN_OR_EQUAL = "and_LessThanOrEqualTo";

	// "等于"标志
	public static final String LIKE = "and_Like";

	// "或者等于"标志
	public static final String OR_EQUALS = "or_EqualTo";

	// "或者不等于"标志
	public static final String OR_NOT_EQUALS = "or_NotEqualTo";

	// "或者大于"标志
	public static final String OR_GREATER_THAN = "or_GreaterThan";

	// "或者大于等于"标志
	public static final String OR_GREATER_THAN_OR_EQUAL = "or_GreaterThanOrEqualTo";

	// "或者少于"标志
	public static final String OR_LESS_THAN = "or_LessThan";

	// "或者少于等于"标志
	public static final String OR_LESS_THAN_OR_EQUAL = "or_LessThanOrEqualTo";

	// "或者等于"标志
	public static final String OR_LIKE = "or_Like";

	public static void copyValueToExample(Object valueObj,Class fieldsObjClass, BaseExampleObject exampleObj)
	{
		Field[] fields = ObjectUtil.getAllFields(fieldsObjClass);
		for (Object obj : fields) {
			Field field = (Field) obj;
			String fieldName = field.getName();
			try {
				String methodName = "get"
						+ StringUtil.toFirstLetterUppcase(fieldName);
				Method method = valueObj.getClass().getMethod(methodName,
						new Class[0]);
				Object value = method.invoke(valueObj, new Object[0]);
				if (value != null) {
					if(value instanceof String){
						if("".equalsIgnoreCase((String)value)){
							continue;
						}
					}
					if(value instanceof Integer){
						if(0==(Integer)value){
							continue;
						}
					}
					// 查询操作标志值
					Object opValue = null;
					try {
						method = valueObj.getClass().getMethod(
								methodName + "_Indicator", new Class[0]);
						opValue = method.invoke(valueObj, new Object[0]);
						if (opValue == null) {
							method = valueObj.getClass().getMethod(
									"getIndicator", new Class[0]);
							opValue = method.invoke(valueObj, new Object[0]);
						}
					} catch (NoSuchMethodException e) {
						if (opValue == null) {
							method = valueObj.getClass().getMethod(
									"getIndicator", new Class[0]);
							opValue = method.invoke(valueObj, new Object[0]);
						}
					}
					String operator = null;
					// 以城市编码作为查询条件时,都统一以"OR_EQUALS"作为操作符,因为这时都需要加上城市编码为"0"的条件查询全省公用数据
					if (fieldName.equalsIgnoreCase("citycode")) {
						operator = OR_EQUALS;
					} else if (opValue == null || !(opValue instanceof String)) {
						operator = EQUALS;
					}else if(((String)opValue).equalsIgnoreCase(ExampleUtil.LIKE )&& value instanceof Integer){
						operator = EQUALS;
					}
					else {
						operator = (String) opValue;
						operator = operator.trim();
					}
					if (!operator.equals(IGNORE)) {
						int index = operator.indexOf("_");
						if (index != -1) {
							methodName = operator.substring(0, index)
									+ StringUtil
											.toFirstLetterUppcase(fieldName)
									+ operator.substring(index + 1);
							try {
								method = exampleObj.getClass().getMethod(
										methodName, value.getClass());
							} catch (NoSuchMethodException nse) {
								if (!(value instanceof String)) {
									continue;
								}
								try {
									method = exampleObj.getClass().getMethod(
											methodName, java.util.Date.class);
									Date date = DateUtil
											.stringToDateTime((String) value);
									if (date == null) {
										date = DateUtil.getDate((String) value);
									}
									if (date != null) {
										value = date;
									} else {
										continue;
									}
								} catch (NoSuchMethodException ne) {
									continue;
								}
							}
							// 如果是模糊查询时,判断是否在输入条件己经加上"%",如果没加上就执行前后模糊查询
							if (OR_LIKE.equals(operator)
									|| LIKE.equals(operator)) {
								if (value instanceof String) {
									String temp = (String) value;
									if (temp.indexOf("%") == -1) {
										if (fieldsObjClass
												.getSimpleName()
												.equalsIgnoreCase("CustomerTel")
												|| fieldsObjClass
														.getSimpleName()
														.equalsIgnoreCase(
																"customer")) {
											value = temp + "%";
										} else {
											value = "%" + temp + "%";
										}
									}
								}
							}
							method.invoke(exampleObj, value);
						}
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

}
