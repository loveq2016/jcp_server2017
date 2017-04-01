package com.jucaipen.main.purch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.HashedMap;
import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.Marker;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.model.User;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
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
	private Map<String, String> params=new HashedMap();
	private static final String SEND_LIVE_MSG="http://www.jucaipen.com/TeacherLive/ashx/VideoLive.ashx?action=APPSendMsg";

	
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
			String liveId=request.getParameter("liveId");
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
											markerMoney,liveId);
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
						result = JsonUtil.getRetMsg(3, "用户还没登录");
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

	private String initMarkerInfo(int uId, int typeId, int fId, int markerMoney,String liveId) {
		// 初始化打赏数据
		FamousTeacher teacher = null;
		int teacherId;
		HotIdea idea = null;
		String nickName = null;
		AccountDetail detail = new AccountDetail();
		AccountDetail detailIntegeral = new AccountDetail();
		Account account = AccountSer.findAccountByUserId(uId);
		int integeral = account.getIntegeral();
       
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
		contribute.setTeacherId(fId);
		contribute.setAllJucaiBills(markerMoney);

		SysAccount sysAccount = SysAccountSer.findAccountInfo();

		SysDetailAccount sysDetail = new SysDetailAccount();
		sysDetail.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		sysDetail.setIsDel(0);
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

		int isSuccess = RollBackUtil.getInstance().addReward(marker, detail, integeral,
				markerMoney, jucaiBills, uId, contribute, sysAccount,
				sysDetail, user, rebate, sysRebate, detailIntegeral);
		if( isSuccess == 1&&typeId==0){
			//打赏讲师
			String msg="<font style='color:#f00'>"+"\""+user.getUserName()+"\""+"打赏名师"+"\""+teacher.getNickName()+"\""+markerMoney+"个聚财币</font>";
			sendMsg(uId, liveId,msg, 0);
			/*String pushMsg=JsonUtil.createRewardMsg(false,uId,user.getUserName()+"打赏了"+markerMoney+"个聚财币");
			JPushClient client = JPushUtils.getJPush();
			PushPayload msgs = JPushUtils.createMsg("msg", "liveMsg", pushMsg, null);
		    PushResult res = JPushUtils.pushMsg(client, msgs);*/
		}
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "打赏成功") : JsonUtil
				.getRetMsg(1, "打赏失败");
	}
	
	
	/**
	 * @param uId
	 * @param lId
	 * @param msg
	 * @param toId
	 * @return   发送消息
	 */
	public  void sendMsg(int uId, String liveId, String msg, int toId){
		int gurdianId;
		User fromUser;
		int teacherId=0;
		User toUser;
		int lId=0;
		if(StringUtil.isNotNull(liveId)&&StringUtil.isInteger(liveId)){
			lId=Integer.parseInt(liveId);
		}
		VideoLive live = VideoLiveServer.getRoomInfo(lId);
		if(live!=null){
			teacherId=live.getTeacherId();
		}
		
		Guardian fromGuardian = null;
		if(uId>0){
			fromUser=UserServer.findUserChatInfo(uId);
			fromGuardian = GuardianSer.findIsGuardian(teacherId, uId);
			toUser=UserServer.findUserChatInfo(toId);
			if(toUser==null){
				toUser=new User();
			}
		}else{
			fromUser=new User();
			toUser=new User();
		}
		if(fromGuardian!=null){
			gurdianId=fromGuardian.getId();
		}else{
			gurdianId=0;
		}
		params.clear();
		params.put("msgtype", 1+"");
		params.put("lid", lId+"");
		params.put("msg", msg);
		params.put("userlevel", fromUser.getUserLeval()+"");
		params.put("isroomadmin", 1+"");
		params.put("issysadmin", 1+"");
		params.put("ischatadmin", 1+"");
		params.put("isserverid",1+"");
		params.put("isshouhuzhe",gurdianId+"");
		params.put("isteacher",fromUser.getIsTeacher()+"");
		params.put("buyproductid",fromUser.getBuyProductId()+"");
		params.put("nickName", "系统消息");
		params.put("userid", 0+"");
		String result=LoginUtil.sendHttpPost(SEND_LIVE_MSG, params);
	}
	
	

}
