package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Special;

public interface SpecialDao {
	
	/**
	 * @param id
	 * @return 根据id获取专辑信息
	 */
	public Special findSpecialById(int id);
	
	/**
	 * @param page
	 * @return 获取所有专辑信息
	 */
	public List<Special> findAll(int page);

}
