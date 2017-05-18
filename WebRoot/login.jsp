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
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style>
*{
   font-family: "微软雅黑";
   
}

form {
	text-align: center;
	margin-top: 49%;
}
#btn_reset{
  margin-left: 20px;
  width: 25%;
  padding:3px;
  font-size:14px;
  border: 0px;
  background: green;
  color: white;
}
#btn_login{
  width: 25%;
  padding:3px;
  font-size:14px;
  border: 0px;
  background: green;
  color: white;
}
</style>

</head>

<body>
    <form action="login" method="post">
	<div align="center" style="text-align: center; margin-top:20%;">
		输入账号:&nbsp;<input type="text" name="account">
	</div>
	<div align="center" style="text-align: center; margin-top:15px;">
		输入密码:&nbsp;<input type="password" name="password">
	</div>
	<div align="center" style="text-align: center; margin-top:15px;">
		<input type="submit" value="登录"  id="btn_login">  
		<input type="reset" value="重置" id="btn_reset">
	</div>
	</form>
</body>
</html>