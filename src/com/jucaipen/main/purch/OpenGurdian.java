package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import com.jucaipen.model.LiveRecoder;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.LiveRecoderSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator
 * 
 *         开通守护     1   购买单次直播       2   购买单次点播      0  开通
 */
public class OpenGurdian extends HttpServlet {
	private static final long serialVersionUID = 7215614542250498399L;
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
		if (isDevice == HeaderUtil.PHONE_APP) {
			String days = request.getParameter("days");
			String userId = request.getParameter("userId");
			String teacherId = request.getParameter("teacherId");
			String bills = request.getParameter("bills");
			String type=request.getParameter("type");
			String liveId=request.getParameter("liveId");
			if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(teacherId)
							&& StringUtil.isInteger(teacherId)) {
						int tId = Integer.parseInt(teacherId);
						if (StringUtil.isNotNull(bills)
								&& StringUtil.isInteger(bills)) {
							int b = Integer.parseInt(bills);
						if(StringUtil.isNotNull(type)&&StringUtil.isInteger(type)){
							int typeId=Integer.parseInt(type);
							if(typeId==1||typeId==2){
								//单次购买
								if(StringUtil.isNotNull(liveId)
										&& StringUtil.isInteger(liveId)){
									int lId=Integer.parseInt(liveId);
									result=openGurdian(uId, tId, 0, b,typeId,lId);
								}else{
									result = JsonUtil.getRetMsg(12, "liveId 参数异常");
								}
							}else{
								//开通守护
								if (StringUtil.isNotNull(days)
										&& StringUtil.isInteger(days)) {
									int d = Integer.parseInt(days);
									result=openGurdian(uId, tId, d, b,typeId,0);
								} else {
									result = JsonUtil.getRetMsg(1, "days 参数异常");
								}
							}
						}else{
							result = JsonUtil.getRetMsg(11, "type 参数异常");
						}
						}else{
							result = JsonUtil.getRetMsg(3, "bill 参数异常");
							
						}
					} else {
						result = JsonUtil.getRetMsg(2, "teacherId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "用户登录失败");
				}

			} else {
				result = JsonUtil.getRetMsg(4, "userId 参数异常");
			}

		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String openGurdian(int uId, int tId, int d, int  b,int type,int liveId) {
		// 聚财币是否足够
		String startDate;
		String endDate = null;
		int gurdianId = 0;
		LiveRecoderSale sale=null;
		Guardian guardian=null;
		
		Account account = AccountSer.findAccountByUserId(uId);
		FamousTeacher teacher = FamousTeacherSer.findTeacherBaseInfo(tId);
		User user = UserServer.querryIntegeralByUid(uId);
		if(b<=0){
			return JsonUtil.getRetMsg(6, "暂不支持开通");
		}
		
		if (account == null||account.getJucaiBills() < b) {
			return JsonUtil.getRetMsg(3, "账户聚财币不足，请充值");
		}
		
		if(type==1||type==2){
			//单次购买
			sale=new LiveRecoderSale();
			sale.setRecoderType(type);
			if(type==1){
				//直播
				LiveRecoder recoder = LiveRecoderSer.getRecoderByLiveId(liveId);
				sale.setLiveRecoderId(recoder.getId());
				sale.setRemark("购买【"+teacher.getNickName()+"】直播视频1次");
			}else{
				//点播
				sale.setLiveRecoderId(liveId);
				sale.setRemark("购买【"+teacher.getNickName()+"】点播视频1次");
			}
			sale.setPayBills(b);
			sale.setPurchDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sale.setTeacherId(tId);
			sale.setUserId(uId);
		}else{
			//开通守护
			Guardian guardianM = GuardianSer.findIsGuardian(tId, uId);
			if (guardianM != null) {
				// 续约
				gurdianId = guardianM.getId();
				startDate = guardianM.getStartDate();
				try {
					Date endD = TimeUtils.parse(guardianM.getEndDate(),
							"yyyy-MM-dd HH:mm:ss");
					endDate = TimeUtils.format(TimeUtils.addBaseDay(endD, d),
							"yyyy-MM-dd HH:mm:ss");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				startDate = TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
				endDate = TimeUtils.format(TimeUtils.addBaseDay(new Date(), d),
						"yyyy-MM-dd HH:mm:ss");
			}
			
			guardian = new Guardian();
			guardian.setIp(ip);
			guardian.setTeacherId(tId);
			guardian.setUserId(uId);
			guardian.setInsertDate(TimeUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			guardian.setStartDate(startDate);
			guardian.setEndDate(endDate);
			guardian.setState(0);
		}
		// 讲师返利
		Rebate rebate = new Rebate();
		rebate.setRebateMoney((b * teacher.getReturnRate()));
		rebate.setFromId(uId);
		rebate.setTeacherId(tId);
		rebate.setRemark("用户开通守护返利");
		rebate.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		rebate.setType(0);
		// 系统返利
		Rebate sysRebate = new Rebate();
		sysRebate.setRebateMoney(b * (1 - teacher.getReturnRate()));
		sysRebate.setFromId(uId);
		sysRebate.setTeacherId(tId);
		sysRebate.setRemark("用户开通守护返利");
		sysRebate.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysRebate.setType(1);

		AccountDetail detailJ = new AccountDetail();
		detailJ.setDetailMoney(b);
		if(type==1){
			detailJ.setRemark("购买【" + teacher.getNickName() + "】的视频直播");
		}else if(type==2){
			detailJ.setRemark("购买【" + teacher.getNickName() + "】的视频点播");
		}else{
			detailJ.setRemark("成为【" + teacher.getNickName() + "】的守护者【" + d + "】天");
		}
		detailJ.setDetailType(1);
		detailJ.setState(0);
		detailJ.setOrderCode("");
		detailJ.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailJ.setIsDel(0);
		detailJ.setUserId(uId);

		AccountDetail detailIntegeral = new AccountDetail();
		detailIntegeral.setDetailMoney(b);
		if(type==1){
			detailIntegeral.setRemark("购买【" + teacher.getNickName() + "】的视频直播,账户积分+" + b);
		}else if(type==2){
			detailIntegeral.setRemark("购买【" + teacher.getNickName() + "】的视频点播,账户积分+" + b);
		}else{
			detailIntegeral.setRemark("成为【" + teacher.getNickName() + "】的守护者【" + d
					+ "】天，账户积分+" + b);
		}
		detailIntegeral.setDetailType(0);
		detailIntegeral.setState(1);
		detailIntegeral.setOrderCode("");
		detailIntegeral.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailIntegeral.setIsDel(0);
		detailIntegeral.setUserId(uId);

		SysAccount account2 = SysAccountSer.findAccountInfo();

		SysDetailAccount detailAccount = new SysDetailAccount();
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailAccount.setIsDel(0);
		detailAccount.setOrderId(0);
		detailAccount.setPrice(b);
		detailAccount.setRecoderType(2);
		detailAccount.setType(8);
		detailAccount.setIp(ip);
		if(type==1){
			detailAccount.setRemark("购买【" + teacher.getNickName() + "】的视频直播");
		}else if(type==2){
			detailAccount.setRemark("购买【" + teacher.getNickName() + "】的视频点播");
		}else{
			detailAccount.setRemark("成为【" + teacher.getNickName() + "】的守护者【" + d
					+ "】天");
		}
		detailAccount.setUserId(uId);
		Contribute contribute = new Contribute();
		contribute.setAllJucaiBills(b);
		contribute.setComType(3);
		contribute.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		contribute.setTeacherId(tId);
		contribute.setUserId(uId);
		contribute.setFk_id(0);

		int isSuccess = RollBackUtil.getInstance().synchrGuardian(guardian, rebate, account,
				b, detailJ, detailIntegeral, uId, user, account2,
				detailAccount, contribute, teacher, sysRebate, gurdianId,type,sale);
		return isSuccess == 1 ? JsonUtil.getOpenGurdianSuccess(guardian,type)
				: JsonUtil.getRetMsg(1, "开通守护失败");
	}

}
