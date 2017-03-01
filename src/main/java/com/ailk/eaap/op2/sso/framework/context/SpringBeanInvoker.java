package com.ailk.eaap.op2.sso.framework.context;

public class SpringBeanInvoker {
	public static Object getBean(String beanName){
		return ContextHolder.getSpringContext().getBean(beanName);
	}
}
