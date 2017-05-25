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

<title>登录APP后台管理系统</title>
<meta name="viewport" content="" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/md5.js"></script>

<script type="text/javascript">
	window.onload = function() {
		var meta = document.getElementsByTagName("meta")[0];
		var form = document.getElementsByTagName("form")[0];
		var btn_login = document.getElementById("btn_login");
		var btn_reset = document.getElementById("btn_reset");
		if (!isPC()) {
			//手机端
			meta.setAttribute("content", "width=device-width, initial-scale=1");
		} else {
			// PC
			form.setAttribute("style", "margin-top:10px;");
			form.style.cssText = "margin-top:10px";
			btn_login.setAttribute("style", "width:100px;");
			btn_reset.setAttribute("style", "width:100px;");
		}
		function isPC() {
			var userAgentInfo = navigator.userAgent;
			var Agents = new Array("Android", "iPhone", "SymbianOS",
					"Windows Phone", "iPad", "iPod");
			var flag = true;
			for (var v = 0; v < Agents.length; v++) {
				if (userAgentInfo.indexOf(Agents[v]) > 0) {
					flag = false;
					break;
				}
			}
			return flag;
		}
	};
</script>
<style>
* {
	font-family: "微软雅黑";
}

form {
	text-align: center;
	margin-top: 49%;
}

#btn_reset {
	margin-left: 20px;
	width: 25%;
	padding: 3px;
	font-size: 14px;
	border: 0px;
	background: green;
	color: white;
}

 #btn_login {
	width: 100px;
	padding: 3px;
	font-size: 14px;
	border: 0px;
	background: green;
	color: white;
} 

span {
	font-size: 15px;
	color: red;
	margin: auto 0;
}

input {
	width:200px;
}
#code{
  width: 150px;
}
#codeImage{
   height: 30px;
   width: 100px;
}
</style>

</head>

<body>
	<form action="login" method="post">

	<div align="center" style="text-align: center; margin-top:20%;">
			输入账号:&nbsp;<input type="text" name="account" id="acct"
				onblur="checkAccount()"><span id="sp1"></span>
		</div>
		<div align="center" style="text-align: center; margin-top:15px;">
			输入密码:&nbsp;<input type="password" name="password" id="pwd"
				onblur="putPwd()">
		</div>
		<div align="center" style="text-align: center; margin-top:15px;">
			输入验证码:&nbsp;<input type="password" name="password" id="code"
				>
				<img alt="" src="checkCode" id="codeImage">
		</div>
		<div align="center" style="text-align: center; margin-top:15px;">
			<input type="submit" value="登录" id="btn_login"> <input
				type="reset" value="重置" id="btn_reset">
		</div> 
	</form>

	<script type="text/javascript">
		function putPwd() {
			var pwd = $("#pwd").val();
			if (pwd.length > 0) {
				var p = hex_md5($("#pwd").val());
				$("#pwd").val(p);
			}
		}

		function checkAccount() {
			var acct = $("#acct").val();
			if (acct.length < 3) {
				$("#sp1").html("账号长度不足");
			} else {
				$("#sp1").html("");
			}
		}
	</script>

</body>
</html>