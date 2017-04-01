package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Fans;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.FansSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         关注名师 opType 0 添加关注 1 取消关注
 * 
 */
public class Attention extends HttpServlet {
	private static final long serialVersionUID = 5934460474869115371L;
	private String result;
	private String ip;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String opType = request.getParameter("opType");
			String userId = request.getParameter("userId");
			String teacherId = request.getParameter("teacherId");
			ip=request.getRemoteAddr();
			if (StringUtil.isInteger(opType)) {
				int type = Integer.parseInt(opType);
				if (StringUtil.isInteger(userId)) {
					// 用户id数字格式化正常
					int uId = Integer.parseInt(userId);
					if(uId>0){
						if (StringUtil.isInteger(teacherId)) {
							int tId = Integer.parseInt(teacherId);
							if (type == 0||type==1) {
								boolean isFans=checkIsAttention(uId, tId);
								result=initData(isFans,type,uId,tId);
							} else {
								result = JsonUtil.getRetMsg(5, "操作id不符合要求");
							}
						} else {
							result = JsonUtil.getRetMsg(1, "讲师id数字格式化异常");
						}
					}else{
						result=JsonUtil.getRetMsg(7,"用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "用户id数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(4, "操作id数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initData(boolean isFans, int type, int uId, int tId) {
		if(type==0){
			//添加关注
			if(isFans){
				return JsonUtil.getRetMsg(1,"该讲师已经关注了");
			}
			Fans fan=new Fans();
			fan.setUserId(uId);
			fan.setTeacherId(tId);
			fan.setIp(ip);
			fan.setInsertDate(TimeUtils.format(new Date()));;
			int isSuccess=FansSer.addFans(fan);
			if(isSuccess==1){
				//添加讲师粉丝数
				FamousTeacher teacher=FamousTeacherSer.findTeacherBaseInfo(tId);
				FamousTeacherSer.updateFansNum(teacher.getFans()+1, tId);
			}
			return isSuccess==1?JsonUtil.getRetMsg(0, "关注成功"):JsonUtil.getRetMsg(2,"关注失败");
		}else if(type==1){
			//取消关注
			if(!isFans){
				return JsonUtil.getRetMsg(1,"该讲师还没关注");
			}
			int isSuccess=FansSer.cancelFans(tId,uId);
			return isSuccess==1?JsonUtil.getRetMsg(0, "取消关注成功") :JsonUtil.getRetMsg(2,"取消关注失败");
		}
		return null;
	}


	private boolean checkIsAttention(int uId, int tId) {
		// 检查之前是否关注过
		Fans f=FansSer.findIsFans(uId, tId);
		if(f!=null){
			return true;
		}else{
			return false;
		}
	}
}
