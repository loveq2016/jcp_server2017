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
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.LiveDetailSale;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SaleRecoder;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveDetails;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.LiveDetailSaleSer;
import com.jucaipen.service.SaleRecoderSer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.TxtLiveDetaileSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 * 
 *         购买文字直播 单条信息
 */
public class PurchTxtDetails extends HttpServlet {
	private static final long serialVersionUID = 6893212497317566219L;
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
			String txtDetailId = request.getParameter("txtDetailId");
			String bills = request.getParameter("bills");
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(txtDetailId)
							&& StringUtil.isInteger(txtDetailId)) {
						int detailId = Integer.parseInt(txtDetailId);
						if (StringUtil.isNotNull(bills)
								&& StringUtil.isInteger(bills)) {
							int b = Integer.parseInt(bills);
							result = purchTxtDetails(ip, uId, b, detailId);
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
		out.print(result);
		out.flush();
		out.close();
	}

	private String purchTxtDetails(String ip, int uId, int b, int detailId) {
		Account account = AccountSer.findAccountByUserId(uId);
		TxtLiveDetails liveDetail = TxtLiveDetaileSer
				.findTextDetaileById(detailId);
		User user = UserServer.findBaseInfoById(uId);
		int liveId = liveDetail.getFk_liveId();
		TextLive textLive = TxtLiveSer.findTextLiveById(liveId);
		int teacherId = textLive.getTeacherId();
		FamousTeacher teacher = FamousTeacherSer
				.findFamousTeacherById(teacherId);
		LiveDetailSale detailSale = LiveDetailSaleSer
				.findSaleByUserIdAndTxtIdAndDetailId(uId, detailId);
		
		
		Guardian gurdian = GuardianSer.findIsGuardian(teacherId, uId);
		if(gurdian!=null){
			return JsonUtil.getRetMsg(8, "已经成为讲师守护者，不需要购买");
		}
		
		if (detailSale != null) {
			return JsonUtil.getRetMsg(6, "直播详细已经购买，不能重复购买");
		}
		
		if(b<=0){
			return JsonUtil.getRetMsg(6,"暂不支持购买");
		}
		
		SiteConfig config = SiteConfigSer.findSiteConfig();
		int count=config.getLiveDetailCount();
		int num=SaleRecoderSer.findSaleCountByRecoder(uId);
		if(num>=count){
			return JsonUtil.getRetMsg(10,"试看次数用完，需要开通守护查看更多");
		}
		
		if (account == null || account.getJucaiBills() < b) {
			return JsonUtil.getRetMsg(5, "余额不足，请先充值");
		}

		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setDetailMoney(b);
		// 订单类型 0收入，1消费
		accountDetail.setDetailType(1);
		// 0聚财币，1积分
		accountDetail.setState(0);
		accountDetail.setOrderCode("");
		accountDetail.setRemark("购买【" + teacher.getNickName() + "】的直播《"
				+ liveDetail.getBodys() + "》的观点");
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
				+ "】的直播《" + liveDetail.getBodys() + "》的观点,积分+" + b);
		accountDetailIntegeral.setIsDel(0);
		accountDetailIntegeral.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		accountDetailIntegeral.setUserId(uId);

		LiveDetailSale sale = new LiveDetailSale();
		sale.setUserId(uId);
		sale.setTeacherId(teacherId);
		sale.setOrderCode("");
		sale.setLiveDetailId(detailId);
		sale.setInsertDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sale.setPayPrice(b);
		sale.setRemark("用户购买(App支付)");

		Contribute contribute = new Contribute();
		contribute.setAllJucaiBills(b);
		contribute.setComType(8);
		contribute.setFk_id(detailId);
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

		detailAccount.setRemark("购买【" + teacher.getNickName() + "】的直播《"
				+ liveDetail.getBodys() + "》的观点");
		detailAccount.setUserId(uId);
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		
		
		SaleRecoder recoder=new SaleRecoder();
		recoder.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		recoder.setGuandianId(detailId);
		recoder.setIsDel(0);
		recoder.setLiveId(0);
		recoder.setReadCount(1);
		recoder.setUserId(uId);
		recoder.setType(0);
		recoder.setRemark("用户试看直播");

		Rebate rebate = new Rebate();
		rebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		rebate.setType(0);
		rebate.setRebateMoney(b * teacher.getReturnRate());
		rebate.setFromId(uId);
		rebate.setRemark("用户购买直播观点返利");
		rebate.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		Rebate sysRebate = new Rebate();
		sysRebate.setTeacherId(teacherId);
		// 返利类型（0讲师返利记录，1系统收入记录）
		sysRebate.setType(1);
		sysRebate.setRebateMoney(b * (1 - teacher.getReturnRate()));
		sysRebate.setFromId(uId);
		sysRebate.setRemark("用户购买直播观点返利");
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		int isSuccess = RollBackUtil.getInstance().purchTxtDetail(user, b, sysAccount,
				rebate, sysRebate, sale, account, accountDetail,
				accountDetailIntegeral, uId, detailAccount, contribute,recoder);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "购买直播观点成功") : JsonUtil
				.getRetMsg(1, "购买直播观点失败");
	}

}
