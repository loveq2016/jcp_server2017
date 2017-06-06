package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.FansDao;
import com.jucaipen.model.Fans;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 * 
 *         "SELECT TOP 15 * FROM " +
 *         "(SELECT ROW_NUMBER() OVER (ORDER BY AskDate desc) AS RowNumber,* FROM JCP_Ask) A "
 *         + "WHERE RowNumber > " + 15 * (page - 1)
 * 
 * 
 *         粉丝
 */
public class FansImp implements FansDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Fans> fans = new ArrayList<Fans>();

	/**
	 * @return 查询粉丝信息总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Fans "
							+ condition);
			res.next();
			int totlePager = res.getInt("totlePager");
			return totlePager;
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

	@Override
	public List<Fans> findFansByUid(int userId, int page) {
		// 根据用户id获取粉丝信息
		fans.clear();
		int totlePage = getTotlePage(" WHERE FK_UserId=" + userId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Fans WHERE FK_UserId="
							+ userId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int teacherId = res.getInt("FK_TearchId");
				String insertDate = res.getString("InsertDate");
				Fans fan = new Fans();
				fan.setTotlePage(totlePage);
				fan.setPage(page);
				fan.setTeacherId(teacherId);
				fan.setInsertDate(insertDate);
				fan.setId(id);
				fans.add(fan);
			}
			return fans;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Fans> findFansByTeacherId(int teacherId, int page) {
		// 根据讲师id获取粉丝信息
		fans.clear();
		int totlePage = getTotlePage(" WHERE FK_TearchId=" + teacherId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Fans WHERE FK_TearchId="
							+ teacherId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int userId = res.getInt("FK_UserId");
				String insertDate = res.getString("InsertDate");
				Fans fan = new Fans();
				fan.setTotlePage(totlePage);
				fan.setPage(page);
				fan.setUserId(userId);
				fan.setTeacherId(teacherId);
				fan.setInsertDate(insertDate);
				fan.setId(id);
				fans.add(fan);
			}
			return fans;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Fans findFansById(int id) {
		// 根据id获取粉丝信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Fans WHERE Id=" + id);
			while (res.next()) {
				int userId = res.getInt("FK_UserId");
				int teacherId = res.getInt("FK_TearchId");
				String insertDate = res.getString("InsertDate");
				Fans fan = new Fans();
				fan.setId(id);
				fan.setUserId(userId);
				fan.setTeacherId(teacherId);
				fan.setInsertDate(insertDate);
				fan.setId(id);
				return fan;
			}
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Fans findIsFans(int uId, int tId) {
		// 查询用户是否关注该讲师
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT Id FROM JCP_Fans WHERE FK_UserId="
					+ uId + " AND FK_TearchId=" + tId);
			while (res.next()) {
				int id = res.getInt(1);
				Fans fans = new Fans();
				fans.setId(id);
				return fans;
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
	public int addFans(Fans fans) {
		// 添加关注
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_Fans(FK_UserId,FK_TearchId,InsertDate,Ip) VALUES ("
							+ fans.getUserId()
							+ ","
							+ fans.getTeacherId()
							+ ",'"
							+ fans.getInsertDate()
							+ "','"
							+ fans.getIp() + "')");
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
	public int cancelFans(int tId,int uId) {
		// 取消关注
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("DELETE JCP_Fans WHERE FK_UserId=" + uId+" AND FK_TearchId="+tId);
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
	public List<Fans> findFansByUid(int userId) {
		// 根据讲师id获取粉丝信息
		fans.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Fans WHERE FK_UserId="+userId);
			while (res.next()) {
				int id = res.getInt("Id");
				int teacherId = res.getInt("FK_TearchId");
				String insertDate = res.getString("InsertDate");
				Fans fan = new Fans();
				fan.setUserId(userId);
				fan.setTeacherId(teacherId);
				fan.setInsertDate(insertDate);
				fan.setId(id);
				fans.add(fan);
			}
			return fans;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Fans> findFansByTeacherId(int teacherId) {
		// 根据讲师id获取粉丝信息
		fans.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT FK_UserId FROM JCP_Fans WHERE FK_TearchId="+teacherId);
			while (res.next()) {
				int userId = res.getInt(1);
				Fans fan = new Fans();
				fan.setUserId(userId);
				fans.add(fan);
			}
			return fans;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int getFansNum(int teacherId) {
		// 根据讲师id获取粉丝信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res=sta.executeQuery("SELECT COUNT(*) FROM JCP_Fans WHERE FK_TearchId="+teacherId);
			while (res.next()) {
				return res.getInt(1);
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
