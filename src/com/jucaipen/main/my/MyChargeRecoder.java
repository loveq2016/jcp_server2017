package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ChargeOrder;
import com.jucaipen.service.ChargeOrderSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  我的充值记录
 *  
 */
public class MyChargeRecoder extends HttpServlet {
	private static final long serialVersionUID = 4092198290376308819L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String page=request.getParameter("page");
		String state=request.getParameter("state");
		if(StringUtil.isNotNull(userId)){
			if(StringUtil.isInteger(userId)){
				int uId=Integer.parseInt(userId);
				if(StringUtil.isNotNull(page)&&StringUtil.isInteger(page)){
					int p=Integer.parseInt(page);
					if(StringUtil.isNotNull(state)&&StringUtil.isInteger(state)){
						int s=Integer.parseInt(state);
						result=initChargeRecoder(uId,p,s);
					}else{
						result=JsonUtil.getRetMsg(1, "state 参数异常");
					}
				}else{
					result=JsonUtil.getRetMsg(1, "page 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(1, "userId 参数数字格式化异常");
			}
			
		}else{
			result=JsonUtil.getRetMsg(1, "userId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	private String initChargeRecoder(int uId, int p, int s) {
		List<ChargeOrder> charges = ChargeOrderSer.findOrderByUidAndState(uId, s, p);
		return JsonUtil.getMyCharge(charges);
	}

}
