package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.AreaDao;
import com.jucaipen.model.Area;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.SqlUtil;

public class AreaImp implements AreaDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Area> areas;

	/**
	 * @return 查询地区信息总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Eare "
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
	 * 根据省市查询地区
	 */
	public List<Area> findAreas(int pId, int cId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Eare WHERE FK_CityId="+cId);
			areas = getAreas(res, -1, -1);
			return areas;
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
	 * 根据id查询地区信息
	 */
	public Area findArea(int id) {
		Area a = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_Eare where EareId=" + id);
			areas = getAreas(res, -1, -1);
			if (areas != null && areas.size() > 0) {
				a = areas.get(0);
			}
			return a;
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
	 * @return 获取地区信息
	 */
	public List<Area> getAreas(ResultSet result, int pager, int totlePager) {
		areas = new ArrayList<Area>();
		try {
			while (result.next()) {
				int id = result.getInt(SqlUtil.EARE_ID);
				int cityId = result.getInt(SqlUtil.AREA_CID);
				String name = result.getString(SqlUtil.AREA_NAME);
				int sortId = result.getInt(SqlUtil.AREA_SORT);
				String remark=result.getString("Eremark");
				Area area = new Area(id, cityId, name, sortId, remark);
				area.setTotlePager(totlePager);
				area.setPager(pager);
				areas.add(area);
			}
			return areas;
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
