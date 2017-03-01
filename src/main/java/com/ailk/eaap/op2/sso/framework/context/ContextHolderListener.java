package com.ailk.eaap.op2.sso.framework.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.asiainfo.integration.o2p.web.util.WebPropertyUtil;



public class ContextHolderListener extends ContextLoaderListener {
	public static final String APPLICATION_STYLE_THEME = "contextStyleTheme";
	public static final String APPLICATION_STYLE_SPECIAL = "contextStyleSpecial";
	public static final String APPLICATION_MENU_BELONGTO = "contextMenuBelongto";
	public static final String APP_WEB_TITLE="APP_WEB_TITLE";
	
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ContextHolder.setApplicationContext(ctx);
		
		//样式主题，取/resource/目录下哪一主题的样式
		String StyleTheme = context.getInitParameter(APPLICATION_STYLE_THEME);
		context.setAttribute(APPLICATION_STYLE_THEME, StyleTheme);
		//本项目专用图片目录，取/resource/styleTheme/images/目录下哪一个项目专用目录
		String styleSpecial = context.getInitParameter(APPLICATION_STYLE_SPECIAL);
		context.setAttribute(APPLICATION_STYLE_SPECIAL, styleSpecial);
		//加载对应系统的菜单
		String menuBelongto = context.getInitParameter(APPLICATION_MENU_BELONGTO);
		context.setAttribute(APPLICATION_MENU_BELONGTO, menuBelongto);
		
		context.setAttribute(APP_WEB_TITLE, 
				 WebPropertyUtil.getPropertyValue(APP_WEB_TITLE));
	}
}
