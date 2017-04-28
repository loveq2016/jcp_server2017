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

<title>app后台数据管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
     a{
       text-decoration: none;
     
     }
 
</style>


</head>

<body>
	<!-- 设置背景音乐-->
	<!-- <bgsound src="www.baidu.com/" loop="infinite"> -->
	<h2>
		<marquee direction="right">
			<font color="pink"> APP后台数据管理</font>
		</marquee>
	</h2>
	<br>
	<!-- <a href='/WEB-INF/admin.jsp'>admin</a> -->
	<table>
		<tr>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/user.jsp"><font size="5" color="green"><strong>用户管理</strong>
				</font> </a></td>
			<td align="center" valign="middle" width="10%" bgColor="#c0c0c0"><a
				href="admin/finalcenter.jsp"><font size="5" color="green"><strong>理财中心</strong>
				</font> </a></td>
			<td align="center" valign="middle" width="10%" bgColor="#c0c0c0"><a
				href="admin/teachmanage.jsp"><font color="green" size="5"><strong>教学管理中心</strong>
				</font> </a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/finalnews.jsp"><font size="5" color="green"><strong>财经资讯</strong>
				</font> </a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/peterhouse.jsp"><font color="green" size="5"><strong>学堂</strong>
				</font> </a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/apkManager.jsp"><font color="green" size="5"><strong>APK管理</strong>
				</font> </a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/pushInfoManager.jsp"><font color="green" size="5"><strong>消息推送管理</strong>
				</font> </a>
			</td>
		</tr>


	</table>
	<br>

</body>
</html>