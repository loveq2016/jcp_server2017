package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.StudioGuestDao;
import com.jucaipen.model.StudioGuest;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *  演播室嘉宾
 */
public class StudioGuestImp implements StudioGuestDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<StudioGuest> guests = new ArrayList<StudioGuest>();

	/**
	 * @return 查询嘉宾总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_StudioGuest "
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


	@Override
	public List<StudioGuest> findAll(int page, int studioId) {
		// 获取演播室所有嘉宾信息
		guests.clear();
		int totlePage=getTotlePage("WHERE StudioId="+studioId);
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY Id desc) AS RowNumber,* FROM JCP_StudioGuest WHERE StudioId="
							+ studioId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id=res.getInt("Id");  //Id
				String name=res.getString("Name");  //Name
				String headFace=res.getString("Headface");  //Headface
				String touxian=res.getString("TouXian");  //TouXian
				String jianJie=res.getString("jianjie");  //jianjie
				String shanChange=res.getString("shanchang");  //shanchang
				StudioGuest guest=new StudioGuest();
				guest.setTotlePage(totlePage);
				guest.setPage(page);
				guest.setId(id);
				guest.setName(name);
				guest.setHeadFace(headFace);
				guest.setLeavl(touxian);
				guest.setHoby(shanChange);
				guest.setJianjie(jianJie);
				guests.add(guest);
			}
			return guests;
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

	@Override
	public StudioGuest findGuestById(int id) {
		//根据id获取演播室嘉宾信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM  JCP_StudioGuest WHERE Id="+id);
			while (res.next()) {
				int studioId=res.getInt("StudioId");  //Id
				String name=res.getString("Name");  //Name
				String headFace=res.getString("Headface");  //Headface
				String touxian=res.getString("TouXian");  //TouXian
				String jianJie=res.getString("jianjie");  //jianjie
				String shanChange=res.getString("shanchang");  //shanchang
				StudioGuest guest=new StudioGuest();
				guest.setId(id);
				guest.setName(name);
				guest.setStudioId(studioId);
				guest.setHeadFace(headFace);
				guest.setLeavl(touxian);
				guest.setHoby(shanChange);
				guest.setJianjie(jianJie);
				return guest;
			}
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

	@Override
	public List<StudioGuest> findTopGuest(int top, int studioId) {
		//获取演播室最近的嘉宾信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP "+top+" * FROM  JCP_StudioGuest WHERE StudioId="+studioId);
			while (res.next()) {
				int id=res.getInt("Id");  //Id
				String name=res.getString("Name");  //Name
				String headFace=res.getString("Headface");  //Headface
				String touxian=res.getString("TouXian");  //TouXian
				String jianJie=res.getString("jianjie");  //jianjie
				String shanChange=res.getString("shanchang");  //shanchang
				StudioGuest guest=new StudioGuest();
				guest.setId(id);
				guest.setName(name);
				guest.setStudioId(studioId);
				guest.setHeadFace(headFace);
				guest.setLeavl(touxian);
				guest.setHoby(shanChange);
				guest.setJianjie(jianJie);
				guests.add(guest);
			}
			return guests;
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
