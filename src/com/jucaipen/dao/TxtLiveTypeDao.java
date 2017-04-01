package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TxtLiveType;

/**
 * @author Administrator
 *
 *  文字直播分类信息
 */
public interface TxtLiveTypeDao {
	
	/**
	 * @return
	 *   获取所有文字直播分类信息
	 */
	public List<TxtLiveType> findAll();
	
	/**
	 * @param id
	 * @return 根据id获取文字直播分类信息
	 */
	public TxtLiveType findTypeById(int id); 

}
