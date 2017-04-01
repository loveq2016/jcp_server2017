package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.CityDao;
import com.jucaipen.model.City;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class CityImp implements CityDao {
	private List<City> cities;
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	/**
	 * @return 查询城市总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_City "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/*
	 * 查询省份下所有的城市
	 */
	public List<City> findCitys(int pId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_City WHERE FK_ProId="+pId);
			cities = getCitys(res, -1, -1);
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<City> findAll(int pager) {
		int totlePager = findTotlePager("");
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY CityId) AS RowNumber,* FROM JCP_City"
							+ " ) A "
							+ "WHERE RowNumber > " + 15 * (pager - 1));
			cities = getCitys(res, pager, totlePager);
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/*
	 * 根据id查询城市信息
	 */
	public City findCity(int id) {
		City c = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_City where CityId=" + id);
			cities = getCitys(res, -1, 1);
			if (cities != null && cities.size() > 0) {
				c = cities.get(0);
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * @param result
	 * @return 获取城市信息
	 */
	public List<City> getCitys(ResultSet result, int pager, int totlePager) {
		cities = new ArrayList<City>();
		try {
			while (result.next()) {
				int id = result.getInt(SqlUtil.CITY_ID);
				int provinceId = result.getInt(SqlUtil.CITY_PID);
				String name = result.getString(SqlUtil.CITY_NAME);
				int sortId = result.getInt(SqlUtil.CITY_SORT);
				String remark=result.getString("CRemark");
				City city = new City(id, provinceId, name, sortId, remark);
				city.setTotlePager(totlePager);
				city.setPager(pager);
				cities.add(city);
			}
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
