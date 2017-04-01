package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.City;

public interface CityDao {
	/**
	 * @param pId
	 * @return  查询省下所属的所有城市信息
	 */
	public List<City> findCitys(int pId);
	/**
	 * @param pId
	 * @param id
	 * @return  查询省下的某个城市信息
	 */
	public City findCity(int id);
	/**
	 * @param pager
	 * @return  查询所有城市信息
	 */
	public List<City> findAll(int pager);

}
