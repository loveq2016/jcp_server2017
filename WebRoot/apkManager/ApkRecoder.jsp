<%@page import="com.jucaipen.utils.JdbcUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<title>APK更新记录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<%
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Connection dbConn = JdbcUtil.connSqlServer();
		Statement sta = dbConn.createStatement();
		ResultSet res = sta.executeQuery("SELECT * FROM versionInfo");
	%>
	
	<table align="center" border="2">
	<tr >
	<td>版本id(Id)</td>
	<td>包名（pkgName）</td>
	<td>版本号（versionCode）</td>
	<td>版本名称（versionName）</td>
	<td>APK存储URL（apkUrl）</td>
	<td>更新时间（updateDate）</td>
	</tr>
	<%
	while(res.next()){
	 %>
	 <tr>
	 <td><%=res.getInt("Id") %></td>
	 <td><%=res.getString("pkgName") %></td>
	 <td>当前版本号：<%=res.getInt("versionCode") %></td>
	 <td><%=res.getString("versionName") %></td>
	 <td><%=res.getString("apkUrl") %></td>
	 <td><%=res.getDate("updateDate").toString()%></td>
	<%
	}
	 %>
	</table>
	
</body>
</html>
