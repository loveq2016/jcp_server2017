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
		//初始化缓存数据
		config.getServletContext().setAttribute("check", false);
		config.getServletContext().setAttribute("expand", true);
	/*	List<Advertive> findAdver = AdverSer.findAdverByPid(12);
		String advertive1 = JsonUtil.getAdvertive1(findAdver);
		new CacheUtils(Constant.DEFAULT_CACHE).addToCache("ads", advertive1);*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
