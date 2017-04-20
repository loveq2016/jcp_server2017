package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 * 
 *         payState 1 未支付 2 已支付 3 支付失败 payType 1网上银行（通联） 2支付宝 3微信支付4：余额支付   6   ios
 *         5网上银行(汇付宝)
 */
public class Recharge extends HttpServlet {
	private static final long serialVersionUID = 4058444946640167655L;
	private List<String> orderCodes = new ArrayList<String>();
	private String result;
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
			String userId = request.getParameter("userId");
			String jucaiBills = request.getParameter("jucaiBills");
			String orderCode = request.getParameter("orderCode");
			String payState = request.getParameter("payState");
			String prePayDate=request.getParameter("prePayDate");
			String payDate = request.getParameter("payDate");
			String payType = request.getParameter("payType");
			String ip = request.getRemoteAddr();
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(jucaiBills)
							&& StringUtil.isInteger(jucaiBills)) {
						int bills = Integer.parseInt(jucaiBills);
							if (StringUtil.isNotNull(payState)
									&& StringUtil.isInteger(payState)&&StringUtil.isNotNull(prePayDate)) {
								int pState = Integer.parseInt(payState);
								if (StringUtil.isNotNull(payType)
										&& StringUtil.isInteger(payType)) {
									int type = Integer.parseInt(payType);
									if (pState == 2) {
										if (StringUtil.isNotNull(payDate)) {
											result = initRecharge(uId, bills,
													ip, orderCode, payDate,
													pState, type,prePayDate);
										} else {
											result = JsonUtil.getRetMsg(5,
													"支付时间不能为空");
										}
									} else {
										result = initRecharge(uId, bills, ip,
												orderCode, payDate, pState,
												type,prePayDate);
									}
								} else {
									result = JsonUtil.getRetMsg(1,
											"payType 参数异常");
								}
							} else {
								result = JsonUtil.getRetMsg(6, "支付状态异常");
							}
					} else {
						result = JsonUtil.getRetMsg(1, "jucaiBills 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "用户还没登录");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initRecharge(int uId, int bills, String ip,
			String orderCode, String payDate, int pState, int type,String prePayDate) {
		// 判断订单号是否已经存在
		Account a = AccountSer.findAccountByUserId(uId);
		AccountDetail detail = new AccountDetail();
		SysAccount account = SysAccountSer.findAccountInfo();
		detail.setDetailMoney(bills);
		detail.setDetailType(0);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detail.setOrderCode(orderCode);
		detail.setRemark("充值聚财币");
		detail.setState(0);
		detail.setUserId(uId);

		SysDetailAccount detailAccount = new SysDetailAccount();
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailAccount.setIsDel(0);
		detailAccount.setOrderId(0);
		detailAccount.setPrice(bills);
		detailAccount.setRecoderType(1);
		detailAccount.setType(1);
		detailAccount.setIp(ip);
		// 1网上银行（通联） 2支付宝 3微信支付4：余额支付 5网上银行(汇付宝)
		if (type == 1) {
			detailAccount.setRemark("用户通联充值聚财币");
		} else if (type == 2) {
			detailAccount.setRemark("用户支付宝充值聚财币");
		} else if (type == 3) {
			detailAccount.setRemark("用户微信充值聚财币");
		} else if (type == 4) {
			detailAccount.setRemark("用户余额充值聚财币");
		} else {
			detailAccount.setRemark("用户汇付宝充值聚财币");
		}
		detailAccount.setUserId(uId);
		int isSuccess = RollBackUtil.getInstance().recharge(orderCode, pState, payDate, ip,
				bills, a, uId, detail, account, detailAccount, type,prePayDate,orderCodes);
		int restBills=a.getJucaiBills()+bills;
		return isSuccess == 1 ? JsonUtil.getPurchrResult(0, "账单更新成功",restBills) : JsonUtil
				.getRetMsg(1, "账单更新失败");
	}
}
