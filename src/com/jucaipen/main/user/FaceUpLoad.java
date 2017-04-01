package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *   同步更新用户头像信息
 */
public class FaceUpLoad extends HttpServlet {
	private static final long serialVersionUID = -7510692746856945862L;
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String imageFace=request.getParameter("imageFace");
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(userId)){
			if(StringUtil.isInteger(userId)){
				int uId=Integer.parseInt(userId);
				if(uId>0){
					result=initUserFaceUrl(uId,imageFace);
				}else{
					result=JsonUtil.getRetMsg(3,"用户还没登录");
				}
			}else{
				result=JsonUtil.getRetMsg(2,"userId 参数数字格式化异常");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"userId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initUserFaceUrl(int uId, String imageFace) {
		//初始化用户头像URL信息
		int isSuccess = UserServer.updateUserLogo(uId, imageFace);
		return isSuccess==1 ? JsonUtil.getRetMsg(0, "用户头像更新成功") : JsonUtil.getRetMsg(1,"用户头像更新失败") ;
	}

}
