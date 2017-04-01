package com.jucaipen.main.play;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.service.SharkDetailServer;
import com.jucaipen.service.SharkInfoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author 杨朗飞
 *2017年1月23日  下午1:09:43
 *    获取摇一摇信息
 */
public class SharkInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_COUNT=1;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
			int uId=Integer.parseInt(userId);
			if(uId>0){
				String date = TimeUtils.format(new Date(), "yyyy-MM-dd");
				com.jucaipen.model.SharkInfo info=SharkInfoServer.getSharkByIsEnd(date);
				if(info!=null){
					if(TimeUtils.isLive(info.getStartDate(), info.getEndDate())){
						if(checkCount(uId)){
							//正在进行
							result=JsonUtil.getSharkResult(0,"正在进行",1);
						}else{
							//已领取
							result=JsonUtil.getSharkResult(0,"今日已经领取",2);
						}
					}else{
						if(TimeUtils.isDateBefore(info.getStartDate())){
							//未开始
							result=JsonUtil.getSharkResult(0,"活动还未开始",3);
						}else{
							//已结束
							result=JsonUtil.getSharkResult(0,"活动已经结束",4);
						}
					}
				}else{
					result=JsonUtil.getSharkResult(0,"活动还未开始",3);
				}
			}else{
				result=JsonUtil.getSharkResult(2,"请先登录",-1);
			}
		}else{
			result=JsonUtil.getSharkResult(1,"userId 参数异常",-1);
		}
		out.print(result);
		out.flush();
		out.close();
	}
	
	/**
	 * @param uId
	 * @return  摇一摇次数是否用完
	 */
	private boolean checkCount(int uId) {
		// 获取当前用户摇一摇次数
		int count = SharkDetailServer.getSharkCount(uId);
		return count<MAX_COUNT;
	}

}
