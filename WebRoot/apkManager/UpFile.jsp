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

<title>My JSP 'fileupload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="jucaipen/upload" enctype="multipart/form-data"
		method="post">
		<fieldset   style="padding: 10px">
		<legend> 上传文件</legend>
		<div style="margin-top: 10px">
		用户名：<input type="text" name="username">
		</div>
		<div style="margin-top: 10px">
		上传证件照(正)：<input type="file" name="img1">
		</div>
		<div style="margin-top: 10px">
		上传证件照(反)： <input type="file" name="img2">
		</div>
		<div  style="margin-top: 10px">
		上传头像：<input type="file" name="img3"  >
		</div>
		<div>
		<input type="submit" value="提交"  style="width: 300px;margin-top: 10px"/>
		</div>
		</fieldset>

	</form>
</body>
</html>
