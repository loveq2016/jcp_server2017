package com.jucaipen.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ServerManager;
import com.jucaipen.utils.JdbcUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         登录后台管理系统
 * 
 */
public class LoginServer extends HttpServlet {
	private static final long serialVersionUID = -2446747156672320315L;
	private String result;
	private Connection dbConn; 
	private ServerManager manager;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {/*
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (StringUtil.isNotNull(account)) {
			if (StringUtil.isNotNull(password)) {
				// 查询用户信息
				manager = querryServerManagerInfo(account);
				if (manager != null) {
					// 当前账号存在
					String pwd = manager.getPassword();
					if (password.equals(pwd)) {
						// 登录成功
						request.getRequestDispatcher("index.jsp").forward(
								request, response);
					} else {
						// 密码错误
						result = JsonUtil.getRetMsg(3, "密码输入错误");
					}
				} else {
					// 当前账号不存在
					result = JsonUtil.getRetMsg(4, "账号输入错误");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "密码不能为空");
			}
		} else {
			result = JsonUtil.getRetMsg(2, "账号不能为空");
		}
		out.print(result);
		out.flush();
		out.close();
	*/}

/*	private ServerManager querryServerManagerInfo(String account) {
		// 查询管理员信息
		try {
			dbConn = JdbcUtil.connMySql();
			Statement sta = dbConn.createStatement();
			ResultSet res = sta
					.executeQuery("select * from manager where userName='"
							+ account + "'");
			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String telPhone = res.getString("telPhone");
				String userName = res.getString("userName");
				String password = res.getString("password");
				manager = new ServerManager();
				manager.setId(id);
				manager.setName(name);
				manager.setTelPhone(telPhone);
				manager.setUserName(userName);
				manager.setPassword(password);
			}
			return manager;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
*/
}
