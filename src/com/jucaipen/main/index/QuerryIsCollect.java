package com.jucaipen.main.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Favorites;
import com.jucaipen.service.FavoritesSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  获取收藏状态    0   视频收藏状态      
 *             1    知识收藏状态
 */
@SuppressWarnings("serial")
public class QuerryIsCollect extends HttpServlet {
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId=request.getParameter("typeId");
		String userId=request.getParameter("userId");
		String fkId=request.getParameter("fkId");
		if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
			int uId=Integer.parseInt(userId);
			if(uId>0){
				if(StringUtil.isNotNull(fkId)&&StringUtil.isInteger(fkId)){
					int fId=Integer.parseInt(fkId);
					if(StringUtil.isNotNull(typeId)&&StringUtil.isInteger(typeId)){
						int type=Integer.parseInt(typeId);
						result=initCollectState(uId,fId,type);
					}
				}
			}
		}else{
			result=JsonUtil.getRetMsg(3,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initCollectState(int uId, int fId, int type) {
		//1为收藏视频 2为收藏知识
		if(type==0){
			//视频收藏状态
			Favorites favourate = FavoritesSer.findNewsIsCollect(uId, fId, 1);
			return favourate!=null ?JsonUtil.getRetMsg(0, "视频已经收藏") : JsonUtil.getRetMsg(1,"视频还没收藏");
		}else{
			//知识收藏状态
			Favorites favourate = FavoritesSer.findNewsIsCollect(uId, fId, 2);
			return favourate!=null ?JsonUtil.getRetMsg(0, "知识已经收藏") : JsonUtil.getRetMsg(1,"知识还没收藏");
		}
	}

}
