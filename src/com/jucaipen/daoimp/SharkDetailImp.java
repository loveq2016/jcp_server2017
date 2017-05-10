package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.SharkDetailDao;
import com.jucaipen.model.SharkDetail;
import com.jucaipen.utils.JdbcUtil;

public class SharkDetailImp implements SharkDetailDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<SharkDetail> sharks = new ArrayList<SharkDetail>();

	@Override
	public List<SharkDetail> getSharkList(int uId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Activity_Detail WHERE UserId="
							+ uId);
			while (res.next()) {
				int id = res.getInt(1);
				int price = res.getInt(3);
				int pathWay = res.getInt(4);
				String insertDate = res.getString(5);
				String remark = res.getString(6);
				SharkDetail detail = new SharkDetail(id, uId, price, pathWay,
						insertDate, remark);
				sharks.add(detail);
			}
			return sharks;
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
	public int getSharkCount(int uId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT COUNT(*) FROM JCP_Activity_Detail WHERE UserId="
							+ uId+" AND DATEDIFF(day,InsertDate,GETDATE())=0");
			while (res.next()) {
				return  res.getInt(1);
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
		return 0;
	}

	@Override
	public int addDetail(SharkDetail detail) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			return sta.executeUpdate("INSERT INTO JCP_Activity_Detail"
					+ " (UserId,Price,Pathway,InsertDate,Remark) VALUES("
					+ detail.getUserId() + "," + detail.getPrice() + ","
					+ detail.getPathWay() + ",'" + detail.getInsertDate()
					+ "','" + detail.getRemark() + "')");
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
	public int getMaxCount(int min, int max) {
		try {
			dbConn=JdbcUtil.connSqlServer();
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT COUNT(*) from JCP_Activity_Detail WHERE Price>"+min+" AND Price<="+max);
			while (res.next()) {
				return res.getInt(1);
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
		return 0;
	}

}
