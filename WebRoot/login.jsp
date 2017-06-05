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
<title>登录</title>
<meta name="viewport" content="" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<link rel="stylesheet" href="css/login.css">
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
				//$("#codeInfo").html("验证码错误");
				alert("验证码错误");
			} else if (errInfo == 2) {
				var account = getQueryString("account");
				/* $("#acct").val(account); */
				//$("#pwdInfo").html("密码错误");
				alert("密码错误");
			} else {
				//$("#accountInfo").html("用户不存在");
				alert("用户不存在");
			}
		}

		var meta = document.getElementsByTagName("meta")[0];
		var form = document.getElementsByTagName("form")[0];
		var btn_login = document.getElementById("btn_login");
		var btn_reset = document.getElementById("btn_reset");
		var swithArea=document.getElementById("swithArea");
		var btn_swith=document.getElementById("btn_swith");
		var qrCode=document.getElementById("qrCode");
		if (!isPC()) {
			//手机端
			meta.setAttribute("content", "width=device-width, initial-scale=1");
			btn_login.setAttribute("style", "width:120px;padding:10px;");
			btn_reset.setAttribute("style", "width:120px;padding:10px;");
			form.setAttribute("style", "margin-top:35%;margin-left:10%;");
			qrCode.setAttribute("style", "width:250px;height:250px;");
			//form.style.cssText = "margin-top:35%;margin-left:10%;";
			swithArea.setAttribute("style", "width:80%;");
			btn_swith.setAttribute("style", "width:100%;");
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

</head>

<body>
	<form action="login" method="post" onsubmit="retuen dosubmit()">
		<div class="normal">
			<p>
				<span>账&nbsp;&nbsp;&nbsp;号&nbsp;</span> <input type="text" name="account"
					id="acct" placeholder="请输入账号" onblur="putNull()"> <label
					id="accountInfo"></label>
			</p>
			<p>
				<span>密&nbsp;&nbsp;&nbsp;码&nbsp;</span> <input type="password" name="password"
					id="pwd" onblur="putPwd()" placeholder='请输入密码'> <label
					id="pwdInfo"></label>
			</p>
			<p id='dc'>
				<span>验证码&nbsp;</span> <input type="text" name="checkCode" id="code"
					placeholder='请输入验证码' onblur="putNull()" s> <img alt="验证码"
					src="checkCode?type=1" id="codeImage"> <label id="codeInfo"></label>
			</p>
			<p>
				<input type="submit" value="登录" id="btn_login"> <input
					type="reset" value="重置" id="btn_reset">
			</p>
		</div>
		<div class="qrLogin">
			<img alt="" src="checkCode?type=2" id="qrCode">
		</div>
		<p id="swithArea">
			<input type="reset" value="切换登录方式" id="btn_swith">
		</p>
	</form>

	<script type="text/javascript">
		//加载验证码
		$("#codeImage").click(
				function() {
					$("#codeImage").attr(
							"src",
							"checkCode?type=1&" + new Date().getTime()
									+ (Math.floor(Math.round() * 100)));
				});
				
				
		$("#btn_swith").click(function() {
			var display=$(".qrLogin").css("display");
			if(display=='none'){
			  $(".normal").hide(2000);
			  $(".qrLogin").show(2000);
			}else{
			  $(".qrLogin").hide(2000);
			  $(".normal").show(2000);
			}
		})

		//清空提示数据
		function putNull() {
			$("#codeInfo").html("");
			$("#accountInfo").html("");
			$("#pwdInfo").html("");

		}

		//md5加密密码
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
		};

		function dosubmit() {
			var isSubmit = false;
			if (!isSubmit) {
				isSubmit = true;
				return true;
			} else {
				isSubmit = false;
				return false;
			}
			;
		};
		$("form").show(100);
	</script>

</body>
</html>