package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.NewsClass;

public interface NewsClassDao {
	/**
	 * @return   获取所有的一级分类信息
	 */
	public List<NewsClass>  findAllNewsClass();
	/**
	 * @param id
	 * @return   通过id 查询分类获取分类信息
	 */
	public List<NewsClass> findNewsClassByPId(int pId);
	
	/**
	 * @param id
	 * @return  根据id获取分类信息
	 */
	public NewsClass findClassById(int id);

}
