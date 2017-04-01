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
		// new TimerManager();
		//timer=new Timer();
		//timer.scheduleAtFixedRate(new MyTask(), new Date(), 1000*60*2);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//timer.cancel();
		//timer=null;

	}

}
