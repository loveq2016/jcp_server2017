package com.jucaipen.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jucaipen.dao.LoginLogDao;
import com.jucaipen.model.LoginLog;
import com.jucaipen.utils.JdbcUtil;
/**
 * @author YLF
 * 
 *         登录日志实现类
 * 
 */
public class LoginLogImp implements LoginLogDao {
	private Connection dbConn;
	private Statement sta;
	private ResultSet res;
	private List<LoginLog> logs;
	private int isSuccess;

	/**
	 * @return 查询登录日志总页数
	 */
	public int findTotlePager(String condition) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT  CEILING(COUNT(*)/15.0) as totlePager from JCP_UserLoginLog "
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

	/*
	 * 添加登录日志
	 */
	public int insertLog(LoginLog log) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("insert into JCP_UserLoginLog(UserId,"
					+ "LoginAccount,LoginPassWord,LoginDate,BrowserName,"
					+ "OS_Name,LoginIP,Result,Remark,LoginType,LoginUrl) VALUES("
					+log.getUserId()+",'"+log.getAccount()+"','"+log.getPassword()
					+"','"+log.getLoginDate()+"','"+log.getBrowserName()+"','"
					+log.getOsName()+"','"+log.getLoginIp()+"','"+log.getLoginResult()
					+"','"+log.getRemark()+"',"+log.getLoginType()+",'"+log.getLoginUrl()
					+"')");
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/*
	 * 删除登录日志
	 */
	public int deleteLog(int id) {
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			isSuccess = sta.executeUpdate("delete from JCP_UserLoginLog where Id="
					+ id);
			return isSuccess;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JdbcUtil.closeConn(sta, dbConn, res);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/*
	 * 根据id查询登录日志
	 */
	public LoginLog findLog(int id) {
		LoginLog log = null;
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta.executeQuery("select * from JCP_UserLoginLog where Id="
					+ id);
			logs = getLogs(res, -1, -1);
			if (logs != null && logs.size() > 0) {
				log = logs.get(0);
			}
			return log;
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

	/*
	 * 根据用户id，查询登录日志
	 */
	public List<LoginLog> findLogByUser(int userId, int pager) {
		int totlePager = findTotlePager("where UserId=" + userId);
		try {
			dbConn = JdbcUtil.connSqlServer();
			sta = dbConn.createStatement();
			res = sta
					.executeQuery("SELECT TOP 15 * FROM "
							+ "(SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM JCP_UserLoginLog"
							+ " where UserId=" + userId + " ) A "
							+ "WHERE RowNumber > " + 15 * (pager - 1));
			logs = getLogs(res, pager, totlePager);
			return logs;
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

	/**
	 * @param result
	 * @return 获取登录日志信息
	 */
	public List<LoginLog> getLogs(ResultSet result, int pager, int totlePager) {
		logs = new ArrayList<LoginLog>();
		try {
			while (result.next()) {
				int id = result.getInt("Id");
				int userId = result.getInt("UserId");
				String account = result.getString("LoginAccount");
				String password = result.getString("LoginPassWord");
				String loginDate = result.getString("LoginDate");
				int loginResult = result.getInt("Result");
				String loginIp = result.getString("LoginIP");
				String remark = result.getString("Remark");
				LoginLog log = new LoginLog();
				log.setId(id);
				log.setUserId(userId);
				log.setAccount(account);
				log.setPassword(password);
				log.setLoginDate(loginDate);
				log.setLoginResult(loginResult);
				log.setLoginIp(loginIp);
				log.setRemark(remark);
				log.setTotlePager(totlePager);
				log.setPager(pager);
				logs.add(log);
			}
			return logs;
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
