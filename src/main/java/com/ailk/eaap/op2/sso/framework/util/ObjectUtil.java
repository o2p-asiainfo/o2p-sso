package com.ailk.eaap.op2.sso.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;

public class ObjectUtil {
	public ObjectUtil() {
	}

	
	public static void copyObject(Object from, Object to) {
		Field[] fields = ObjectUtil.getAllFields(to.getClass());
		for (Object obj : fields) {
			Field field = (Field) obj;
			String fieldName = field.getName();
			try {
				String methodName = "get"
						+ StringUtil.toFirstLetterUppcase(fieldName);
				Method method = from.getClass().getMethod(methodName,
						new Class[0]);
				Object value = method.invoke(from, new Object[0]);
				if (value != null) {
					methodName = "set"
							+ StringUtil.toFirstLetterUppcase(fieldName);
					method = to.getClass().getMethod(methodName,
							new Class[] { value.getClass() });
					method.invoke(to, value);
				}
			} catch (NoSuchMethodException e) {
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void copyModeltoModel(Object src, Object des) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(des); // �õ�to������ֶ����Լ���
		for (int i = 0; i < pds.length; i++) {
			try {
				String className = ClassUtils.getShortClassName(pds[i]
						.getPropertyType());
				PropertyDescriptor descriptor = null;
				try {
					descriptor = PropertyUtils.getPropertyDescriptor(src,
							pds[i].getDisplayName());
					if (descriptor == null) {
						continue; // Skip this property setter
					}
				} catch (NoSuchMethodException e) {
					continue; // Skip this property setter
				}
				String fromClassName = ClassUtils.getShortClassName(descriptor
						.getPropertyType());

				if ("Class".equals(className)) {
					continue;
				} else {
					Object o = PropertyUtils.getProperty(src, pds[i]
							.getDisplayName());
					if (o != null) {
						if (className.equals("Date")
								&& fromClassName.equals("String")) { 
							if (StringUtil.isDateTimeFormat((String) o)) {
								o = DateUtil.toDateByFormat((String) o,
										"yyyy-MM-dd HH:mm:ss");
							} else if (StringUtil.isDateFormat((String) o)) {
								o = DateUtil.toDateByFormat((String) o,
										"yyyy-MM-dd");
							} else {
								continue;
							}
						} else if (className.equals("String")
								&& fromClassName.equals("Date")) {
							o = DateUtil.toStringByFormat((Date) o,
									"yyyy-MM-dd HH:mm:ss");
							if (org.apache.commons.lang.StringUtils.contains(
									(String) o, "00:00:00")) {
								o = org.apache.commons.lang.StringUtils
										.substringBeforeLast((String) o,
												"00:00:00").trim();
							}
						}
						org.apache.commons.beanutils.BeanUtils.setProperty(des,
								pds[i].getDisplayName(), o);
					}
				}
			} catch (Exception ex) {
			}
		}
	}

	
	public static Map describe(Object bean) {
		if (bean == null) {
			return (new java.util.HashMap());
		}
		Map description = new HashMap();
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < pds.length; i++) {
			try {
				String className = ClassUtils.getShortClassName(pds[i]
						.getPropertyType());
				if ("Class".equals(className)) {
					continue;
				} else {
					Object o = PropertyUtils.getProperty(bean, pds[i]
							.getDisplayName());
					if (o != null) {
						description.put(pds[i].getDisplayName(), o);
					}
				}
			} catch (Exception ex) {
			}
		}
		return (description);

	}

	
	public static void copyObjectToObject(Object from, Object to) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(to); 
		for (int i = 0; i < pds.length; i++) {
			try {
				String className = ClassUtils.getShortClassName(pds[i]
						.getPropertyType());
				PropertyDescriptor descriptor = null;
				try {
					descriptor = PropertyUtils.getPropertyDescriptor(from,
							pds[i].getDisplayName());
					if (descriptor == null) {
						continue; // Skip this property setter
					}
				} catch (NoSuchMethodException e) {
					continue; // Skip this property setter
				}
				String fromClassName = ClassUtils.getShortClassName(descriptor
						.getPropertyType());

				if ("Class".equals(className)) {
					continue;
				} else {
					Object o = PropertyUtils.getProperty(from, pds[i]
							.getDisplayName());

					if (className.equals("Date")
							&& fromClassName.equals("String")) { 
						if (StringUtil.isDateTimeFormat((String) o)) {
							o = DateUtil.toDateByFormat((String) o,
									"yyyy-MM-dd HH:mm:ss");
						} else if (StringUtil.isDateFormat((String) o)) {
							o = DateUtil.toDateByFormat((String) o,
									"yyyy-MM-dd");
						} else {
							o = null;
						}
					} else if (className.equals("String")
							&& fromClassName.equals("Date")) {
						o = DateUtil.toStringByFormat((Date) o,
								"yyyy-MM-dd HH:mm:ss");
						if (org.apache.commons.lang.StringUtils.contains(
								(String) o, "00:00:00")) {
							o = org.apache.commons.lang.StringUtils
									.substringBeforeLast((String) o, "00:00:00")
									.trim();
						}

					}
					org.apache.commons.beanutils.BeanUtils.setProperty(to,
							pds[i].getDisplayName(), o);

				}
			} catch (Exception ex) {
			}
		}

	}

	
	public static Field[] getAllFields(Class objClass) {
		Map map = new HashMap();
		boolean end = false;
		Class c = objClass;
		while (!end) {
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; fields != null && i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldType = fields[i].getType().getName();
				if (map.get(fieldType + "_" + fieldName) == null) {
					map.put(fieldType + "_" + fieldName, fields[i]);
				}
			}
			c = c.getSuperclass();
			if (c == null) {
				end = true;
			}
		}
		Collection ct = map.values();
		if (ct.size() > 0) {
			Object[] objs = ct.toArray();
			Field[] fields = new Field[objs.length];
			for (int i = 0; i < objs.length; i++) {
				fields[i] = (Field) objs[i];
			}
			return fields;
		} else {
			return null;
		}
	}

	public static Object getValueByPropertyName(Object o, String property) {
		try {
			return PropertyUtils.getProperty(o, property);
		} catch (Exception e) {
			return null;
		}
	}

	public static void setValueByPropertyName(Object des, String property,
			Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(des, property,
					value);
		} catch (IllegalAccessException e) {

		} catch (InvocationTargetException e) {

		}
	}
	
	public void covnterMap(Map map){
		
	}

	public static void main(String[] args) {

	}
}
