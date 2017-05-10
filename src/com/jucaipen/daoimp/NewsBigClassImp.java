package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.NewsBigClassDao;
import com.jucaipen.model.NewsBigClass;
import com.jucaipen.utils.JdbcUtil;

public class NewsBigClassImp implements NewsBigClassDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<NewsBigClass> bigClasses = new ArrayList<NewsBigClass>();

	@Override
	public List<NewsBigClass> findAll() {
		bigClasses.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCPNewsBigClass ORDER BY PxId");
			while (res.next()) {
				int id = res.getInt(1);
				String bigName = res.getString(2);
				NewsBigClass big = new NewsBigClass();
				big.setBigName(bigName);
				big.setId(id);
				bigClasses.add(big);
			}
			return bigClasses;
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
	public NewsBigClass findClassById(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FRM JCPNewsBigClass WHERE Id="
					+ id);
			while (res.next()) {
				String bigName = res.getString(2);
				NewsBigClass big = new NewsBigClass();
				big.setId(id);
				big.setBigName(bigName);
				return big;
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
