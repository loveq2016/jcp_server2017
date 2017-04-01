package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.RebateIntegeralDetail;
import com.jucaipen.service.RebateIntegeralDetailSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *   获取我的返利信息       普通用户     讲师
 */
public class QuerryRebate extends HttpServlet {
	private static final long serialVersionUID = -2131947635113301234L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String page=request.getParameter("page");
		if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
			int uId=Integer.parseInt(userId);
			if(uId>0){
				if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
					int p=Integer.parseInt(page);
					result=initRebateInfo(uId,p);
				}else{
					result=JsonUtil.getRetMsg(3,"page 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(2, "用户还没登录");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initRebateInfo(int uId, int p) {
		List<RebateIntegeralDetail> rebates = RebateIntegeralDetailSer.findRebateIntegeralByUserId(uId, p);
		return JsonUtil.getMyRebate(rebates);
	}

}
