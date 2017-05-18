<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消息推送管理</title>
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
</style>

  </head>
  
  <body>
   <div class="text" style="text-align: center;">
		<font size="5" color="pink">消息推送管理</font>
	</div>
	  <div align="center" style="margin-top: 4%"><a href="admin/pushManager/pushAll.jsp">推送全部设备</a></div>
    <div align="center" style="margin-top: 1%"><a href="apkManager/ApkRecoder.jsp">推送指定TAG设备</a></div>
    <div align="center" style="margin-top: 1%"><a href="apkManager/UploadApk.jsp">推送指定TOKEN设备</a></div>
  </body>
</html>
