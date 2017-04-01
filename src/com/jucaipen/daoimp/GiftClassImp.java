package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.GiftClassDao;
import com.jucaipen.model.GiftClass;
import com.jucaipen.utils.JdbcUtil;

public class GiftClassImp implements GiftClassDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<GiftClass> giftClasses=new ArrayList<GiftClass>();

	@Override
	public List<GiftClass> findAllClass() {
		// 获取所有分类信息
		giftClasses.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LiPin_Class");
			while (res.next()) {
				int id=res.getInt("Id");
				String name=res.getString("ClassName");
				int sortId=res.getInt("sortId");
				GiftClass gc=new GiftClass();
				gc.setId(id);
				gc.setClassName(name);
				gc.setSortId(sortId);
				giftClasses.add(gc);
			}
			return giftClasses;
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
	public GiftClass findIdByClass(int id) {
		//根据id获取分类信息
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_LiPin_Class WHERE Id="+id);
			while (res.next()) {
				String name=res.getString("ClassName");
				int sortId=res.getInt("sortId");
				GiftClass gc=new GiftClass();
				gc.setId(id);
				gc.setClassName(name);
				gc.setSortId(sortId);
				return gc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<GiftClass> findTopClass(int top) {
		try {
			giftClasses.clear();
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT TOP "+top+" * FROM JCP_LiPin_Class");
			while (res.next()) {
				int id=res.getInt("Id");
				String name=res.getString("ClassName");
				int sortId=res.getInt("sortId");
				GiftClass gc=new GiftClass();
				gc.setId(id);
				gc.setClassName(name);
				gc.setSortId(sortId);
				giftClasses.add(gc);
			}
			return giftClasses;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
