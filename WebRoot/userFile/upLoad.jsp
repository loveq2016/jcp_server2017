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
<title>上传用户图片</title>
</head>
<style>
* {
	font-family: "宋体";
	font-size: 14px
}
</style>
<body>
	<p align="center">请您选择需要上传的文件</p>
	<form id="form1" name="form1" method="post" action="http://192.168.1.79/ashx/AndroidUser.ashx?action=ImageUpload"
		enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td>上传人：</td>
				<td><input name="name" type="text" id="name" size="20">
				</td>
			</tr>
			<tr>
				<td>上传文件：</td>
				<td><input name="imgFile" type="file" size="20">
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="提交"> <input
					type="reset" name="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>