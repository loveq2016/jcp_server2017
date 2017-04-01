package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.TxtLiveSaleSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         购买文字直播 整个
 */
public class PurchTxtLive extends HttpServlet {
	private static final long serialVersionUID = 7355170506754734899L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ip = request.getRemoteAddr();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String userId = request.getParameter("userId");
			String txtLiveId = request.getParameter("txtLiveId");
			String bills = request.getParameter("bills");
			String days=request.getParameter("days");
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(txtLiveId)
							&& StringUtil.isInteger(txtLiveId)) {
						int txtId = Integer.parseInt(txtLiveId);
						if (StringUtil.isNotNull(bills)
								&& StringUtil.isInteger(bills)) {
							int b = Integer.parseInt(bills);
							if(StringUtil.isNotNull(days)&&StringUtil.isInteger(days)){
								int d=Integer.parseInt(days);
								result = purchTxtLive(ip, uId, b, txtId,d);
							}else{
								result = JsonUtil.getRetMsg(5, "days 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "bills 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(3, "txtLiveId 参数异常");
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

	private String purchTxtLive(String ip, int uId, int b, int txtId, int d) {
		Account account = AccountSer.findAccountByUserId(uId);
		TextLive txtLive = TxtLiveSer.findTextLiveById(txtId);
		User user = UserServer.findBaseInfoById(uId);
		int teacherId = txtLive.getTeacherId();
		FamousTeacher teacher = FamousTeacherSer
				.findFamousTeacherById(teacherId);
		List<TxtLiveSale> saled = TxtLiveSaleSer.findSaleByUserIdAndTiD(uId, teacher.getId());
		if(b<=0){
			return JsonUtil.getRetMsg(6, "暂不支持购买");
		}
		
		if(saled!=null){
			for(TxtLiveSale sale : saled){
				if(TimeUtils.isLive(sale.getStartDate(),sale.getEndDate())){
					return JsonUtil.getRetMsg(6, "文字直播已经购买，不能重复购买");
				}
			}
		}
		if (account == null || account.getJucaiBills() < b) {
			return JsonUtil.getRetMsg(5, "余额不足，请先充值");
		}
		
		
		String startDate = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String endDate = TimeUtils.format(TimeUtils.addBaseDay(new Date(), d),
				"yyyy-MM-dd HH:mm:ss");

		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(b);
		// 订单类型 0收入，1消费
		accountDetail.setDetailType(1);
		// 0聚财币，1积分
		accountDetail.setState(0);
		accountDetail.setOrderCode("");
		accountDetail.setRemark("购买【" + teacher.getNickName() + "】的文字直播《"
				+ txtLive.getTitle() + "》");
		accountDetail.setIsDel(0);
		accountDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetail.setUserId(uId);

		AccountDetail accountDetailIntegeral = new AccountDetail();
		accountDetailIntegeral.setDetailMoney(b);
		// 订单类型 0收入，1消费
		accountDetailIntegeral.setDetailType(0);
		// 0聚财币，1积分
		accountDetailIntegeral.setState(1);
		accountDetailIntegeral.setOrderCode("");
		accountDetailIntegeral.setRemark("购买【" + teacher.getNickName()
				+ "】的文字直播《" + txtLive.getTitle() + "》,积分+" + b);
		accountDetailIntegeral.setIsDel(0);
		accountDetailIntegeral.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetailIntegeral.setUserId(uId);

		TxtLiveSale sale = new TxtLiveSale();
		sale.setUserId(uId);
		sale.setTeacherId(teacherId);
		sale.setOrderCode("");
		sale.setFk_txtId(txtId);
		sale.setStartDate(startDate);
		sale.setEndDate(endDate);
		sale.setInsertDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sale.setPayPrice(b);
		sale.setRemark("用户购买(App支付)");

		Contribute contribute = new Contribute();
		contribute.setAllJucaiBills(b);
		contribute.setComType(12);
		contribute.setFk_id(txtId);
		contribute.setTeacherId(teacherId);
		contribute.setUserId(uId);
		contribute.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount detailAccount = new SysDetailAccount();
		detailAccount.setIp(ip);
		detailAccount.setIsDel(0);
		detailAccount.setOrderId(0);
		detailAccount.setPrice(b);

		detailAccount.setRecoderType(2);
		detailAccount.setType(5);

		detailAccount.setRemark("购买【" + teacher.getNickName() + "】的文字直播《"
				+ txtLive.getTitle() + "》");
		detailAccount.setUserId(uId);
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		Rebate rebate = new Rebate();
		rebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		rebate.setType(0);
		rebate.setRebateMoney(b * teacher.getReturnRate());
		rebate.setFromId(uId);
		rebate.setRemark("用户购买文字直播返利");
		rebate.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		Rebate sysRebate = new Rebate();
		sysRebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		sysRebate.setType(1);
		sysRebate.setRebateMoney(b * (1 - teacher.getReturnRate()));
		sysRebate.setFromId(uId);
		sysRebate.setRemark("用户购买文字直播返利");
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		int isSuccess = RollBackUtil.getInstance().purchTxtLive(account, accountDetail,
				accountDetailIntegeral, uId, b, sale, contribute, sysAccount,
				detailAccount, user, rebate, sysRebate);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "购买文字直播成功") : JsonUtil
				.getRetMsg(1, "购买文字直播失败");
	}

}
