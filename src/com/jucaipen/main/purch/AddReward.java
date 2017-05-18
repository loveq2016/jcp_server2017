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
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.Marker;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *     打赏 typeId (0 打赏讲师) （1 打赏观点）
 */
public class AddReward extends HttpServlet {
	private static final long serialVersionUID = 7660136154061926366L;
	private String result;
	private String ip;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String usertAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(usertAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, usertAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String typeId = request.getParameter("typeId");
			String userId = request.getParameter("userId");
			String fkId = request.getParameter("fkId");
			ip = request.getRemoteAddr();
			String allIntegeral = request.getParameter("allIntegeral");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						if (StringUtil.isNotNull(typeId)
								&& StringUtil.isInteger(typeId)) {
							int tId = Integer.parseInt(typeId);
							if (StringUtil.isNotNull(fkId)
									&& StringUtil.isInteger(fkId)) {
								int fId = Integer.parseInt(fkId);
								if (StringUtil.isNotNull(allIntegeral)
										&& StringUtil.isInteger(allIntegeral)) {
									int markerMoney = Integer
											.parseInt(allIntegeral);
									result = initMarkerInfo(uId, tId, fId,
											markerMoney);
								} else {
									result = JsonUtil.getRetMsg(6,
											"allIntegeral 参数异常");
								}
							} else {
								result = JsonUtil.getRetMsg(5, "fkId 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "typeId 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(8, "用户还没登录");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
			}

		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initMarkerInfo(int uId, int typeId, int fId, int markerMoney) {
		// 初始化打赏数据
		FamousTeacher teacher = null;
		int teacherId;
		HotIdea idea = null;
		String nickName = null;
		AccountDetail detail = new AccountDetail();
		AccountDetail detailIntegeral = new AccountDetail();
		Account account = AccountSer.findAccountByUserId(uId);
		int integeral = account.getIntegeral();
		int isTest = UserServer.findIsTest(uId);
		if(isTest==1){
			return JsonUtil.getPurchrResult(0,"测试账号打赏成功",account.getJucaiBills(),0);
		}
       
		if(markerMoney<=0){
			return JsonUtil.getRetMsg(6,"打赏金额异常");
		}
		
		if (account == null||account.getJucaiBills()<markerMoney) {
			return JsonUtil.getRetMsg(3, "账户聚财币不足，请充值");
		}
		 int jucaiBills=account.getJucaiBills();
		User user = UserServer.findBaseInfoById(uId);
		Marker marker = new Marker();
		if (typeId == 0) {
			// 打赏讲师
			teacherId = fId;
			teacher = FamousTeacherSer.findTeacherBaseInfo(fId);
			nickName = teacher.getNickName();
			detail.setRemark("打赏给名师：【" + nickName + "】");
			detailIntegeral.setRemark("打赏给名师：【" + nickName + "】,账户积分+"
					+ markerMoney);
			marker.setType(1);
		} else {
			// 打赏观点
			idea = HotIdeaServ.findIdeaById(fId);
			teacherId = idea.getTeacherId();
			teacher = FamousTeacherSer.findTeacherBaseInfo(teacherId);
			String title = idea.getTitle();
			detail.setRemark("打赏观点：【" + title + "】");
			detailIntegeral.setRemark("打赏观点：【" + title + "】,账户积分+"
					+ markerMoney);
			marker.setType(2);
		}
		marker.setIp(ip);
		marker.setMarkerCount(markerMoney);
		marker.setUserId(uId);
		marker.setIdeaId(fId);
		marker.setInsertDate(TimeUtils.format(new Date()));

		detail.setOrderCode("");
		detailIntegeral.setOrderCode("");
		detail.setDetailMoney(markerMoney);
		detailIntegeral.setDetailMoney(markerMoney);
		// 订单类型 0收入，1消费
		detail.setDetailType(1);
		detailIntegeral.setDetailType(0);
		// 0聚财币，1积分
		detail.setState(0);
		detailIntegeral.setState(1);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detailIntegeral.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detailIntegeral.setIsDel(0);
		detail.setUserId(uId);
		detailIntegeral.setUserId(uId);
		Contribute contribute = new Contribute();
		if (typeId == 0) {
			// 打赏讲师
			contribute.setComType(5);
			contribute.setFk_id(0);
		} else {
			contribute.setComType(9);
			contribute.setFk_id(fId);
		}
		contribute.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		contribute.setUserId(uId);
		contribute.setTeacherId(teacherId);
		contribute.setAllJucaiBills(markerMoney);

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount sysDetail = new SysDetailAccount();
		sysDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysDetail.setIsDel(0);
		sysDetail.setUserId(uId);
		sysDetail.setIp(ip);
		sysDetail.setOrderId(0);
		sysDetail.setPrice(markerMoney);
		sysDetail.setRecoderType(2);
		if (typeId == 0) {
			sysDetail.setRemark("打赏给名师：【" + teacher.getNickName() + "】");
		} else {
			sysDetail.setRemark("打赏观点：【" + idea.getTitle() + "】");
		}

		sysDetail.setType(13);
		sysDetail.setUserId(uId);

		// 讲师返利
		Rebate rebate = new Rebate();
		rebate.setRebateMoney((markerMoney * teacher.getReturnRate()));
		rebate.setType(0);
		rebate.setTeacherId(teacherId);
		rebate.setFromId(uId);
		rebate.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		rebate.setRemark("用户打赏返利");
		// 系统返利
		Rebate sysRebate = new Rebate();
		sysRebate.setRebateMoney((markerMoney * (1 - teacher.getReturnRate())));
		sysRebate.setType(1);
		sysRebate.setTeacherId(teacherId);
		sysRebate.setFromId(uId);
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysRebate.setRemark("用户打赏返利");

		int isSuccess = RollBackUtil.getInstance().addRewardNoCommit(marker, detail, integeral,
				markerMoney, jucaiBills, uId, contribute, sysAccount,
				sysDetail, user, rebate, sysRebate, detailIntegeral);
		int restBills=account.getJucaiBills()-markerMoney;
		return isSuccess == 1 ? JsonUtil.getPurchrResult(0,"打赏成功",restBills,0) : JsonUtil
				.getRetMsg(1, "打赏失败");
	}
}
