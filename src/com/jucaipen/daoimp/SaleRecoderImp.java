package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jucaipen.dao.SaleRecoderDao;
import com.jucaipen.utils.JdbcUtil;

public class SaleRecoderImp implements SaleRecoderDao {

	private Connection dbConn;
	private Statement sta;
	private ResultSet res;

	@Override
	public int findSaleCountByRecoder(int uId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  COUNT(Id) AS num FROM  JCP_ShiKanSale  WHERE  UserId="
							+ uId);
			while (res.next()) {
				int num = res.getInt("num");
				return num;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

}
