package com.jucaipen.base;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * @author ���ʷ� 2016��11��4�� ����4:11:47
 * 
 *         ����web ����������
 */
public class WebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent config) {
		//��ʼ����������
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
