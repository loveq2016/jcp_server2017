<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'yao.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
     <style type="text/css">
     </style>
  </head>
  
  <body>
       <form action="changeState"   method="post">
        <input type="radio" name="yy" value="on">启用摇一摇
        <input  type="radio"  name="yy"  value="off">关闭摇一摇 <br/>   
                操作码：<input type="text"><br/>
        <input type="submit"  value="提交" width="500px">
       </form>
  </body>
</html>
