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
		
		//加载验证码
		$("#codeImage").click(
				function() {
					$("#codeImage").attr(
							"src",
							"checkCode?" + new Date().getTime()
									+ (Math.floor(Math.round() * 100)));
				});

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
		}
		
	};