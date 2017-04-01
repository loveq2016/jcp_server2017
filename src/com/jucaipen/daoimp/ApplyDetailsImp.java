package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jucaipen.dao.ApplyDetailsDao;
import com.jucaipen.model.ApplyDetails;
import com.jucaipen.utils.JdbcUtil;

public class ApplyDetailsImp implements ApplyDetailsDao {
	private Connection dbConn;
	private Statement sta;

	private ResultSet res;

	@Override
	public ApplyDetails findDetailsByApplyId(int applyId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_ShenHe_Detail WHERE FK_ApplyId="
							+ applyId);
			while (res.next()) {
				int id = res.getInt(1);
				String cause = res.getString(3);
				String passDate = res.getString(4);
				ApplyDetails details = new ApplyDetails();
				details.setId(id);
				details.setCause(cause);
				details.setPassDate(passDate);
				return details;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
