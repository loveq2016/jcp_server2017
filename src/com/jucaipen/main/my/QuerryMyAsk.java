package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.User;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *
 *  获取我的问答
 */
public class QuerryMyAsk extends HttpServlet {
	private static final long serialVersionUID = 6425301365805052709L;
	private String result;
	private List<User> users=new ArrayList<User>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String page=request.getParameter("page");
		if(StringUtil.isNotNull(userId)){
			if(StringUtil.isInteger(userId)){
				int uId=Integer.parseInt(userId);
				if(uId>0){
					if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
						int p=Integer.parseInt(page);
						result=initMyQuestion(uId,p);
					}else{
						result=JsonUtil.getRetMsg(4,"page 参数异常");
					}
				}else{
					result=JsonUtil.getRetMsg(3,"用户还没登录");
				}
			}else{
				result=JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
			}
			
		}else{
			result=JsonUtil.getRetMsg(1,"userId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initMyQuestion(int uId, int p) {
		users.clear();
		List<Ask> asks=AskSer.findAskByUserId(uId, p);
		if(asks!=null){
			for(Ask ask : asks){
				int teacherId=ask.getTeacherId();
				FamousTeacher teacher=FamousTeacherSer.findFamousTeacherById(teacherId);
				if(teacher==null){
					teacher=new FamousTeacher();
					ask.setTeacherName("");
				}else{
					ask.setTeacherName(teacher.getNickName());
				}
			}
		}
		User user=UserServer.findUserById(uId);
		return JsonUtil.getAskList(asks, user);
	}

}
