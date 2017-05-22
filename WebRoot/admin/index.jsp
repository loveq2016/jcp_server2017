<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
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

<title>app后台管理</title>
<meta name="viewport" content="" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
    window.onload=function(){
    var meta=document.getElementsByTagName("meta")[0];
    var a=document.getElementsByTagName("a");
    if(!isPC()){
       //手机端
       meta.setAttribute("content","width=device-width, initial-scale=1");
    }else{
      // PC
      for(var i=0;i<a.length;i++){
         a[i].setAttribute("style","font-size:22px;color:green;");
      }
    }
    function isPC() 
       { 
           var userAgentInfo = navigator.userAgent; 
           var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"); 
           var flag = true; 
           for (var v = 0; v < Agents.length; v++) { 
               if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; } 
           } 
           return flag; 
       } 
    };
</script>

<style type="text/css">
     a{
       text-decoration: none;
     }
     html{
        font-family: "微软雅黑";
     }
</style>


</head>

<body>
	<!-- 设置背景音乐-->
	<!-- <bgsound src="www.baidu.com/" loop="infinite"> -->
	<h2 >
		<marquee direction="right">
			<font color="pink"> APP后台数据管理</font>
		</marquee>
	</h2>
	  <table>
		<tr>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/user.jsp"><strong>用户管理</strong>
				 </a></td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/finalnews.jsp"><strong>账号管理</strong>
				 </a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/apkManager.jsp"><strong>APK管理</strong>
				</a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/pushInfoManager.jsp"><strong>消息推送管理</strong>
				</a>
			</td>
			<td align="center" valign="middle" bgColor="#c0c0c0" width="10%"><a
				href="admin/yao.jsp"><strong>配置管理</strong>
				 </a>
			</td>
		</tr>


	</table>
	<br>

</body>
</html>