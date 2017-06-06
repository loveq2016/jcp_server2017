<%@page import="com.jucaipen.utils.JdbcUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户管理首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
	<%
		Connection conn = JdbcUtil.connSqlServer();
		Statement sta = conn.createStatement();
		ResultSet res = sta.executeQuery("select TOP 10 * from JCP_User where RegFrom="+5);
	%>
	<div style="text-align: center;">
		<font size="5" color="pink">用户管理中心</font>
	</div>
	<br>
	<div align="center">
	<table border="1" bordercolor="gray">
		<tr>
			<td width="10%" align="center">id</td>
			<td width="10%" align="center">用户名</td>
			<td width="10%" align="center">密码</td>
			<td width="10%" align="center">真实姓名</td>
			<td width="10%" align="center">昵称</td>
			<td width="10%" align="center">Email</td>
			<td width="10%" align="center">头像URL</td>
			<td width="10%" align="center">手机号</td>
			<td width="10%" align="center">性别</td>
		</tr>
		<%
			while(res.next()) {
		%>
		<tr>
			<td align="center"><%=res.getInt("Id")%></td>
			<td align="center"><%=res.getString("UserName")%></td>
			<td align="center"><%=res.getString("PassWord")%></td>
			<td align="center"><%=res.getString("TrueName")%></td>
			<td align="center"><%=res.getString("NickName")%></td>
			<td align="center"><%=res.getString("Email")%></td>
			<td align="center"><%=res.getString("UserFace")%></td>
			<td align="center"><%=res.getString("MobileNum")%></td>
			<td align="center"><%=res.getString("Sex")%></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="javascript:void(0)" id="lastPage">上一页</a>
	<a href="javascript:void(0)"  id="nextPage">下一页</a>
	</div>
	
</body>
</html>
