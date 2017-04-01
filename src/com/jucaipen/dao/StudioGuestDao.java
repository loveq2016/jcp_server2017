package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.StudioGuest;

/**
 * @author Administrator
 *  演播室嘉宾
 */
public interface StudioGuestDao {
	
	/**
	 * @param page
	 * @return 获取演播室所有嘉宾信息
	 */
	public List<StudioGuest> findAll(int page,int studioId);
	
	/**
	 * @param id
	 * @return 根据id 获取嘉宾信息
	 */
	public StudioGuest findGuestById(int id);
	
	/**
	 * @param top
	 * @param studioId
	 * @return 获取演播室最近的几个嘉宾
	 */
	public List<StudioGuest> findTopGuest(int top,int studioId);

}
