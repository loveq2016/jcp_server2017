package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.RecommderDao;
import com.jucaipen.model.Recommder;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *  推荐信息
 */
public class RecommderImp implements RecommderDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Recommder> recommders = new ArrayList<Recommder>();

	/**
	 * @return 查询返利积分总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TuiJian "
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
	public Recommder findRecommderById(int id) {
		// 根据id获取推荐信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_TuiJian WHERE Id="
							+ id);
			while (res.next()) {
				int userId = res.getInt(2); // UserId
				int parentId = res.getInt(3); // ParentUserId
				String insertDate = res.getString(4); // InsertDate
				String remark = res.getString(5); // Remark
				int type = res.getInt(6); // Type
				int integral = res.getInt(7); // Integral
				Recommder recommder = new Recommder();
				recommder.setUserId(userId);
				recommder.setType(type);
				recommder.setParentUserId(parentId);
				recommder.setInsertDate(insertDate);
				recommder.setRemsrk(remark);
				recommder.setIntegral(integral);
				return recommder;
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
	public List<Recommder> findRecommderByParentId(int parentId, int page) {
		//获取我推荐的用户信息
		recommders.clear();
		int totlePage = getTotlePage("WHERE ParentUserId=" + parentId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_TuiJian WHERE ParentUserId="
							+ parentId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id=res.getInt("Id");
				int userId = res.getInt("UserId"); // UserId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Remark"); // Remark
				int type = res.getInt("Type"); // Type
				int integral = res.getInt("Integral"); // Integral
				Recommder recommder = new Recommder();
				recommder.setUserId(userId);
				recommder.setType(type);
				recommder.setId(id);
				recommder.setTotlePage(totlePage);
				recommder.setPage(page);
				recommder.setParentUserId(parentId);
				recommder.setInsertDate(insertDate);
				recommder.setRemsrk(remark);
				recommder.setIntegral(integral);
				recommders.add(recommder);
			}
			return recommders;
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
	public int addRecommder(Recommder recommder) {
		//添加推荐信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_TuiJian(UserId,ParentUserId,InsertDate,Remark,Type,Integral) VALUES("
							+ recommder.getUserId()
							+ ","
							+ recommder.getParentUserId()
							+ ",'"
							+ recommder.getInsertDate()
							+ "','"
							+ recommder.getRemsrk()
							+ "',"
							+ recommder.getType()
							+ "," + recommder.getIntegral() + ")");
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
