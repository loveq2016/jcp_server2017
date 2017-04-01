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

<title>好友邀请</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
body {
	background: #FF6D78
}

.login_main ul li span i.icon1 {
	margin-top: 0.7rem;
}

.login_main ul li span i img {
	width: 1rem;
}

.login_main h4 input {
	width: 100%;
	float: left;
	background: #d70c1a;
	color: #fff;
	line-height: 3.1rem;
	font-size: 1.2rem;
	text-align: center;
	border-width: 0px
}

.login_main ul li span {
	display: block;
	width: 100%;
	float: left;
}

.login_main ul {
	width: 100%;
	float: left;
	background: #fff;
	border-radius: 0.15rem;
}

.login_main ul li span em {
	display: block;
	float: left;
	width: 82%;
	padding: 0.95rem 0 0.85rem 0;
}

.login_main {
	width: 100%;
	float: left;
	padding: 0.8%;
	margin-top: 4.3rem;
}

.login_main ul li {
	width: 100%;
	float: left;
	border-bottom: 1px solid #eee;
}

ul,ul li {
	display: block;
	list-style: none;
}

ul,ul li {
	display: block;
	list-style: none;
}

* {
	list-style-type: none;
}

.login_main ul li span em input {
	width: 100%;
	color: #555;
	font-size: 0.8rem;
	line-height: 1.2rem;
}

.login_main ul li span i {
	display: block;
	float: left;
	width: 14%;
	text-align: center;
	margin-top: 0.85rem;
}

.login_main ul li span em input.red {
	background: #d70c1a;
	color: #fff;
}

.login_main ul li span em input.fsyzm {
	display: block;
	float: right;
	line-height: 0.9rem;
	color: #fff;
	font-size: 0.55rem;
	padding: 0.3rem 0.4rem 0.3rem 0.4rem;
	background: #aaa;
	border-radius: 0.2rem;
	margin: 0.2rem 0 0.2rem 0;
	cursor: pointer;
}

.login_main ul li span em.icon_yzm input.yanzhengma {
	width: 50%;
	color: #555;
	font-size: 0.8rem;
	line-height: 1.5rem;
	margin-top: 0.2rem;
}

.login_main ul li span em.icon_yzm {
	padding: 0.55rem 0 0.5rem 0;
}

h4 {
	color: black;
}

.login_body_m {
	width: 84%;
	float: left;
	padding: 0 8%;
}
</style>

<script type="text/javascript">
	
</script>

</head>
<%
	String investCode = request.getParameter("investCode");
%>
<body>
	<div class="login_body_m">
		<form action="jucaipen/regin" method="post">
			<div class="login_main" style="margin-top: 1.8rem;">
				<h4>
					好友邀请您加入聚财盆，邀请码:<%=investCode%></h4>
				<ul>
					<li><span><i class="icon1"> <img
								src="images/tel.png" /></i><em> <input name="telPhone"
								type="text" id="txb_mobile" placeholder="请输入手机号" />
						</em></span></li>
					<li><span><i class="icon2"> <img
								src="images/yanzhengma.png" /></i><em class="icon_yzm"> <input
								name="checkCode" type="text" id="txb_code" class="yanzhengma"
								placeholder="请输入短信验证码" /> <input id="sendcode" type="button"
								class="fsyzm red" style="width: 3.5rem" value="获取验证码" />
						</em></span></li>
					<li><span><i class="icon5"> <img
								src="images/lock.png" /></i><em> <input name="password"
								type="password" id="txb_pwd" placeholder="请输入登录密码" />
						</em></span></li>
					<li class="def"><span><i class="icon5"> <img
								src="images/lock.png" /></i><em> <input name="reptPwd"
								type="password" id="txb_sure_pwd" placeholder="请再次输入登录密码" />
						</em></span></li>
				</ul>
				<h4>
					<input type="submit" name="btn_reg" value="注   册" id="btn_reg" />
				</h4>
			</div>
		</form>
	</div>
</body>
</html>
