package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.NewSmallClassDao;
import com.jucaipen.model.NewSmallClass;
import com.jucaipen.utils.JdbcUtil;

public class NewSmallClassImp implements NewSmallClassDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<NewSmallClass> smallClasses = new ArrayList<NewSmallClass>();

	@Override
	public List<NewSmallClass> findAll() {
		smallClasses.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCPNewsSmallClass ORDER BY PxId ASC");
			while (res.next()) {
				int id=res.getInt(1);
				int bigId = res.getInt(2); // BigId
				String smallName = res.getString(3); // SmallName
				NewSmallClass small = new NewSmallClass();
				small.setBigId(bigId);
				small.setId(id);
				small.setSmallName(smallName);
				smallClasses.add(small);
			}
			return smallClasses;
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
	public List<NewSmallClass> findClassByBigId(int bigId) {
		smallClasses.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCPNewsSmallClass WHERE BigId="
							+ bigId + " ORDER BY PxId ASC");
			while (res.next()) {
				int id=res.getInt(1);
				String smallName = res.getString(3); // SmallName
				NewSmallClass small = new NewSmallClass();
				small.setBigId(bigId);
				small.setId(id);
				small.setSmallName(smallName);
				smallClasses.add(small);
			}
			return smallClasses;
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
	public NewSmallClass findClassById(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCPNewsSmallClass WHERE Id="
					+ id);
			while (res.next()) {
				int bigId=res.getInt(2);
				String smallName = res.getString(3); // SmallName
				NewSmallClass small = new NewSmallClass();
				small.setBigId(bigId);
				small.setSmallName(smallName);
				return small;
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
