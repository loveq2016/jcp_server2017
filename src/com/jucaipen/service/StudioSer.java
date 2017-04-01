package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.StudioDao;
import com.jucaipen.daoimp.StudioImp;
import com.jucaipen.model.Studio;
/**
 * @author Administrator
 *
 *  演播室
 */
public class StudioSer{

	/**
	 * @param page
	 * @return 获取所有演播室信息
	 */
	public static List<Studio> findAll(int page) {
		StudioDao dao=new StudioImp();
		return dao.findAll(page);
	}

	/**
	 * @param id
	 * @return 根据id获取演播室信息
	 */
	public static Studio findStudioById(int id) {
		StudioDao dao=new StudioImp();
		return dao.findStudioById(id);
	}

	/**
	 * @param columnId
	 * @param page
	 * @return 获取栏目下的演播室信息
	 */
	public static List<Studio> findStudioByColumnId(int columnId, int page) {
		StudioDao dao=new StudioImp();
		return dao.findStudioByColumnId(columnId, page);
	}
	
	/**
	 * @param week
	 * @return  获取今日演播节目榜
	 */
	public static List<Studio> findStudioByToday(int week){
		StudioDao dao=new StudioImp();
		return dao.findStudioByToday(week);
	}
	
	/**
	 * @param week
	 * @return  获取人气榜
	 */
	public static List<Studio>  findStudioFansByToday(int week){
		StudioDao dao=new StudioImp();
		return dao.findStudioFansByToday(week);
	}
	
	/**
	 * @param id
	 * @return  根据id获取人气信息
	 */
	public static int findStudioRenQiById(int id){
		StudioDao dao=new StudioImp();
		return dao.findStudioRenQiById(id);
	}
	
	/**
	 * @param id
	 * @param renQi
	 * @return 根据id获取人气信息
	 */
	public static int updateStudioRenQiById(int id,int renQi){
		StudioDao dao=new StudioImp();
		return dao.updateStudioRenQiById(id, renQi);
	}

}
