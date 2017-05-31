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
<style type="text/css">
* {
	font-family: "微软雅黑";
}

fieldset {
	width: 100%
}

div {
	width: 50%;
	padding: 10px;
	line-height: 20px;
}

.btn {
	width: 25%;
	padding: 5px;
	color: white;
	font-size: 18spx;
	background: #0099FF;
	border: 0px;
}

.btn:ACTIVE {
	background: #0080FF;
	border: 0;
}
#fileArea{
   text-align:center;
   width: 250px;
   height: 250px;
   border: 1px solid  red;
}
span{
   line-height: 250px;
}
</style>
<script type="text/javascript">
   var fileArea=document.getElementById("fileArea");


</script>

</head>
<body>
	<form
		action="http://www.wtlive.cn/interface/androidinterface.ashx?action=UpdateApk"
		method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>APK上传</legend>
			<div>
				APK版本名称: <input type="text" name="versionName">
			</div>
			<div>
				APK版本号：<input type="text" name="versionCode">&nbsp;<font
					color="red" size="2px">*&nbsp;格式必须为数字</font>
			</div>
			<div>
				APK文件：<input type="file" name="file"><font color="red"
					size="2px">*&nbsp;文件格式：.apk</font>
			</div>
			<div  id="fileArea">
			
			  <span>
			    拖拽文件到此区域
			  </span>
			</div>
			<div>
				<input type="submit" value="提交" class="btn"> <input
					type="reset" value="重置" class="btn">
			</div>
		</fieldset>
	</form>
</body>
</html>
