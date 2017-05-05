package com.jucaipen.base;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jucaipen.model.Advertive;
import com.jucaipen.service.AdverSer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
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
		List<Advertive> findAdver = AdverSer.findAdverByPid(12);
		String advertive1 = JsonUtil.getAdvertive1(findAdver);
		try {
			new CacheUtils(Constant.DEFAULT_CACHE).addToCache("ads", advertive1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
