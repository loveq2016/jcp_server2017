package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.VideoClass;
import com.jucaipen.service.VideoClassSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取视频分类信息  不是类型  讲师
 */
public class QuerryVideClass extends HttpServlet {
	private static final long serialVersionUID = 6515272608845875430L;
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
			String parentId=request.getParameter("parentId");
			if(StringUtil.isNotNull(parentId)&&StringUtil.isInteger(parentId)){
				int pId=Integer.parseInt(parentId);
				result = initVideoList(pId);
			}else{
				result=JsonUtil.getRetMsg(1,"parentId 参数异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initVideoList(int pId) {
		// 获取视频分类列表   不是类型列表    也不是讲师列表
		List<VideoClass> types = VideoClassSer.findClassByPid(pId);
		return JsonUtil.getVideoClassList(types,pId);
	}
}
