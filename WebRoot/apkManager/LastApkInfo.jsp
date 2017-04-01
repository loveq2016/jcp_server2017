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

<title>最新APK版本信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<%
		Connection dbConn = JdbcUtil.connMySql();
		Statement sta = dbConn.createStatement();
		ResultSet res = sta
				.executeQuery("SELECT * FROM versionInfo ORDER BY versionCode DESC LIMIT 0,1");
	%>

    <%
    while(res.next()){
     %>
	<div align="center" style="margin-top: 10%">APK名称：聚财盆</div>
	<div align="center" style="margin-top: 10px;">APK包名（pkgName）：<%=res.getString("pkgName") %></div>
	<div align="center" style="margin-top: 10px;">APK版本名称(versionName)：<%=res.getString("versionName") %></div>
	<div align="center" style="margin-top: 10px;">APK版本号(versionCode)：<%=res.getInt("versionCode") %></div>
	<div align="center" style="margin-top: 10px;">APK文件大小：15M</div>
	<div align="center" style="margin-top: 10px;">APK版本更新时间：<%=res.getDate("updateDate") %></div>
	<div align="center" style="margin-top: 10px;">APK文件存储URL：<%=res.getString("apkUrl") %></div>
	<%
	}
	 %>
</body>
</html>
