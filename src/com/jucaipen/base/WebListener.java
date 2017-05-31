package com.jucaipen.base;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * @author 杨朗飞 2016年11月4日 下午4:11:47
 * 
 *         监听web 容器的启动
 */
public class WebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent config) {
		//是否审核
		config.getServletContext().setAttribute("check", false);
		//是否启用完整功能
		config.getServletContext().setAttribute("expand", true);
		//是否缓存
		config.getServletContext().setAttribute("hasCache", true);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
