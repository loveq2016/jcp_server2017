package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.ResourceSources;

public interface ResourceFromDao {
	/**
	 * @return  查询所有信息来源
	 */
	public List<ResourceSources> findAll(int pager);
	/**
	 * @param id
	 * @return  查询指定信息来源
	 */
	public String findResourceSources(int id);

}
