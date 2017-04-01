package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.LoginLogDao;
import com.jucaipen.daoimp.LoginLogImp;
import com.jucaipen.model.LoginLog;

public class LoginLogServer {
	/**
	 * @param userId
	 * @return 根据用户id查询登录日志
	 */
	public static List<LoginLog> findLogByUser(int userId, int pager) {
		LoginLogDao dao = new LoginLogImp();
		return dao.findLogByUser(userId, pager);
	}

	/**
	 * @param id
	 * @return 根据日志id，查询登录日志信息
	 */
	public static LoginLog findLogById(int id) {
		LoginLogDao dao = new LoginLogImp();
		return dao.findLog(id);
	}

	/**
	 * @param log
	 * @return  添加登录日志
	 */
	public static int insertLog(LoginLog log) {
		LoginLogDao dao = new LoginLogImp();
		return dao.insertLog(log);
	}

}
