package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.CityDao;
import com.jucaipen.daoimp.CityImp;
import com.jucaipen.model.City;

public class CityServer {
	/**
	 * @param pId
	 * @return 查询省份下的所有城市信息
	 */
	public static List<City> getCitys(int pId) {
		CityDao dao = new CityImp();
		return dao.findCitys(pId);
	}

	/**
	 * @param id
	 * @return 查询指定城市信息
	 */
	public static City getCity(int id) {
		CityDao dao = new CityImp();
		return dao.findCity(id);
	}

	/**
	 * @param pager
	 * @return  查询所有城市信息
	 */
	public static List<City> findAll(int pager) {
		CityDao dao = new CityImp();
		return dao.findAll(pager);
	}

}
