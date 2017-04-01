package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.GiftClass;

public interface GiftClassDao {
	/**
	 * @return  获取所有的礼品分类信息
	 */
	public List<GiftClass> findAllClass();
	/**
	 * @param id
	 * @return 根据id获取分类信息
	 */
	public GiftClass findIdByClass(int id);
	
	public List<GiftClass>  findTopClass(int top);

}
