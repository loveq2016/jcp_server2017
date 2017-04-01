package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.StudioGuestDao;
import com.jucaipen.daoimp.StudioGuestImp;
import com.jucaipen.model.StudioGuest;

/**
 * @author Administrator
 *
 *  演播室嘉宾
 */
public class StudioGuestSer{

	/**
	 * @param page
	 * @param studioId
	 * @return  获取演播室所有的嘉宾
	 */
	public static List<StudioGuest> findAll(int page, int studioId) {
		StudioGuestDao dao=new StudioGuestImp();
		return dao.findAll(page, studioId);
	}

	/**
	 * @param id
	 * @return 根据id获取嘉宾信息
	 */
	public static StudioGuest findGuestById(int id) {
		StudioGuestDao dao=new StudioGuestImp();
		return dao.findGuestById(id);
	}

	/**
	 * @param top
	 * @param studioId
	 * @return 获取演播室最近的嘉宾
	 */
	public static List<StudioGuest> findTopGuest(int top, int studioId) {
		StudioGuestDao dao=new StudioGuestImp();
		return dao.findTopGuest(top, studioId);
	}

}
