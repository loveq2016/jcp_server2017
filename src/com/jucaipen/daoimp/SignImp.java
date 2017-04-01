package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jucaipen.dao.SignDao;
import com.jucaipen.model.Sign;
import com.jucaipen.utils.JdbcUtil;

/**
 * @author Administrator
 * 
 *         签到
 */
public class SignImp implements SignDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	//private List<Sign> signs = new ArrayList<Sign>();

	@Override
	public Sign findSignByUid(int userId) {
		// 根据用户id获取签到信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			res = sta.executeQuery("SELECT * FROM JCP_QianDao WHERE UserId="
					+ userId);
			while (res.next()) {
				int id = res.getInt(1);
				String lastDate = res.getString(3); // LastDate
				int signNum = res.getInt(5); // QDCount
				Sign sign = new Sign();
				sign.setId(id);
				sign.setLastDate(lastDate);
				sign.setSignNum(signNum);
				return sign;
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
	public int addSign(Sign sign) {
		// 添加签到信息
		dbConn = JdbcUtil.connSqlServer();
		try {
			sta = dbConn.createStatement();
			return sta
					.executeUpdate("INSERT INTO JCP_QianDao(UserId,LastDate,Ip,QDCount) VALUES ("
							+ sign.getUserId()
							+ ",'"
							+ sign.getLastDate()
							+ "','"
							+ sign.getIp()
							+ "',"
							+ sign.getSignNum()
							+ ")");
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
	public int updateSign(Sign sign) {
		// 更新签到总表
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			return sta.executeUpdate("UPDATE JCP_QianDao SET LastDate='"+sign.getLastDate()+"',Ip='"
					+sign.getIp()+"',QDCount="+sign.getSignNum()+" WHERE UserId="+sign.getUserId());
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
	public int findSignCount(int uId) {
		// 获取签到次数
		dbConn=JdbcUtil.connSqlServer();
		try {
			sta=dbConn.createStatement();
			res=sta.executeQuery("SELECT QDCount FROM JCP_QianDao WHERE UserId="+uId);
			while (res.next()) {
				return res.getInt(1);
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
		return 0;
	}
}
