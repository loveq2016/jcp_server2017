package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Recommder;

public interface RecommderDao {
	
	/**
	 * @param id
	 * @return 根据id获取推荐信息
	 */
	public Recommder findRecommderById(int id);
	
	/**
	 * @param parentId
	 * @param page
	 * @return 获取我推荐的用户信息
	 */
	public List<Recommder> findRecommderByParentId(int parentId,int page);
	
	/**
	 * @param recommder
	 * @return 添加推荐信息
	 *  
	 */
	public int addRecommder(Recommder recommder);

}
