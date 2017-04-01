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

<title>登录APP后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
    <form action="LoginServer" method="post">
	<div align="center" style="text-align: center; margin-top:20%;">
		输入账号:&nbsp;&nbsp;&nbsp;<input type="text" name="account">
	</div>
	<div align="center" style="text-align: center; margin-top:15px;">
		输入密码:&nbsp;&nbsp;&nbsp;<input type="password" name="password">
	</div>
	<div align="center" style="text-align: center; margin-top:15px;">
		<input type="submit" value="登录">&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
	</div>
	</form>
</body>
</html>