package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.AskDao;
import com.jucaipen.model.Ask;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.TimeUtils;

public class AskImp implements AskDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;
	private List<Ask> asks = new ArrayList<Ask>();

	/**
	 * @return 查询提问总页数
	 */
	public int findTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Ask "
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

	public List<Ask> findAllAsk(int page) {
		// 获取搜友提问信息
		try {
			int totlePage = findTotlePage("");
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY AskDate ASC) AS RowNumber,* FROM JCP_Ask WHERE ParentId=0) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			asks = getAsk(res, page, totlePage);
			return asks;
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

	public List<Ask> findLstAsk(int count) {
		// 获取最近几条提问
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + count
					+ " * FROM JCP_Ask ORDER BY AskDate DESC");
			asks = getAsk(res, 0, 0);
			return asks;
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

	public int findAskNumByUId(int uId) {
		// 根据用户id获取用户的提问数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT COUNT (*) FROM JCP_Ask WHERE FK_UserId="
							+ uId);
			while (res.next()) {
				int askNum = res.getInt(1);
				return askNum;
			}
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public List<Ask> findAskByUserId(int userId, int page) {
		// 根据用户id获取提问信息
		try {
			int totlePage = findTotlePage("WHERE FK_UserId=" + userId);
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY AskDate desc) AS RowNumber,* FROM JCP_Ask"
							+ " WHERE FK_UserId=" + userId + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			asks = getAsk(res, page, totlePage);
			return asks;
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

	public List<Ask> findAskByUserId(int userId) {
		// 根据用户id获取提问信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM  JCP_Ask  WHERE FK_UserId="
					+ userId);
			asks = getAsk(res, 0, 0);
			return asks;
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
	public List<Ask> findLastByTeacherId(int teacherId, int count) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + count
					+ " * FROM JCP_Ask WHERE FK_TearchId=" + teacherId
					+ " ORDER BY AskDate desc");
			asks = getAsk(res, 0, 0);
			return asks;
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

	public List<Ask> findAskByTeacherId(int teacherId, int page) {
		// 根据讲师id获取提问信息
		int totlePage = findTotlePage("WHERE FK_TearchId=" + teacherId);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY AskDate desc) AS RowNumber,* FROM JCP_Ask"
							+ " WHERE FK_TearchId=" + teacherId
							+ " AND ParentId=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			asks = getAsk(res, page, totlePage);
			return asks;
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

	public List<Ask> findAskByClassId(int classId) {
		// 根据分类获取提问信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Ask WHERE FK_ClassId="
					+ classId + " ORDER BY AskDate DESC");
			asks = getAsk(res, 0, 0);
			return asks;
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

	public List<Ask> findAskByAskState(int isPay) {
		// 根据提问是否收费获取提问信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Ask WHERE IsPay=" + isPay
					+ " ORDER BY AskDate DESC");
			asks = getAsk(res, 0, 0);
			return asks;
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

	public List<Ask> findAskByIsReply(int isReply) {
		// 根据提问回复状态获取提问信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Ask WHERE IsReply="
					+ isReply + " ORDER BY AskDate DESC");
			asks = getAsk(res, 0, 0);
			return asks;
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

	public Ask findAskById(int id) {
		// 根据提问id获取提问信息
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Ask WHERE Id=" + id
					+ " ORDER BY AskDate DESC");
			asks = getAsk(res, 0, 0);
			if (asks.size() > 0) {
				return asks.get(0);
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

	public int insertAsk(Ask ask) {
		// 添加提问
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("INSERT INTO JCP_Ask"
					+ "(FK_UserId,ParentId,AskBodys,AskDate,"
					+ "AskState,Hits,IsReply,FK_TearchId,FK_ClassId,Ip,"
					+ "IsPay,PayJucaibi,AskFrom,IsReturnJcb,ReplyCount) "
					+ "VALUES("
					+ ask.getUserId()
					+ ","
					+ ask.getParentId()
					+ ",'"
					+ ask.getAskBody()
					+ "','"
					+ ask.getAskDate()
					+ "',"
					+ ask.getAskState()
					+ ","
					+ ask.getHits()
					+ ","
					+ ask.getIsReply()
					+ ","
					+ ask.getTeacherId()
					+ ","
					+ ask.getClassId()
					+ ",'"
					+ ask.getIp()
					+ "',"
					+ ask.getIsPay()
					+ ","
					+ ask.getJucaiBills()
					+ ","
					+ ask.getAskFrom()
					+ ","
					+ ask.getIsReturnJcb()
					+ ","
					+ ask.getReplyCount() + ")");
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public List<Ask> getAsk(ResultSet result, int page, int totlePage) {
		asks.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				int userId = result.getInt("FK_UserId");
				int parentId = result.getInt("ParentId");
				String bodys = result.getString("AskBodys");
				int classId = result.getInt("FK_ClassId");
				String askDate = result.getString("AskDate");
				int isPay = result.getInt("IsPay");
				int hits = result.getInt("Hits");
				int isReply = result.getInt("IsReply");
				String ip = result.getString("IP");
				int replyCount = result.getInt("ReplyCount");
				int teacherId = result.getInt("FK_TearchId");
				int xnHits = result.getInt("VirtualNum");
				Ask ask = new Ask();
				ask.setTotlePage(totlePage);
				ask.setPage(page);
				ask.setId(id);
				ask.setReplyCount(replyCount);
				ask.setUserId(userId);
				ask.setParentId(parentId);
				ask.setAskBody(bodys);
				ask.setXnHits(xnHits);
				ask.setClassId(classId);
				ask.setAskDate(askDate);
				ask.setTeacherId(teacherId);
				ask.setIp(ip);
				ask.setIsReply(isReply);
				ask.setHits(hits);
				ask.setIsPay(isPay);
				asks.add(ask);
			}
			return asks;
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
	public List<Ask> findAskByParentId(int pId) {
		asks.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT FK_UserId,AskBodys,AskDate,IsReply,FK_TearchId,IsPay,ReplyCount,Id FROM JCP_Ask WHERE ParentId="
							+ pId);
			while (res.next()) {
				int uId = res.getInt(1);
				String askBody = res.getString(2);
				String askDate = res.getString(3);
				int isReply = res.getInt(4);
				int tId = res.getInt(5);
				int isPay = res.getInt(6);
				int replyCount = res.getInt(7);
				int id = res.getInt(8);
				Ask ask = new Ask();
				ask.setId(id);
				ask.setUserId(uId);
				ask.setAskBody(askBody);
				ask.setAskDate(askDate);
				ask.setTeacherId(tId);
				ask.setIsPay(isPay);
				ask.setIsReply(isReply);
				ask.setReplyCount(replyCount);
				asks.add(ask);
			}
			return asks;
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
	public int updateHits(int id, int hits, int xnHits) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_Ask SET Hits=" + hits
					+ ",VirtualNum=" + xnHits + " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Ask> findAskByUidAndTeacherId(int userId, int teacherId) {
		asks.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,AskDate FROM JCP_Ask WHERE ParentId=0"
							+ " AND FK_UserId="
							+ userId
							+ " AND FK_TearchId="
							+ teacherId + " AND IsPay=0");
			while (res.next()) {
				int id = res.getInt(1);
				String askDate = res.getString(2);
				boolean isToday = TimeUtils.isToday(askDate);
				if (isToday) {
					Ask ask = new Ask();
					ask.setId(id);
					asks.add(ask);
				}
			}
			return asks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
