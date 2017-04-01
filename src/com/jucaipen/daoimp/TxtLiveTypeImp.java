package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TxtLiveTypeDao;
import com.jucaipen.model.TxtLiveType;
import com.jucaipen.utils.JdbcUtil;

public class TxtLiveTypeImp implements TxtLiveTypeDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<TxtLiveType> types=new ArrayList<TxtLiveType>();

	@Override
	public List<TxtLiveType> findAll() {
		//获取所有文字直播分类信息
		types.clear();
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TxtLive_Type");
			while (res.next()) {
				int id=res.getInt(1); 
				String name=res.getString(2);  //TypeName
				String insertDate=res.getString(3);  //InsertDate
				int sortId=res.getInt(4);  //SortId
				TxtLiveType type=new TxtLiveType();
				type.setId(id);
				type.setName(name);
				type.setInsertDate(insertDate);
				type.setSortId(sortId);
				types.add(type);
			}
			return types;
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
	public TxtLiveType findTypeById(int id) {
		//根据id获取文字直播信息
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TxtLive_Type WHERE Id="+id);
			while (res.next()) {
				String name=res.getString(2);  //TypeName
				String insertDate=res.getString(3);  //InsertDate
				int sortId=res.getInt(4);  //SortId
				TxtLiveType type=new TxtLiveType();
				type.setId(id);
				type.setName(name);
				type.setInsertDate(insertDate);
				type.setSortId(sortId);
				return type;
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

}
