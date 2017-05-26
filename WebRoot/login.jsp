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
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

<script type="text/javascript">
	window.onload = function() {
		var errInfo = getQueryString("errCode");
		if (errInfo) {
			if (errInfo == 1) {
				/* var account = getQueryString("account");
				var pwd = getQueryString("password");
				$("#acct").val(account);
				$("#pwd").val(pwd); */
				$("#codeInfo").html("验证码错误");
			} else if (errInfo == 2) {
				var account = getQueryString("account");
				/* $("#acct").val(account); */
				$("#pwdInfo").html("密码错误");
			} else {
				$("#accountInfo").html("用户不存在");
			}
		}

		var meta = document.getElementsByTagName("meta")[0];
		var form = document.getElementsByTagName("form")[0];
		var btn_login = document.getElementById("btn_login");
		var btn_reset = document.getElementById("btn_reset");
		if (!isPC()) {
			//手机端
			meta.setAttribute("content", "width=device-width, initial-scale=1");
			btn_login.setAttribute("style", "width:100px;padding:10px;");
			btn_reset.setAttribute("style", "width:100px;padding:10px;");
			form.setAttribute("style", "margin-top:35%;margin-left:10%;");
			form.style.cssText = "margin-top:35%;margin-left:10%;";
		} else {
			// PC
			form.setAttribute("style", "margin-top:18%;margin-left:45%;");
			form.style.cssText = "margin-top:18%;margin-left:45%;";
			btn_login.setAttribute("style", "width:150px;padding:8px;");
			btn_reset.setAttribute("style", "width:150px;padding:8px;");
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

		function getQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
	};
</script>
<style>
* {
	font-family: "微软雅黑";
}

#btn_reset {
	margin-left: 20px;
	width: 25%;
	padding: 3px;
	font-size: 14px;
	border-radius:8px;
	border:0px;
	background: green;
	color: white;
}

#btn_login {
	width: 100px;
	padding: 3px;
	font-size: 14px;
	border-radius:8px;
	background: green;
	color: white;
	border: 0px;
}

#sp1 {
	font-size: 15px;
	color: red;
	margin: auto 0;
}

#acct,#pwd,#code {
	width: 200px;
	padding: 5px;
	box-shadow: 0px 0px 5px #888888;
}

#code {
	width: 129px;
}

#codeImage {
	margin-left: 7px;
	margin-bottom: -8px;
}

#dc {
	
}

label {
	color: red;
	font-size: 12px;
}
</style>

</head>

<body>
	<form action="login" method="post">

		<p>
			<span>账&nbsp;&nbsp;&nbsp;号&nbsp;</span> <input type="text"
				name="account" id="acct" placeholder="请输入账号"> <label
				id="accountInfo"></label>
		</p>
		<p>
			<span>密&nbsp;&nbsp;&nbsp;码&nbsp;</span> <input type="password"
				name="password" id="pwd" onblur="putPwd()" placeholder='请输入密码'>
			<label id="pwdInfo"></label>
		</p>
		<p id='dc'>
			<span>验证码&nbsp;</span> <input type="text" name="checkCode" id="code"
				placeholder='请输入验证码'> <img alt="验证码" src="checkCode"
				id="codeImage"> <label id="codeInfo"></label>
		</p>
		<p>
			<input type="submit" value="登录" id="btn_login" > <input
				type="reset" value="重置" id="btn_reset">
		</p>
	</form>

	<script type="text/javascript">
		$("#codeImage").click(function() {
			$("#codeImage").attr("src", "checkCode?" + Date.parse(new Date()));
		});

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