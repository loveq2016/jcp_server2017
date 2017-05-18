<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>财经资讯</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
      *{
         font-family: "微软雅黑";
         font-size: 18px
      }
    
</style>
	
	<script src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
	       var encodingUrl="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetEncryptMobileNum";
	       $("#btn_ed").click(function(){
	       //加密手机号
	        $.ajax({
	         type:"post",
	         url:encodingUrl,
	         data:{mobilenum:$("phone").val()},
             success:function(result){
                var res=result.Result;
                if(res){
                  $("span").html=result.MobileNum;
                }
             },
            dataType:"jsonp"
	       });
	      });
	       
	   
	    });
	</script>

  </head>
  
  <body>
    <div style="text-align: center;"><font size="5" color="pink">账号管理</font></div>
    <fieldset>
        <legend>手机号加密</legend>
        <div>
                 输入手机号<input type="text" id='phone' value="15221299512"> <button id='btn_ed'>加密</button>
        </div>
        <div>
           <span></span>
        </div>
    </fieldset>
    
     <fieldset>
        <legend>手机号解密</legend>
        <div>
                 输入手机号<input type="text" id='phone_ed' value=""> <button id='btn_code'>解密</button>
        </div>
        <div>
           <span></span>
        </div>
    
    </fieldset>
    
    
  </body>
</html>
