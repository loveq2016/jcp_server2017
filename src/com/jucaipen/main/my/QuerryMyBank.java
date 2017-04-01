package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.UserBank;
import com.jucaipen.service.UserBankSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  获取我绑定的银行卡
 */
public class QuerryMyBank extends HttpServlet {
	private static final long serialVersionUID = -83881738302535151L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		if(StringUtil.isNotNull(userId)){
			if(StringUtil.isInteger(userId)){
				int uId=Integer.parseInt(userId);
				if(uId>0){
					result=initMyBankList(uId);
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

	private String initMyBankList(int uId) {
		//初始化用户绑定
		List<UserBank> banks = UserBankSer.findAllBankInfo(uId);
		return JsonUtil.getUserBankList(banks);
	}

}
