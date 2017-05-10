package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.TxtLiveDao;
import com.jucaipen.model.TextLive;
import com.jucaipen.utils.JdbcUtil;

public class TextLiveImp implements TxtLiveDao {
	private List<TextLive> textLives = new ArrayList<TextLive>();
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;

	/**
	 * @return 查询文字直播总页数
	 */
	public int findTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_TxtLive "
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

	public int insertTxtLive(TextLive textLive) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("INSERT INTO JCP_TxtLive (Title,StartDate,EndDate,Goods,Hits,FK_TearchId,IsEnd) VALUES ('"
							+ textLive.getTitle()
							+ "','"
							+ textLive.getStartDate()
							+ "','"
							+ textLive.getEndDate()
							+ "',"
							+ textLive.getGoods()
							+ ","
							+ textLive.getHits()
							+ ","
							+ textLive.getTeacherId()
							+ ","
							+ textLive.getIsEnd() + ")");
			return isSuccess;
		} catch (Exception e) {
		} finally {
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<TextLive> findAllNewTextLivesByPush() {
		textLives.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Id,Title,StartDate,TearchId FROM JCP_TxtLive");
			while (res.next()) {
				int id = res.getInt(1);
				String title = res.getString(2);
				String startDate = res.getString(3);
				int teacherId = res.getInt(4);
				TextLive textLive = new TextLive();
				textLive.setId(id);
				textLive.setTitle(title);
				textLive.setStartDate(startDate);
				textLive.setTeacherId(teacherId);
				textLives.add(textLive);
			}
			return textLives;
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

	public TextLive findTextLiveById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TxtLive WHERE Id=" + id);
			textLives = getTxtLive(res, 0, 0);
			if (textLives.size() > 0) {
				return textLives.get(0);
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

	public List<TextLive> findAllTextLive(int page) {
		textLives.clear();
		try {
			int totlePage = findTotlePage("");
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 Id,Title,StartDate,Hits,FK_TearchId FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY StartDate desc) AS RowNumber,* FROM JCP_TxtLive) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt(1);
				String title = res.getString(2);
				String startDate = res.getString(3);
				int hits = res.getInt(4);
				int teacherId = res.getInt(5);
				TextLive textLive = new TextLive();
				textLive.setId(id);
				textLive.setTitle(title);
				textLive.setTeacherId(teacherId);
				textLive.setStartDate(startDate);
				textLive.setHits(hits);
				textLive.setPage(page);
				textLive.setTotlePage(totlePage);
				textLives.add(textLive);
			}
			return textLives;
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

	public List<TextLive> findNewLiveByLastId(int lastId) {
		// 根据上次的id 获取最新的直播id
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TxtLive WHERE Id >"
					+ lastId + " ORDER BY StartDate");
			textLives = getTxtLive(res, 1, 1);
			return textLives;
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

	public List<TextLive> findLastPushLive(int count) {
		// 获取最近几条要推送的直播消息
		textLives.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " Id,Title,StartDate,FK_TearchId FROM JCP_TxtLive ORDER BY StartDate DESC");
			while (res.next()) {
				int id = res.getInt(1);
				String title = res.getString(2);
				String startDate = res.getString(3);
				int teacherId = res.getInt(4);
				TextLive textLive = new TextLive();
				textLive.setId(id);
				textLive.setTitle(title);
				textLive.setTeacherId(teacherId);
				textLive.setStartDate(startDate);
				textLives.add(textLive);
			}
			return textLives;
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

	public List<TextLive> findTxtLiveByTeacherIdAndLast(int teacherId, int count) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + count
					+ " * FROM JCP_TxtLive WHERE FK_TearchId=" + teacherId
					+ " ORDER BY StartDate DESC");
			textLives = getTxtLive(res, 0, 0);
			return textLives;
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

	public List<TextLive> findTextLiveByTeacherId(int teacherId, int page) {
		textLives.clear();
		int totlePage = findTotlePage("WHERE FK_TearchId=" + teacherId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 Id,Title,StartDate,Hits,FK_TearchId,IsEnd,VirtualNum FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY StartDate desc) AS RowNumber,"
							+ "* FROM JCP_TxtLive WHERE FK_TearchId="
							+ teacherId
							+ ") A "
							+ "WHERE RowNumber > "
							+ 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				String startDate = res.getString("StartDate");
				int hits = res.getInt("Hits");
				int isEnd = res.getInt("IsEnd");
				int xnHits=res.getInt("VirtualNum");
				TextLive textLive = new TextLive();
				textLive.setId(id);
				textLive.setPage(page);
				textLive.setTitle(title);
				textLive.setHits(hits);
				textLive.setXnHits(xnHits);
				textLive.setTotlePage(totlePage);
				textLive.setIsEnd(isEnd);
				textLive.setStartDate(startDate);
				textLives.add(textLive);
			}
			return textLives;
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

	public List<TextLive> findTextLiveByIsEnd(int isEnd) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_TxtLive WHERE IsEnd="
					+ isEnd + " ORDER BY StartDate DESC");
			textLives = getTxtLive(res, 0, 0);
			return textLives;
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

	public List<TextLive> getTxtLive(ResultSet result, int page, int totlePage) {
		textLives.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				String title = result.getString("Title");
				String startDate = result.getString("StartDate");
				String endDate = result.getString("EndDate");
				int goods = result.getInt("Goods");
				int xnHits = result.getInt("VirtualNum");
				int teacherId = result.getInt("FK_TearchId");
				int isEnd = result.getInt("IsEnd");
				int hits = result.getInt("Hits");
				TextLive textLive = new TextLive();
				textLive.setId(id);
				textLive.setHits(hits);
				textLive.setTitle(title);
				textLive.setStartDate(startDate);
				textLive.setEndDate(endDate);
				textLive.setGoods(goods);
				textLive.setXnHits(xnHits);
				textLive.setIsEnd(isEnd);
				textLive.setTeacherId(teacherId);
				textLive.setPage(page);
				textLive.setTotlePage(totlePage);
				textLives.add(textLive);
			}
			return textLives;
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
	public int addHits(int hits, int id, int xnHits) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_TxtLive SET Hits=" + hits
					+ ",VirtualNum=" + xnHits + " WHERE Id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public TextLive findHitsAndGoods(int id) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT Goods,Hits,VirtualNum FROM JCP_TxtLive WHERE Id="+id);
			while (res.next()) {
				int goods=res.getInt(1);
				int hits=res.getInt(2);
				int xnHits=res.getInt(3);
				TextLive live=new TextLive();
				live.setGoods(goods);
				live.setHits(hits);
				live.setXnHits(xnHits);
				return live;
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
	public int addGoods(int id, int goods) {
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_TxtLive SET Goods=" + goods
					+ " WHERE Id=" + id);
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
	public List<TextLive> findLastDateLive(String date) {
		//获取前一天直播信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT * FROM JCP_TxtLive WHERE Convert(varchar,StartDate,120) LIKE '"+date+"%'");
			textLives = getTxtLive(res, 0, 0);
			return textLives;
		} catch (Exception e) {
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
