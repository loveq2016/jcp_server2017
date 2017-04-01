package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.MySpecial;
import com.jucaipen.model.Special;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         购买 专辑
 */
public class PurchSpecial extends HttpServlet {
	private static final long serialVersionUID = 6311943643159751306L;
	private String result;
	private String ip;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ip = request.getRemoteAddr();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			String userId = request.getParameter("userId");
			String specialId = request.getParameter("specialId");
			String bills = request.getParameter("bills");
			String days = request.getParameter("days");
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(specialId)
							&& StringUtil.isInteger(specialId)) {
						int sId = Integer.parseInt(specialId);
						if (StringUtil.isNotNull(bills)
								&& StringUtil.isInteger(bills)) {
							int b = Integer.parseInt(bills);
							if (StringUtil.isNotNull(days)
									&& StringUtil.isInteger(days)) {
								int d = Integer.parseInt(days);
								result = purchVideo(uId, sId, b, d);
							}else{
								result=JsonUtil.getRetMsg(5,"days 参数异常");
							}
						}else{
							result=JsonUtil.getRetMsg(4,"bills 参数异常");
						}
					}else{
						result=JsonUtil.getRetMsg(3,"specialId 参数异常");
					}
				}else{
					result=JsonUtil.getRetMsg(2,"用户还没登录");
				}

			}else{
				result=JsonUtil.getRetMsg(1,"userId 参数异常");
			}
		}else{
			result=StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String purchVideo(int uId, int sId, int b, int days) {
		// 1、查看聚财币是否足够
		String startDate;
		String endDate = null;
		User user = UserServer.findBaseInfoById(uId);
		Account a = AccountSer.findAccountByUserId(uId);
		if(b<=0){
			return JsonUtil.getRetMsg(6,"暂不支持购买");
		}
		
		if (a == null || a.getJucaiBills() < b) {
			return JsonUtil.getRetMsg(1, "聚财币余额不足");
		}

		// 是否已经购买
		MySpecial mSpecial=MySpecialSer.getIsMySpecial(uId, sId);
		if (mSpecial != null
				&& TimeUtils.isLive(mSpecial.getStartDate(),
						mSpecial.getEndDate())) {
			// 续费
			return JsonUtil.getRetMsg(5, "当前用户在订阅时间段，不能继续订阅");
		} else {
			startDate = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			endDate = TimeUtils.format(TimeUtils.addBaseDay(new Date(), days),
					"yyyy-MM-dd HH:mm:ss");
		}

		Special special=SpecialSer.findSpecialById(sId);
		
		
		MySpecial mySpecial=new MySpecial();
		mySpecial.setEndDate(endDate);
		mySpecial.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		mySpecial.setIsDel(0);
		mySpecial.setIsStop(0);
		mySpecial.setRemark("购买专辑【"+special.getName()+"】");
		mySpecial.setSpecialId(sId);
		mySpecial.setStartDate(startDate);
		mySpecial.setUserId(uId);
		

		AccountDetail detail = new AccountDetail();
		detail.setDetailMoney(b);
		detail.setDetailType(1);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detail.setOrderCode("");
		detail.setRemark("购买专辑【" + special.getName()+ "】");
		detail.setState(0);
		detail.setUserId(uId);

		AccountDetail detailInteger = new AccountDetail();
		detailInteger.setDetailMoney(b);
		detailInteger.setDetailType(0);
		detailInteger.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailInteger.setIsDel(0);
		detailInteger.setOrderCode("");
		detailInteger.setRemark("购买专辑【" + special.getName() + "】，账户积分+" + b);
		detailInteger.setState(1);
		detailInteger.setUserId(uId);

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount sysDetailAccount = new SysDetailAccount();
		sysDetailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysDetailAccount.setIp(ip);
		sysDetailAccount.setIsDel(0);
		sysDetailAccount.setOrderId(0);
		sysDetailAccount.setPrice(b);
		sysDetailAccount.setRecoderType(2);
		sysDetailAccount.setType(11);
		sysDetailAccount.setUserId(uId);
		sysDetailAccount.setRemark("购买专辑【" + special.getName() + "】");

		int isSuccess = RollBackUtil.getInstance().purchSpecial(mySpecial, a, uId, b, detail,
				detailInteger, sysAccount, sysDetailAccount, user);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "购买视频成功") : JsonUtil
				.getRetMsg(1, "购买视频失败");
	}

}
