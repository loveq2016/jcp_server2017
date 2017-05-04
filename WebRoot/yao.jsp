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

<title>审核管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
div,fieldset {
	font-size: 18px;
	font-family: "微软雅黑";
}

fieldset {
	padding: 10px;
}

.btn {
	width: 100%;
	color: white;
	padding: 5px;
	font-size: 18spx;
	background: #0099FF;
	border: 0px;
}

.btn : HOVER {
	background: red;
}

.btn:ACTIVE {
	background: #0080FF;
	border: 0;
}
</style>
</head>

<body>
	<form action="changeState" method="post">
		<fieldset>
			<legend>审核管理</legend>
			<br />
			<div>
				<input type="radio" name="yy" value="on">使用趣看视频 <input
					type="radio" name="yy" value="off" checked="checked">使用腾讯视频
			</div>
			<br />
			<div>
				<input type="radio" name="fun" value="on">显示全部功能 <input
					type="radio" name="fun" value="off" checked="checked">隐藏功能
			</div>
			<br />
			<div>
				操作码：<input type="password" name="code">
			</div>
			<br />
			<div>
				<input type="submit" value="提交" class="btn">
			</div>
		</fieldset>
	</form>
</body>
</html>
