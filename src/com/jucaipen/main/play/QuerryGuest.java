package com.jucaipen.main.play;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.StudioGuest;
import com.jucaipen.service.StudioGuestSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取嘉宾   typeId   (0 获取首页嘉宾)   （1 获取全部嘉宾）
 */
public class QuerryGuest extends HttpServlet {
	private static final long serialVersionUID = 8943981291520195117L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String typeId=request.getParameter("typeId");
			String studioId=request.getParameter("studioId");
			if(StringUtil.isNotNull(studioId)){
				if(StringUtil.isInteger(studioId)){
					int sId=Integer.parseInt(studioId);
					if(StringUtil.isNotNull(typeId)&&StringUtil.isInteger(typeId)){
						int type=Integer.parseInt(typeId);
						if(type==0){
							//获取首页嘉宾
							result=initStudioGuest(sId,type,0);
						}else{
							//获取全部嘉宾
							String page=request.getParameter("page");
							if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
								int p=Integer.parseInt(page);
								result=initStudioGuest(sId,type,p);
							}else{
								result=JsonUtil.getRetMsg(4,"page 参数数字格式化异常");
							}
						}
					}else{
						result=JsonUtil.getRetMsg(3,"typeId 参数异常");
					}
					
				}else{
					result=JsonUtil.getRetMsg(2,"studioId 参数数字格式化异常");
				}
			}else{
				result=JsonUtil.getRetMsg(1,"studioId 参数不能为空");
			}
			
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initStudioGuest(int sId, int type,int page) {
		//初始化演播室嘉宾信息
		List<StudioGuest> guests;
		if(type==0){
			//获取首页嘉宾
			guests = StudioGuestSer.findTopGuest(2, sId);
		}else{
			//获取全部嘉宾
			guests=StudioGuestSer.findAll(page, sId);
		}
		return JsonUtil.getGuests(guests);
	}
}
