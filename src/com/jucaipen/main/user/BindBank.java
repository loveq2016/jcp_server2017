package com.jucaipen.main.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.UserBank;
import com.jucaipen.service.UserBankSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator 绑定银行卡
 */
public class BindBank extends HttpServlet {
	private static final long serialVersionUID = 8589720684117227531L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String withName = request.getParameter("withName");
		String idCode = request.getParameter("idCode");
		String bankCode = request.getParameter("bankCode");
		String telPhone = request.getParameter("telPhone");
		String actionCode = request.getParameter("actionCode");
		String bankName=request.getParameter("bankName");
		if (StringUtil.isNotNull(userId)) {
			if (StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(withName)) {
						if (StringUtil.isNotNull(idCode)&&StringUtil.isIdCard(idCode)) {
							if (StringUtil.isNotNull(bankCode)&&StringUtil.isBankCard(bankCode)) {
								if (StringUtil.isNotNull(telPhone)) {
									if (StringUtil.isNotNull(actionCode)) {
										if(StringUtil.isNotNull(bankName)){
											result = bindBankInfo(uId, withName,
													idCode, bankCode, telPhone,
													actionCode,bankName);
										}else{
											result=JsonUtil.getRetMsg(9,"银行名称不能为空");
										}
									}else{
										result=JsonUtil.getRetMsg(8,"手机验证码不能为空");
									}
								}else{
									result=JsonUtil.getRetMsg(7, "银行预留电话不能为空");
								}
							}else{
								result=JsonUtil.getRetMsg(6,"银行账号不正确");
							}
						}else{
							result=JsonUtil.getRetMsg(5,"身份证号不正确");
						}
					}else{
						result=JsonUtil.getRetMsg(4,"持卡人姓名不能为空");
					}
				}else{
					result=JsonUtil.getRetMsg(3, "用户还没登录");
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

	private String bindBankInfo(int uId, String withName, String idCode,
			String bankCode, String telPhone, String actionCode,String bankName) {
		// 绑定银行账号
		boolean isChecked = checkActionCode(telPhone, actionCode);
		if(isChecked){
			UserBank bank=new UserBank();
			bank.setBankCode(bankCode);
			bank.setBankIdCard(idCode);
			bank.setBankMobile(telPhone);
			bank.setBankName(bankName);
			bank.setBankUserTrueName(withName);
			bank.setInsertDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			bank.setUserId(uId);
			int isSuccess = UserBankSer.addUserBank(bank);
			return isSuccess==1 ?JsonUtil.getRetMsg(0, "账户绑定成功") : JsonUtil.getRetMsg(1,"账户绑定失败");
		}else{
			return JsonUtil.getRetMsg(1,"手机验证码错误");
		}
	}

	private boolean checkActionCode(String telPhone, String actionCode) {
		// 验证手机激活码

		return false;
	}

}
