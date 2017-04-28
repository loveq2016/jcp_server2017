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

<html>
<head>
<base href="<%=basePath%>">

<title>教学管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
    a {
	   text-decoration: none;
}
 
</style>

</head>

<body>
	<%
		Connection conn = JdbcUtil.connSqlServer();
		Statement sta = conn.createStatement();
		ResultSet res = sta.executeQuery("select * from JCP_Tearcher");
	%>
	<div class="text" style="text-align: center;">
		<font size="5" color="pink">教学管理</font>
	</div>
	<br>
	<table border="2" align="center" bordercolor="black" >
		<tr bgcolor="#408080">
			<td align="center" width="10%">编号</td>
			<td align="center" width="10%">姓名</td>
			<td align="center" width="10%">头像URL</td>
			<td align="center" width="10%">概要</td>
			<td align="center" width="10%">简介</td>
			<td align="center" width="10%">操作</td>
		</tr>
		<%
			while (res.next()) {
		%>
		<tr>
			<td align="center"><%=res.getInt("Id")%></td>
			<td align="center"><%=res.getString("NickName")%></td>
			<td align="center"><a href="<%=res.getString("HeadFace")%>" target="black"><%=res.getString("HeadFace")%></a></td>
			<td align="center"><%=res.getString("Notice")%></td>
			<td align="center"><%=res.getString("Jianjie")%></td>
			<td align="center"><a href="teach/insert.jsp">添加</a> <a
				href="teach/delete.jsp">删除</a> <a href="teach/update.jsp">修改</a>
			</td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>
