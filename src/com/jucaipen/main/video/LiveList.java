package com.jucaipen.main.video;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Account;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.LiveRecoder;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.Video;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.LiveRecoderSaleSer;
import com.jucaipen.service.LiveRecoderSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LiveUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         直播 liveType 0 文字直播 
 * 
 *         1 视频直播 返回参数：
 *         
 *         2 推荐直播
 * 
 */
public class LiveList extends HttpServlet {
	private static final long serialVersionUID = -3535325712984870701L;
	private String result;
	private List<FamousTeacher> teachers = new ArrayList<FamousTeacher>();
	private String userAgent;
	private boolean check=false;
	private boolean hasCache;
	private String baseUrl = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info";
	private Map<String,String> param=new HashMap<String,String>();
	private String GET_SIGN="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private String appId="1400028429";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
		userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		response.setDateHeader("expires", 0); 
		ServletContext servletContext = request.getServletContext();
		check=(Boolean) servletContext.getAttribute("check");
		if (isDevice == HeaderUtil.PHONE_APP) {
			String liveType = request.getParameter("liveType");
			String page = request.getParameter("page");
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(liveType)) {
				if (StringUtil.isInteger(liveType)) {
					int type = Integer.parseInt(liveType);
					if (StringUtil.isNotNull(userId)
							&& StringUtil.isInteger(userId)) {
						int uId = Integer.parseInt(userId);
						if(type==2){
							// 推荐直播
							result=initRecoderLive(uId);
						}else{
							if (StringUtil.isNotNull(page)
									&& StringUtil.isInteger(page)) {
								int p = Integer.parseInt(page);
								if (type == 0) {
									// 文字直播
								//	result = initTxtLive(p, uId);
								} else if(type==1){
									// 视频直播
									result = initLive(p, uId);
								}
							} else {
								result = JsonUtil.getRetMsg(3, "page 参数异常");
							}
							
						}
					} else {
						result = JsonUtil.getRetMsg(4, "userId 参数异常");
					}
					
				} else {
					result = JsonUtil.getRetMsg(2, "liveType 参数数字格式异常");
				}

			} else {
				result = JsonUtil.getRetMsg(1, "liveType 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @param uId
	 * @return   推荐直播
	 */
	private String initRecoderLive(int uId) {
		int isPurch = 1;
		int ownJucaiBills = 0;
		if (uId <= 0) {
			isPurch = 1;
		}
		teachers.clear();
		List<VideoLive> videos = VideoLiveServer.findRecoderLive(4);
		if (videos != null) {
			for (VideoLive live : videos) {
				int liveType;
				int tId = live.getTeacherId();
				FamousTeacher teacher = FamousTeacherSer
						.findFamousTeacherById(tId);
				if (teacher == null) {
					teacher = new FamousTeacher();
				}
				// 讲师是否开通直播功能 0 否 1 是
				live.setLiveVideo(teacher.getIsUserLiveUrl() == 1);
				live.setRoomId(teacher.getFk_UserId());
				// 播放url
				if(live.getIsEnd()!=2){
					liveType=2;
					RecoderVideo recoderVideo=RecoderVideoServer.getLastRecoderVideo(tId);
					//直播已经结束 --返回最近的录播url
					// 是否收费 0 否 1 是
					if(recoderVideo!=null){
						live.setTitle(recoderVideo.getTitle());
						live.setCharge(recoderVideo.getLiveIsFree() == 2);
						// 单次直播价格
						live.setId(recoderVideo.getId());
						live.setLivePrice(recoderVideo.getLivePrice());
						live.setVideoUrl(recoderVideo.getLiveUrl1());
						live.setRecoderId(Integer.parseInt(recoderVideo.getExt1()));
					}else{
						live.setCharge(false);
						// 单次直播价格
						live.setLivePrice(0);
						live.setVideoUrl("");
					}
				}else{
					int roomId = FamousTeacherSer.findRoomInfo(tId);
					live.setXnRenQi(getRoomInfo(roomId));
					liveType=1;
					// 是否收费 0 否 1 是
					live.setCharge(teacher.getLiveFree() == 1);
					// 单次直播价格
					live.setLivePrice(teacher.getLivePrice());
					live.setVideoUrl(teacher.getAppLiveUrl());
				}
				live.setTeacherName(teacher.getNickName());
				live.setTeacherFace(teacher.getHeadFace());
				// 默认未开通守护
				live.setGradian(false);
			//	if (live.isCharge()) {
					if (uId > 0) {
						// 是否开通守护
						Account account = AccountSer.findAccountByUserId(uId);
						live.setGradian(LiveUtil.isGradian(tId, uId));
						LiveRecoder resoder = LiveRecoderSer
						.getRecoderByLiveId(live.getId());
						if(resoder!=null){
							live.setTitle(resoder.getTitle());
							LiveRecoderSale liveSale=null;
							LiveRecoderSale recoderSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(uId,resoder.getId(),liveType);
							int recoderId = live.getRecoderId();
							if(recoderId>0){
								liveSale= LiveRecoderSaleSer
										.getLiveSaleByUidAndLiveId(uId,recoderId,1);
							}
							int isTest=UserServer.findIsTest(uId);
							if(recoderSale!=null||liveSale!=null||isTest==1){
								isPurch = 0;
							}else{
								isPurch = 1;
							}
						}else{
							isPurch = 1;
						}
						if (account != null) {
							ownJucaiBills = account.getJucaiBills();
						}
					} else {
						isPurch = 1;
					}
				//}
					if(liveType==2&&check){
						Video video2 = VideoServer.findLastVideoByTeacher(tId);
						live.setVideoUrl(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : Constant.urls[RandomUtils.getRandomData(9, 0)]);
					}
				live.setOwnJucaiBills(ownJucaiBills);
				live.setIsPurch(isPurch);
				teachers.add(teacher);
			}
		}
		String liveList = JsonUtil.getLiveList(videos, teachers);
		return liveList;
	}

	private String initLive(int p, int uId) {
		// 初始化视频直播
		int isPurch = 1;
		int ownJucaiBills = 0;
		if (uId <= 0) {
			isPurch = 1;
		}
		teachers.clear();
		List<VideoLive> videos = VideoLiveServer.findAll(p,10.0);
		if (videos != null) {
			for (VideoLive live : videos) {
				int liveType;
				int tId = live.getTeacherId();
				FamousTeacher teacher = FamousTeacherSer
						.findFamousTeacherById(tId);
				if (teacher == null) {
					teacher = new FamousTeacher();
				}
				// 讲师是否开通直播功能 0 否 1 是
				live.setLiveVideo(teacher.getIsUserLiveUrl() == 1);
				// 播放url
				if(live.getIsEnd()!=2){
					liveType=2;
					RecoderVideo recoderVideo=RecoderVideoServer.getLastRecoderVideo(tId);;
					//直播已经结束 --返回最近的录播url
					// 是否收费 0 否 1 是
					if(recoderVideo!=null){
						live.setCharge(recoderVideo.getLiveIsFree() == 2);
						// 单次直播价格
						live.setId(recoderVideo.getId());
						live.setLivePrice(recoderVideo.getLivePrice());
						live.setVideoUrl(recoderVideo.getLiveUrl1());
						live.setRecoderId(Integer.parseInt(recoderVideo.getExt1()));
					}else{
						live.setCharge(false);
						// 单次直播价格
						live.setLivePrice(0);
						live.setVideoUrl("");
					}
				}else{
					int roomId = FamousTeacherSer.findRoomInfo(tId);
					live.setXnRenQi(getRoomInfo(roomId));
					liveType=1;
					// 是否收费 0 否 1 是
					live.setCharge(teacher.getLiveFree() == 1);
					live.setRoomId(teacher.getFk_UserId());
					// 单次直播价格
					live.setLivePrice(teacher.getLivePrice());
					live.setVideoUrl(teacher.getAppLiveUrl());
				}
				live.setTeacherName(teacher.getNickName());
				live.setTeacherFace(teacher.getHeadFace());
				// 默认未开通守护
				live.setGradian(false);
			//	if (live.isCharge()) {
					if (uId > 0) {
						// 是否开通守护
						LiveRecoder resoder= LiveRecoderSer
								.getRecoderByLiveId(live.getId());
						Account account = AccountSer.findAccountByUserId(uId);
						live.setGradian(LiveUtil.isGradian(tId, uId));
						if(resoder!=null){
							live.setTitle(resoder.getTitle());
							LiveRecoderSale liveSale=null;
							LiveRecoderSale sale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(uId,resoder.getId(),liveType);
							int recoderId = live.getRecoderId();
							if(recoderId>0){
								liveSale= LiveRecoderSaleSer
										.getLiveSaleByUidAndLiveId(uId,recoderId,1);
								
							}
							int isTest=UserServer.findIsTest(uId);
							if(sale!=null||liveSale!=null||isTest==1){
								isPurch = 0;
							}else{
								isPurch = 1;
							}
						}else{
							isPurch = 1;
						}
						if (account != null) {
							ownJucaiBills = account.getJucaiBills();
						}
					} else {
						isPurch = 1;
					}
				//}
					if(liveType==2&&check){
						Video video2 = VideoServer.findLastVideoByTeacher(tId);
						live.setVideoUrl(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : Constant.urls[RandomUtils.getRandomData(9, 0)]);
					}
				live.setOwnJucaiBills(ownJucaiBills);
				live.setIsPurch(isPurch);
				teachers.add(teacher);
			}
		}
		String liveList = JsonUtil.getLiveList(videos, teachers);
		return liveList;
	}

	
	/**
	 * @param p
	 * @param uId
	 * @return  初始化文字直播 ---获取正在直播的文字直播
	 *//*
	private String initTxtLive(int p, int uId) {
		int isPurch = 1;
		int ownJucaiBills = 0;
		teachers.clear();
		List<TextLive> txtLives = TxtLiveSer.findTextLiveByIsEnd(2);
		if (txtLives.size() <= 0) {
			// 获取前一天的文字直播信息
			String time = TimeUtils.getLastDate(-1);
			txtLives = TxtLiveSer.findLastLive(time);
		}
		if (txtLives.size() <= 0) {
			String time = TimeUtils.getLastDate(-2);
			txtLives = TxtLiveSer.findLastLive(time);
		}
		for (TextLive txt : txtLives) {
			int tId = txt.getTeacherId();
			Guardian guardian = GuardianSer.findIsGuardian(tId, uId);
			FamousTeacher teacher = FamousTeacherSer.findFamousTeacherById(tId);
			if (teacher == null) {
				teacher = new FamousTeacher();
			}
			if (uId > 0) {
				Account account = AccountSer.findAccountByUserId(uId);
				List<TxtLiveSale> sale = TxtLiveSaleSer.findSaleByUserIdAndTiD(
						uId, tId);
				if (sale != null && sale.size() > 0) {
					for (TxtLiveSale sa : sale) {
						if (TimeUtils
								.isLive(sa.getStartDate(), sa.getEndDate())) {
							isPurch = 0;
							break;
						} else {
							isPurch = 1;
						}
					}
				} else {
					isPurch = 1;
				}
				if (account != null) {
					ownJucaiBills = account.getJucaiBills();
				}
			}
			txt.setOwnJucaiBills(ownJucaiBills);
			teachers.add(teacher);
			txt.setGuardian(guardian != null);
			txt.setIsPurch(guardian != null ? 0 : isPurch);
		}
		return JsonUtil.getTxtLiveList(txtLives, teachers);
	}*/
	
	/**
	 * 获取聊天室详细信息
	 */
	public int getRoomInfo(int roomId) {
		JsonObject object = new JsonObject();
		object.addProperty("GroupId", roomId+"");
		object.addProperty("Limit", 1);
		object.addProperty("Offset", 0);
		String onLineInfo= LoginUtil.sendPostStr(createUrl(baseUrl, getSign("admin")),
				object.toString(), null);
		JSONObject jsonObj = new JSONObject(onLineInfo);
		String ok = jsonObj.optString("ActionStatus");
		if ("OK".equals(ok)) {
			return jsonObj.optInt("MemberNum", 0);
		}else{
			return 0;
		}
	}
	
	/**
	 * @param base
	 * @param sign
	 * @return 拼接腾讯云URL
	 */
	private String createUrl(String base, String sign) {
		StringBuffer buffer = new StringBuffer(base);
		buffer.append("?usersig=" + sign + "&");
		buffer.append("identifier=admin" + "&");
		buffer.append("sdkappid=" + appId + "&");
		buffer.append("random=" + RandomUtils.getRandomData(8) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}
	
	/**
	 * @return 获取管理员sign
	 */
	private String getSign(String a) {
		Object cached = DataManager.getCached(Constant.CACHE_SIGN, "userSign"+a, hasCache);
		if(cached!=null){
			return cached.toString();
		}
		param.clear();
		param.put("username", a);
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object = new JSONObject(signResult);
		boolean isCreate = object.optBoolean("Result");
		if (isCreate) {
			String sign = object.optString("UserSig");
			new CacheUtils(Constant.CACHE_SIGN).addToCache("userSign"+a, sign);
			return sign;
		}
		return null;
	}
}
