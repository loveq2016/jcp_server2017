package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.HotIdeaDao;
import com.jucaipen.model.HotIdea;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 * 
 *         日志，热门观点 -----------------------JCP_Tearch_Log ---按点击量排列
 */
public class HotIdeaImp implements HotIdeaDao {
	private List<HotIdea> ideas = new ArrayList<HotIdea>();
	private Connection dbConn;
	private ResultSet res;
	private Statement sta;
	private int isSuccess;

	/**
	 * @return 查询新闻总页数
	 */
	public int findTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_Tearch_Log "
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
		return -1;

	}

	public int addHit(int ideaId, int hits, int xnHits) {
		// 添加点赞数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_Tearch_Log SET Hits="
					+ hits + ",VirtualNum=" + xnHits + " WHERE Id=" + ideaId);
			return isSuccess;
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

	public int addComment(int ideaId, int commCount) {
		// 添加评论数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta
					.executeUpdate("UPDATE JCP_Tearch_Log SET CommCount="
							+ commCount + " WHERE Id=" + ideaId);
			return isSuccess;
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

	public int addGood(int ideaId, int googs) {
		// 添加点赞数
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("UPDATE JCP_Tearch_Log SET Goods="
					+ googs + " WHERE Id=" + ideaId);
			return isSuccess;
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

	public HotIdea findGoodAndHitAndComm(int id) {
		// 获取点赞数、点击数和评论数
		HotIdea idea = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Goods,CommCount,Hits FROM JCP_Tearch_Log WHERE Id="
							+ id);
			while (res.next()) {
				int goods = res.getInt("Goods");
				int comms = res.getInt("CommCount");
				int hits = res.getInt("Hits");
				idea = new HotIdea();
				idea.setId(id);
				idea.setHits(hits);
				idea.setCommens(comms);
				idea.setGoods(goods);
				return idea;
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

	public List<HotIdea> findAllHotIdea(int page) {
		ideas.clear();
		try {
			int totlePage = findTotlePage("");
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 Id,InsertDate,Title,Bodys,VirtualNum,FK_TearchId,Goods,CommCount FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Tearch_Log) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String insertDate = res.getString("InsertDate");
				String title = res.getString("Title");
				String body = res.getString("Bodys");
				int xnHits = res.getInt("VirtualNum");
				int teacherId = res.getInt("FK_TearchId");
				int goods = res.getInt("Goods");
				int commCount = res.getInt("CommCount");
				HotIdea idea = new HotIdea();
				idea.setId(id);
				idea.setCommens(commCount);
				idea.setTotlePgae(totlePage);
				idea.setPage(page);
				idea.setInsertDate(insertDate);
				idea.setBodys(body);
				idea.setXnHits(xnHits);
				idea.setTitle(title);
				idea.setGoods(goods);
				idea.setTeacherId(teacherId);
				ideas.add(idea);
			}
			return ideas;
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

	public List<HotIdea> findIdeaByCount(int count) {
		ideas.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " Id,ISNULL (Title,'') Title,ISNULL (Bodys,'') Bodys,ISNULL (ImagesUrl,'') ImagesUrl FROM JCP_Tearch_Log  WHERE LEN(LogImg)>0 ORDER BY InsertDate DESC");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				String bodys = res.getString("Bodys");
				String logImage = res.getString("ImagesUrl");
				HotIdea idea = new HotIdea();
				idea.setId(id);
				idea.setLogImage(logImage);
				idea.setBodys(bodys);
				idea.setTitle(title);
				ideas.add(idea);
			}
			return ideas;
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
	public List<HotIdea> findLastIdeaByTeacherId(int teacherId, int count) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT TOP " + count
					+ " * FROM JCP_Tearch_Log WHERE FK_TearchId=" + teacherId
					+ " ORDER BY InsertDate desc");
			ideas = getHotIdea(res, 0, 0);
			return ideas;
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

	public List<HotIdea> findIdeaByTeacherId(int teacherId, int page) {
		try {
			int totlePage = findTotlePage(" WHERE FK_TearchId=" + teacherId);
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_Tearch_Log"
							+ " WHERE FK_TearchId=" + teacherId + " )A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			ideas = getHotIdea(res, page, totlePage);
			return ideas;
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

	public HotIdea findIdeaById(int id) {
		ideas.clear();
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT Title,Bodys,InsertDate,Goods,FK_TearchId,ImagesUrl,Hits,VirtualNum,IsFree,PayJucaibi,FreeBody FROM JCP_Tearch_Log WHERE Id="
							+ id + " ORDER BY InsertDate DESC");
			while (res.next()) {
				String title = res.getString(1);
				String body = res.getString(2);
				String insertDate = res.getString(3);
				int goods = res.getInt(4);
				int teacherId = res.getInt(5);
				String logImage = res.getString(6);
				int hits = res.getInt(7);
				int xnHits = res.getInt(8);
				int isFree = res.getInt(9);
				int jucaiBills = res.getInt(10);
				String freeBody = res.getString(11);
				HotIdea idea = new HotIdea();
				idea.setId(id);
				idea.setBodys(body);
				idea.setXnHits(xnHits);
				idea.setLogImage(logImage);
				idea.setTitle(title);
				idea.setInsertDate(insertDate);
				idea.setGoods(goods);
				idea.setHits(hits);
				idea.setIsFree(isFree);
				idea.setJucaiBills(jucaiBills);
				idea.setFreeBody(freeBody);
				idea.setTeacherId(teacherId);
				return idea;
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

	public List<HotIdea> getHotIdea(ResultSet result, int page, int totlePage) {
		ideas.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				String title = result.getString("Title");
				String bodys = result.getString("Bodys");
				int goods = result.getInt("Goods");
				int hits = result.getInt("Hits");
				int comms = result.getInt("CommCount");
				int classId = result.getInt("FK_ClassId");
				String insertDate = result.getString("InsertDate");
				int teacherId = result.getInt("FK_TearchId");
				String logImage = result.getString("ImagesUrl");
				int isFree = result.getInt("IsFree");
				int xnHits = result.getInt("VirtualNum");
				HotIdea idea = new HotIdea();
				idea.setPage(page);
				idea.setTotlePgae(totlePage);
				idea.setId(id);
				idea.setBodys(bodys);
				idea.setXnHits(xnHits);
				idea.setTitle(title);
				idea.setGoods(goods);
				idea.setHits(hits);
				idea.setIsFree(isFree);
				idea.setCommens(comms);
				idea.setClassId(classId);
				idea.setInsertDate(insertDate);
				idea.setTeacherId(teacherId);
				idea.setLogImage(logImage);
				ideas.add(idea);
			}
			return ideas;
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
	public List<HotIdea> findIndexIdea(int count) {
		ideas.clear();
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP "
							+ count
							+ " * FROM JCP_Tearch_Log WHERE IsTuiJian=1 AND IsIndex=1 AND ImagesUrl <> ''  ORDER BY InsertDate DESC");
			while (res.next()) {
				int id = res.getInt("Id");
				String title = res.getString("Title");
				String logImage = res.getString("ImagesUrl");
				String insertDate = res.getString("InsertDate");
				int xnHits = res.getInt("VirtualNum");
				HotIdea idea = new HotIdea();
				idea.setId(id);
				idea.setXnHits(xnHits);
				idea.setLogImage(logImage);
				idea.setInsertDate(insertDate);
				idea.setTitle(title);
				ideas.add(idea);
			}
			return ideas;
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
