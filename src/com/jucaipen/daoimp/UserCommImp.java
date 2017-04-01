package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.UserCommDao;
import com.jucaipen.model.UserComm;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         0证券知识，1为视频 JCP_User_Comm
 * 
 */
public class UserCommImp implements UserCommDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<UserComm> comments = new ArrayList<UserComm>();
	/**
	 * @return 查询证券知识 视频总页数
	 */
	public int getTotlePage(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_User_Comm "
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
	public int insertComm(UserComm comment) {
		// 添加评论信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_User_Comm (UserId,Bodys,ParentId,"
							+ "Type,NorVId,InsertDate,Goods,IsShow,RepCount) VALUES("
							+ comment.getUserId()
							+ ",'"
							+ comment.getBodys()
							+ "',"
							+ comment.getParentId()
							+ ","
							+ comment.getType()
							+ ","
							+ comment.getNovId()
							+ ",'"
							+ comment.getInsertDate()
							+ "',"
							+ comment.getGoods()
							+ ","
							+ comment.getIsShow()
							+ "," + comment.getReplyCount() + ")");
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
		// 删除评论信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("DELETE FROM JCP_User_Comm WHERE Id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserComm> findComment(int uId, int type, int page, int parentId) {
		// 根据用户id获取分类下的评论信息
		comments.clear();
		int totlePage = getTotlePage("WHERE UserId=" + uId + " AND Type="
				+ type + " AND ParentId=" + parentId);
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_User_Comm WHERE UserId="
							+ uId + " AND Type=" + type + " AND ParentId="
							+ parentId + ") A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String body = res.getString("Bodys");
				int fkId = res.getInt("NorVId");
				String insertDate = res.getString("InsertDate");
				int goods = res.getInt("Goods");
				int isShow = res.getInt("IsShow");
				int replyCount = res.getInt("RepCount");
				if (TimeUtils.isToday(insertDate)) {
					UserComm comm = new UserComm();
					comm.setId(id);
					comm.setBodys(body);
					comm.setTotlePage(totlePage);
					comm.setPage(page);
					comm.setNovId(fkId);
					comm.setInsertDate(insertDate);
					comm.setGoods(goods);
					comm.setIsShow(isShow);
					comm.setReplyCount(replyCount);
					comments.add(comm);
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
	public List<UserComm> findCommenBykId(int fkId, int page, int parentId,
			int type) {
		// 根据证券知识 视频 获取评论 回复信息
		comments.clear();
		int totlePage = getTotlePage("WHERE NorVId=" + fkId + " AND Type="
				+ type + " AND ParentId=" + parentId+" AND IsShow=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_User_Comm WHERE NorVId="
							+ fkId + " AND Type=" + type + " AND ParentId="
							+ parentId + " AND IsShow=0) A " + "WHERE RowNumber > " + 15
							* (page - 1));
			while (res.next()) {
				int userId = res.getInt("UserId");
				int id = res.getInt("Id");
				String body = res.getString("Bodys");
				String insertDate = res.getString("InsertDate");
				int goods = res.getInt("Goods");
				int isShow = res.getInt("IsShow");
				int replyCount = res.getInt("RepCount");
				UserComm comm = new UserComm();
				comm.setId(id);
				comm.setUserId(userId);
				comm.setBodys(body);
				comm.setTotlePage(totlePage);
				comm.setPage(page);
				comm.setNovId(fkId);
				comm.setInsertDate(insertDate);
				comm.setGoods(goods);
				comm.setIsShow(isShow);
				comm.setReplyCount(replyCount);
				comments.add(comm);
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
	public UserComm findCommentById(int id) {
		// 根据id获取评论信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_User_Comm WHERE Id=" + id);
			while (res.next()) {
				int userId = res.getInt("UserId");
				String body = res.getString("Bodys");
				String insertDate = res.getString("InsertDate");
				int goods = res.getInt("Goods");
				int isShow = res.getInt("IsShow");
				int replyCount = res.getInt("RepCount");
				int fkId = res.getInt("NorVId");
				UserComm comm = new UserComm();
				comm.setId(id);
				comm.setUserId(userId);
				comm.setBodys(body);
				comm.setNovId(fkId);
				comm.setInsertDate(insertDate);
				comm.setGoods(goods);
				comm.setIsShow(isShow);
				comm.setReplyCount(replyCount);
				return comm;
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
		// 修改点赞数
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_User_Comm SET Goods="
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
		// 修改回复数
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_User_Comm SET RepCount="
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
	public List<UserComm> findComment(int uId, int type, int page) {
		// 根据用户id获取分类下的评论信息
		comments.clear();
		int totlePage = getTotlePage("WHERE UserId=" + uId + " AND Type="
				+ type+" AND IsShow=0");
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY InsertDate desc) AS RowNumber,* FROM JCP_User_Comm WHERE UserId="
							+ uId + " AND Type=" + type + " AND IsShow=0) A "
							+ "WHERE RowNumber > " + 15 * (page - 1));
			while (res.next()) {
				int id = res.getInt("Id");
				String body = res.getString("Bodys");
				int fkId = res.getInt("NorVId");
				String insertDate = res.getString("InsertDate");
				int goods = res.getInt("Goods");
				int isShow = res.getInt("IsShow");
				int replyCount = res.getInt("RepCount");
				UserComm comm = new UserComm();
				comm.setId(id);
				comm.setBodys(body);
				comm.setTotlePage(totlePage);
				comm.setPage(page);
				comm.setNovId(fkId);
				comm.setInsertDate(insertDate);
				comm.setGoods(goods);
				comm.setIsShow(isShow);
				comm.setReplyCount(replyCount);
				comments.add(comm);
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
