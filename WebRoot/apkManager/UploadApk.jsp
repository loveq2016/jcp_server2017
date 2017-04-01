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

<title>上传AP文件</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>
	<form action="jucaipen/uploadApk" method="post" enctype="multipart/form-data">
		<div align="center" style="margin-top: 20%">
			APK版本名称: <input type="text" name="versionName">
		</div>
		<div align="center" style="margin-top: 10px">
			APK版本号：<input type="text" name="versionCode">&nbsp;<font color="red" size="2">*&nbsp;格式必须为数字</font>
		</div>
		<div align="center" style="margin-top: 10px;margin-left: 50px">
			APK文件：<input type="file" name="file">&nbsp;<font color="red" size="2">*&nbsp;文件格式：.apk</font>
		</div>
		<div align="center" style="margin-top: 10px">
			<input type="submit" value="提交"> &nbsp;&nbsp;<input
				type="reset" value="重置">
		</div>
	</form>
</body>
</html>
