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
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.Tactics;
import com.jucaipen.model.TacticsSale;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.TacticsSaleSer;
import com.jucaipen.service.TacticsSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator 订阅战法信息
 */
public class OrderTactics extends HttpServlet {
	private static final long serialVersionUID = 1656206475308345758L;
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
			String tacticeId = request.getParameter("tacticsId");
			String days = request.getParameter("days");
			String bills = request.getParameter("bills");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(tacticeId)) {
							if (StringUtil.isInteger(tacticeId)) {
								int tId = Integer.parseInt(tacticeId);
									if (StringUtil.isNotNull(days)
											&& StringUtil.isInteger(days)) {
										int d = Integer.parseInt(days);
										if (StringUtil.isNotNull(bills)
												&& StringUtil.isInteger(bills)) {
											int b = Integer.parseInt(bills);
											result = orderTactics(uId, tId, d, b);
										} else {
											result = JsonUtil.getRetMsg(7,
													"bills 参数异常");
										}
									} else {
										result = JsonUtil.getRetMsg(6, "days 参数异常");
									}
							} else {
								result = JsonUtil.getRetMsg(5,
										"tacticeId 参数数字格式化异常和");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "tacticeId 参数不能为空");
						}
					} else {
						result = JsonUtil.getRetMsg(3, "用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}
		}else{
			result=StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String orderTactics(int uId, int tId, int days,
			int bills) {
		String startDate;
		String endDate = null;
		FamousTeacher teacher;
		User user = UserServer.findBaseInfoById(uId);
		Account account = AccountSer.findAccountByUserId(uId);
		if(bills<=0){
			return JsonUtil.getRetMsg(6,"暂不支持订阅");
		}
		
		if (account == null||account.getJucaiBills()<bills) {
			return JsonUtil.getRetMsg(3, "账户聚财币不足，请充值");
		}
		
		// 是否已经订阅
		TacticsSale saleMain = TacticsSaleSer.findTacticsIsSale(uId, tId);
		if (saleMain != null&&TimeUtils.isLive(saleMain.getStartDate(), saleMain.getEndDate())) {
			// 续费
			return JsonUtil.getRetMsg(5, "当前用户在订阅时间段，不能继续订阅");
		} else {
			startDate = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			endDate = TimeUtils.format(TimeUtils.addBaseDay(new Date(), days),
					"yyyy-MM-dd HH:mm:ss");
		}

		Tactics tactics = TacticsSer.findTacticsById(tId);

		TacticsSale sale = new TacticsSale();
		sale.setUserId(uId);
		sale.setTacticsId(tId);
		sale.setIp(ip);
		sale.setStartDate(startDate);
		sale.setEndDate(endDate);
		sale.setTelPhone(user.getMobileNum());
		sale.setIsStop(0);
		sale.setRemark("订阅成功");
		sale.setInsertDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 订阅战法信息

		// 账户详细 聚财币
		AccountDetail detail = new AccountDetail();
		// 积分
		AccountDetail detailInteger = new AccountDetail();
		detail.setOrderCode("");
		detailInteger.setOrderCode("");
		detail.setDetailMoney(bills);
		detailInteger.setDetailMoney(bills);
		// 订单类型 0收入，1消费
		detail.setDetailType(1);
		detailInteger.setDetailType(0);
		// 0聚财币，1积分
		detail.setState(0);
		detailInteger.setState(1);
		detail.setRemark("订阅战法《" + tactics.getTitle() + "》，时间："+(days/30)+"个月");
		detailInteger.setRemark("订阅战法《" + tactics.getTitle() + "》，时间："+(days/30)+"个月，账户积分+"
				+ bills);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detailInteger.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detailInteger.setIsDel(0);
		detail.setUserId(uId);
		detailInteger.setUserId(uId);

		teacher = FamousTeacherSer.findTeacherBaseInfo(tactics.getTeacherId());

		Rebate teacherRebate = new Rebate();
		teacherRebate.setType(0);
		teacherRebate.setTeacherId(tId);
		teacherRebate.setRebateMoney((teacher.getReturnRate() * bills));
		teacherRebate.setFromId(uId);
		teacherRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		teacherRebate.setRemark("用户订阅战法返利");

		Rebate sysRebate = new Rebate();
		sysRebate.setType(1);
		sysRebate.setTeacherId(teacher.getId());
		sysRebate.setRebateMoney(((1 - teacher.getReturnRate()) * bills));
		sysRebate.setFromId(uId);
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysRebate.setRemark("用户订阅战法返利");

		Contribute contribute = new Contribute();
		contribute.setAllJucaiBills(bills);
		contribute.setComType(10);
		contribute.setFk_id(tId);
		contribute.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		contribute.setUserId(uId);
		contribute.setTeacherId(teacher.getId());

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount sysDetailAccount = new SysDetailAccount();
		sysDetailAccount.setUserId(uId);
		// 记录类型：1用户充值，2系统赠送，3、用户提现，4，礼品消费，5、直播观点，6购买日志，7、购买问答，8、开通守护，9、订阅战法，10、购买视频，11购买专辑，12，系统赠送产品
		// 13,打赏
		sysDetailAccount.setType(9);
		sysDetailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysDetailAccount.setIsDel(0);
		sysDetailAccount.setIp(ip);
		sysDetailAccount.setOrderId(0);
		sysDetailAccount.setPrice(bills);
		// 1收入，2支出
		sysDetailAccount.setRecoderType(2);
		sysDetailAccount.setRemark("订阅战法《" + tactics.getTitle() + "》，时间："
				+ (days/30)+"个月");


		int isSuccess = RollBackUtil.getInstance().orderTactisc(sale, account, bills, uId,
				detail, detailInteger, teacherRebate, sysRebate, contribute,
				sysAccount, sysDetailAccount, user);

		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "订阅成功") : JsonUtil
				.getRetMsg(1, "订阅失败");
	}

}
