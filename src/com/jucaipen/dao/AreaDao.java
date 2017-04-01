package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Area;

public interface AreaDao {
	/**
	 * @param pId
	 * @param cId
	 * @return  查询城市下所有区信息
	 */
	public List<Area> findAreas(int pId,int cId);
	/**
	 * @param id
	 * @param pId
	 * @param cId
	 * @return   查询某个区信息
	 */
	public Area findArea(int id);

}
