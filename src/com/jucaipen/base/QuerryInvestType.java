package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.InvestmentType;
import com.jucaipen.service.InvestmentTypeSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  获取投资风格
 */
@SuppressWarnings("serial")
public class QuerryInvestType extends HttpServlet {
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			String userId=request.getParameter("userId");
			if(StringUtil.isNotNull(userId)){
				if(StringUtil.isInteger(userId)){
					int uId=Integer.parseInt(userId);
					if(uId>0){
						result=initInvestType();
					}else{
						result=JsonUtil.getRetMsg(3, "该用户还没登录");
					}
				}else{
					result=JsonUtil.getRetMsg(2,"userId 参数数字格式化异常");
				}
			}else{
				result=JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}
		}else{
			result=StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initInvestType() {
		// 初始化投资风格
		List<InvestmentType> types = InvestmentTypeSer.findAllTypes();
		return JsonUtil.getInvestType(types);
	}

}
