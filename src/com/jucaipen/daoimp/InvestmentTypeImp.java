package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.InvestmentTypeDao;
import com.jucaipen.model.InvestmentType;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         投资风格
 */
public class InvestmentTypeImp implements InvestmentTypeDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<InvestmentType> types = new ArrayList<InvestmentType>();

	@Override
	public InvestmentType findTypeById(int id) {
		// 根据id获取投资风格信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT ISNULL(TypeName,''),SortId FROM JCP_InvestmentType WHERE Id="
					+ id);
			while (res.next()) {
				String name = res.getString(1); // TypeName
				int sortId = res.getInt(2); // SortId
				InvestmentType type = new InvestmentType();
				type.setId(id);
				type.setTypeName(name);
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

	@Override
	public List<InvestmentType> findAllTypes() {
		// 获取所有的风格信息
		types.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_InvestmentType");
			while (res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2); // TypeName
				int sortId = res.getInt(3); // SortId
				InvestmentType type = new InvestmentType();
				type.setId(id);
				type.setTypeName(name);
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

}
