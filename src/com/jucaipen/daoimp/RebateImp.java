package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.RebateDao;
import com.jucaipen.model.Rebate;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         返利信息
 */
public class RebateImp implements RebateDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Rebate> rebates = new ArrayList<Rebate>();

	/**
	 * @return 查询返利总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Rebate "
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
	public Rebate findRebateById(int id) {
		// 根据id获取返利信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Rebate WHERE Id=" + id);
			while (res.next()) {
				int teacherId = res.getInt(2); // FK_TearchId
				int type = res.getInt(3); // RebateType
				double rebateMoney = res.getDouble(4); // RebateMoney
				int fromId = res.getInt(5); // FK_FromUserId
				String insertDate = res.getString(6); // InsertDate
				String remark = res.getString(7); // Ramerk
				Rebate rebate = new Rebate();
				rebate.setTeacherId(teacherId);
				rebate.setType(type);
				rebate.setRebateMoney(rebateMoney);
				rebate.setFromId(fromId);
				rebate.setInsertDate(insertDate);
				rebate.setRemark(remark);
				return rebate;
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
		return null;
	}

	@Override
	public List<Rebate> findRebateByTid(int teacherId, int page) {
		// 根据讲师id获取返利信息
		rebates.clear();
		int totlePage = getTotlePage("WHERE FK_TearchId=" + teacherId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Rebate WHERE FK_TearchId="
							+ teacherId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int type = res.getInt("RebateType"); // RebateType
				double rebateMoney = res.getDouble("RebateMoney"); // RebateMoney
				int fromId = res.getInt("FK_FromUserId"); // FK_FromUserId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Ramerk"); // Ramerk
				Rebate rebate = new Rebate();
				rebate.setFromId(fromId);
				rebate.setType(type);
				rebate.setId(id);
				rebate.setTotlePage(totlePage);
				rebate.setPage(page);
				rebate.setRebateMoney(rebateMoney);
				rebate.setTeacherId(teacherId);
				rebate.setInsertDate(insertDate);
				rebate.setRemark(remark);
				rebates.add(rebate);
			}
			return rebates;
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
	public List<Rebate> findRebateByUserId(int userId, int page) {
		// 根据用户id获取返利信息
		rebates.clear();
		int totlePage = getTotlePage("WHERE FK_FromUserId=" + userId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Rebate WHERE FK_FromUserId="
							+ userId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				int type = res.getInt("RebateType"); // RebateType
				double rebateMoney = res.getDouble("RebateMoney"); // RebateMoney
				int teacherId = res.getInt("FK_TearchId"); // FK_FromUserId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Ramerk"); // Ramerk
				Rebate rebate = new Rebate();
				rebate.setTeacherId(teacherId);
				rebate.setType(type);
				rebate.setId(id);
				rebate.setTotlePage(totlePage);
				rebate.setPage(page);
				rebate.setRebateMoney(rebateMoney);
				rebate.setInsertDate(insertDate);
				rebate.setRemark(remark);
				rebates.add(rebate);
			}
			return rebates;
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
	public int addRebate(Rebate rebate) {
		// 添加返利信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_Rebate(FK_TearchId,RebateType,RebateMoney,FK_FromUserId,InsertDate,Ramerk) VALUES("
							+ rebate.getTeacherId()
							+ ","
							+ rebate.getType()
							+ ",'"
							+ rebate.getRebateMoney()
							+ "',"
							+ rebate.getFromId()
							+ ",'"
							+ rebate.getInsertDate()
							+ "','"
							+ rebate.getRemark() + "')");
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
	public List<Rebate> findRebateByTid(int teacherId) {
		// 获取讲师下的榜单信息
		rebates.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Rebate WHERE FK_TearchId="
							+ teacherId);
			while (res.next()) {
				int id = res.getInt("Id");
				int type = res.getInt("RebateType"); // RebateType
				double rebateMoney = res.getDouble("RebateMoney"); // RebateMoney
				int fromId = res.getInt("FK_FromUserId"); // FK_FromUserId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Ramerk"); // Ramerk
				Rebate rebate = new Rebate();
				rebate.setFromId(fromId);
				rebate.setType(type);
				rebate.setId(id);
				rebate.setRebateMoney(rebateMoney);
				rebate.setTeacherId(teacherId);
				rebate.setInsertDate(insertDate);
				rebate.setRemark(remark);
				rebates.add(rebate);
			}
			return rebates;
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
	public List<Rebate> findRebate(int uId, int tId) {
		// 获取用户贡献讲师聚财币信息
		rebates.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Rebate WHERE FK_TearchId="
							+ tId + " AND FK_FromUserId=" + uId);
			while (res.next()) {
				int id = res.getInt("Id");
				int type = res.getInt("RebateType"); // RebateType
				double rebateMoney = res.getDouble("RebateMoney"); // RebateMoney
				int fromId = res.getInt("FK_FromUserId"); // FK_FromUserId
				String insertDate = res.getString("InsertDate"); // InsertDate
				String remark = res.getString("Ramerk"); // Ramerk
				Rebate rebate = new Rebate();
				rebate.setType(type);
				rebate.setId(id);
				rebate.setRebateMoney(rebateMoney);
				rebate.setFromId(fromId);
				rebate.setInsertDate(insertDate);
				rebate.setRemark(remark);
				rebates.add(rebate);
			}
			return rebates;
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
	public int contributeBills(int uId, int tId) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT SUM(RebateMoney) from JCP_Rebate WHERE FK_FromUserId="
							+ uId + " AND FK_TearchId=" + tId);
			res.next();
			return res.getInt(1);
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
	public List<Rebate> findRebateByTeacher(int teacherId,String type) {
		// 获取讲师下的榜单信息
		rebates.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT SUM(RebateMoney) AS A,FK_FromUserId from JCP_Rebate WHERE FK_TearchId="
							+ teacherId
							+ "  AND   DATEDIFF("+type+", InsertDate, GETDATE())=0  GROUP BY FK_FromUserId ORDER BY A DESC");
			while (res.next()) {
				int rebateMoney = res.getInt(1); // RebateMoney
				int fromId = res.getInt(2); // FK_FromUserId
				Rebate rebate = new Rebate();
				rebate.setFromId(fromId);
				rebate.setRebateMoney(rebateMoney);
				rebate.setTeacherId(teacherId);
				rebates.add(rebate);
			}
			return rebates;
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
	public List<Rebate> findLastTeacherLast(String type) {
		// 获取讲师下的榜单信息
		rebates.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT SUM(RebateMoney) AS A,FK_TearchId from JCP_Rebate WHERE "
							+ " DATEDIFF("+type+", InsertDate, GETDATE())=0  GROUP BY FK_TearchId ORDER BY A DESC");
			while (res.next()) {
				int rebateMoney = res.getInt(1); // RebateMoney
				int teacherId = res.getInt(2); // FK_FromUserId
				Rebate rebate = new Rebate();
				rebate.setRebateMoney(rebateMoney);
				rebate.setTeacherId(teacherId);
				rebates.add(rebate);
			}
			return rebates;
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

}
