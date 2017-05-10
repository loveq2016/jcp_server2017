package com.jucaipen.daoimp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jucaipen.dao.SharkInfoDao;
import com.jucaipen.model.SharkInfo;
import com.jucaipen.utils.JdbcUtil;

public class SharkInfoImp implements SharkInfoDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	@Override
	public SharkInfo getSharkInfo() {
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Activity WHERE GETDATE() between StartDate and EndDate");
		    while (res.next()) {
		    	int id=res.getInt(1);
		    	int price=res.getInt(2);
		    	int syPrice=res.getInt(3);
		    	String startDate=res.getString(4);
		    	String endDate=res.getString(5);
		    	SharkInfo info=new SharkInfo();
		    	info.setId(id);
		    	info.setPrice(price);
		    	info.setStartDate(startDate);
		    	info.setEndDate(endDate);
		    	info.setSyPrice(syPrice);
		    	return info;
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
	public int updatePrice(int rest) {
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Activity SET SYPrice="+rest+" WHERE GETDATE() between StartDate and EndDate");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	@Override
	public SharkInfo getSharkByIsEnd(String date) {
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_Activity WHERE  convert(varchar(10),StartDate,120)='"+date+"'");
			while (res.next()) {
				int id=res.getInt(1);
		    	int price=res.getInt(2);
		    	int syPrice=res.getInt(3);
		    	String startDate=res.getString(4);
		    	String endDate=res.getString(5);
		    	SharkInfo info=new SharkInfo();
		    	info.setId(id);
		    	info.setPrice(price);
		    	info.setStartDate(startDate);
		    	info.setEndDate(endDate);
		    	info.setSyPrice(syPrice);
		    	return info;
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
