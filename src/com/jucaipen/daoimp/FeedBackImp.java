package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jucaipen.dao.FeedBackDao;
import com.jucaipen.model.FeedBack;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 *
 *   Òâ¼û·´À¡
 */
public class FeedBackImp implements FeedBackDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private int isSuccess;
	private List<FeedBack> feedBacks = new ArrayList<FeedBack>();

	public int insertFeedBack(FeedBack feedBack) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("INSERT INTO JCP_Feedback "
					+ "(UserId,TrueName,MobileNum,Bodys,InsertDate,Ip,Types)"
					+ " VALUES (" + feedBack.getUserId() + ",'"
					+ feedBack.getTrueName() + "','" + feedBack.getMobileNum()
					+ "','" + feedBack.getBody() + "','"
					+ feedBack.getInsertDate() + "','" + feedBack.getIp()
					+ "'," + feedBack.getType() + ")");
			return isSuccess;
		} catch (Exception e) {
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public List<FeedBack> findAllFeedBack() {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Feedback ORDER BY handleDate DESC");
			feedBacks = getFeedBack(res);
			return feedBacks;
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

	public List<FeedBack> findFeedBaceByUserId(int uId) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Feedback WHERE UserId="
							+ uId + " ORDER BY handleDate DESC");
			feedBacks = getFeedBack(res);
			return feedBacks;
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

	public List<FeedBack> findFeedBackByType(int type) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Feedback WHERE Types="
							+ type + " ORDER BY handleDate DESC");
			feedBacks = getFeedBack(res);
			return feedBacks;
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

	public List<FeedBack> findFeedBackByUidAndType(int uId, int type) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT * FROM JCP_Feedback WHERE UserId="
							+ uId
							+ "AND Types="
							+ type
							+ " ORDER BY handleDate DESC");
			feedBacks = getFeedBack(res);
			return feedBacks;
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

	public FeedBack findFeedBackById(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_Feedback WHERE Id="
					+ id);
			feedBacks = getFeedBack(res);
			if (feedBacks.size() > 0) {
				return feedBacks.get(0);
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

	public List<FeedBack> getFeedBack(ResultSet result) {
		feedBacks.clear();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				int userId = result.getInt("UserId");
				String trueName = result.getString("TrueName");
				String mobileNum = result.getString("MobileNum");
				String bodys = result.getString("Bodys");
				String insertDate = result.getString("InsertDate");
				String ip = result.getString("Ip");
				int type = result.getInt("Types");
				String handleResult=result.getString("handleRemabrk");
				String handleDate=result.getString("handleDate");
				String handleMan=result.getString("handleRen");
				FeedBack feedBack = new FeedBack(id, userId, trueName,
						mobileNum, bodys, insertDate, ip, type);
				feedBack.setHandleDate(handleDate);
				feedBack.setHandleMan(handleMan);
				feedBack.setHandleResult(handleResult);
				feedBacks.add(feedBack);
			}
			return feedBacks;
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
