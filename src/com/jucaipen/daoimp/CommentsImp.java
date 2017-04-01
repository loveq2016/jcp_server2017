package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.CommentDao;
import com.jucaipen.model.Comment;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         JCP_LogOrLive_Comm 观点、文字直播 评论
 */
public class CommentsImp implements CommentDao {

	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<Comment> comments = new ArrayList<Comment>();

	/**
	 * @return 查询观点 文字直播总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_LogOrLive_Comm "
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
	public int insertComm(Comment comment) {
		// 添加评论 回复信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_LogOrLive_Comm(FK_UserId,Bodys,"
							+ "CommType,InsertDate,IsShow,Goods,RepCount,FK_LogOrLiveId,ParentId) VALUES("
							+ comment.getUserId()
							+ ",'"
							+ comment.getBodys()
							+ "',"
							+ comment.getCommType()
							+ ",'"
							+ comment.getInsertDate()
							+ "',"
							+ comment.getIsShow()
							+ ","
							+ comment.getGoods()
							+ ","
							+ comment.getRepCount()
							+ ","
							+ comment.getLogOrLiveId()
							+ ","
							+ comment.getParentId() + ")");
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
	public int cancelComm(int id) {
		// 删除评论
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("DELETE FROM JCP_LogOrLive_Comm WHERE Id="
					+ id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Comment> findComment(int uId, int type, int page, int parentId) {
		// 根据用户id获取相应类型下的评论 回复信息
		comments.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + uId
				+ " AND CommType=" + type + " AND ParentId=" + parentId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_LogOrLive_Comm WHERE FK_UserId="
							+ uId + " AND CommType=" + type + " AND ParentId="
							+ parentId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id"); // Id
				String bodys = res.getString("Bodys"); // Bodys
				String insertDate = res.getString("InsertDate"); // InsertDate
				int isShow = res.getInt("IsShow"); // IsShow
				int goods = res.getInt("Goods"); // Goods
				int repNum = res.getInt("RepCount"); // RepCount
				int fkId = res.getInt("FK_LogOrLiveId"); // FK_LogOrLiveId
				if (TimeUtils.isToday(insertDate)) {
					Comment comment = new Comment();
					comment.setId(id);
					comment.setBodys(bodys);
					comment.setInsertDate(insertDate);
					comment.setIsShow(isShow);
					comment.setGoods(goods);
					comment.setTotlePager(totlePage);
					comment.setPager(page);
					comment.setRepCount(repNum);
					comment.setLogOrLiveId(fkId);
					comment.setParentId(parentId);
					comments.add(comment);
				}
			}
			return comments;
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
	public List<Comment> findCommenBykId(int fkId, int page, int parentId,
			int type) {
		// 根据相关观点日志 文字直播获取评论 回复信息
		comments.clear();
		int totlePage = getTotlePage("WHERE FK_LogOrLiveId=" + fkId
				+ " AND CommType=" + type + " AND ParentId=" + parentId
				+ " AND IsShow=1");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_LogOrLive_Comm WHERE FK_LogOrLiveId="
							+ fkId + " AND CommType=" + type + " AND ParentId="
							+ parentId + " AND IsShow=1) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id"); // Id
				int userId = res.getInt("FK_UserId");
				String bodys = res.getString("Bodys"); // Bodys
				String insertDate = res.getString("InsertDate"); // InsertDate
				int isShow = res.getInt("IsShow"); // IsShow
				int goods = res.getInt("Goods"); // Goods
				int repNum = res.getInt("RepCount"); // RepCount
				Comment comment = new Comment();
				comment.setId(id);
				comment.setUserId(userId);
				comment.setBodys(bodys);
				comment.setInsertDate(insertDate);
				comment.setIsShow(isShow);
				comment.setGoods(goods);
				comment.setTotlePager(totlePage);
				comment.setPager(page);
				comment.setRepCount(repNum);
				comment.setLogOrLiveId(fkId);
				comment.setParentId(parentId);
				comments.add(comment);
			}
			return comments;
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
	public Comment findCommentById(int id) {
		// 根据id获取 评论 回复信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_LogOrLive_Comm WHERE Id="
					+ id);
			while (res.next()) {
				int userId = res.getInt(2); // FK_UserId
				String body = res.getString(3); // Bodys
				int commType = res.getInt(4); // CommType
				String insertDate = res.getString(5); // InsertDate
				int isShow = res.getInt(6); // IsShow
				int goods = res.getInt(7); // Goods
				int repCount = res.getInt(8); // RepCount
				int fkId = res.getInt(9); // FK_LogOrLiveId
				int parentId = res.getInt(10); // ParentId
				Comment comment = new Comment();
				comment.setUserId(userId);
				comment.setBodys(body);
				comment.setCommType(commType);
				comment.setInsertDate(insertDate);
				comment.setIsShow(isShow);
				comment.setGoods(goods);
				comment.setRepCount(repCount);
				comment.setLogOrLiveId(fkId);
				comment.setParentId(parentId);
				return comment;
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
	public int updateCommentGoods(int id, int goodNum) {
		// 修改评论 回复信息 点赞数
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_LogOrLive_Comm SET Goods="
					+ goodNum + " WHERE Id=" + id);
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
	public int updateCommentRespCount(int id, int respCount) {
		// 修改评论 回复数
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_LogOrLive_Comm SET RepCount="
					+ respCount + " WHERE Id=" + id);
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
	public List<Comment> findComment(int uId, int type, int page) {
		// 根据用户id获取相应类型下的评论 回复信息
		comments.clear();
		int totlePage = getTotlePage("WHERE FK_UserId=" + uId
				+ " AND CommType=" + type);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_LogOrLive_Comm WHERE FK_UserId="
							+ uId + " AND CommType=" + type + ") A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id"); // Id
				String bodys = res.getString("Bodys"); // Bodys
				String insertDate = res.getString("InsertDate"); // InsertDate
				int isShow = res.getInt("IsShow"); // IsShow
				int goods = res.getInt("Goods"); // Goods
				int repNum = res.getInt("RepCount"); // RepCount
				int fkId = res.getInt("FK_LogOrLiveId"); // FK_LogOrLiveId
				Comment comment = new Comment();
				comment.setId(id);
				comment.setBodys(bodys);
				comment.setInsertDate(insertDate);
				comment.setIsShow(isShow);
				comment.setGoods(goods);
				comment.setTotlePager(totlePage);
				comment.setPager(page);
				comment.setRepCount(repNum);
				comment.setLogOrLiveId(fkId);
				comments.add(comment);
			}
			return comments;
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
