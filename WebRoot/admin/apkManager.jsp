<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>APK信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
    <div align="center" style="margin-top: 10px"><a href="apkManager/LastApkInfo.jsp">最新APK版本信息</a></div>
    <div align="center" style="margin-top: 10px"><a href="apkManager/ApkRecoder.jsp">APK版本更新记录</a></div>
    <div align="center" style="margin-top: 10px"><a href="apkManager/UploadWebApk.jsp">上传APK文件</a></div>
  </body>
</html>
