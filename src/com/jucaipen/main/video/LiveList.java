package com.jucaipen.main.video;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Account;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.LiveRecoder;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.Video;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.LiveRecoderSaleSer;
import com.jucaipen.service.LiveRecoderSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.TxtLiveSaleSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LiveUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
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
	private String urls[]={"http://recordcdn.quklive.com/broadcast/activity/9458019977964845/20160719192025-20160719202126.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160721130012-20160721140053.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160725102510-20160725113411.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160727192010-20160727202111.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160729130000-20160729140032.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160801102551-20160801113353.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160802090747-20160802092208.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160809092055-20160809102256.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160815085910-20160815091500.m3u8",
	"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160816092110-20160816102025.m3u8"
	};
	private String result;
	private List<FamousTeacher> teachers = new ArrayList<FamousTeacher>();
	private String userAgent;
	private boolean check=false;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
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
									result = initTxtLive(p, uId);
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
		Object cached = DataManager.getCached(Constant.VIDEO_CACHE, "recoderVideo");
		if(cached!=null){
			return cached.toString();
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
						live.setCharge(recoderVideo.getLiveIsFree() == 2);
						// 单次直播价格
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
							LiveRecoderSale liveSale=null;
							LiveRecoderSale recoderSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(uId,resoder.getId(),liveType);
							int recoderId = live.getRecoderId();
							if(recoderId>0){
								liveSale= LiveRecoderSaleSer
										.getLiveSaleByUidAndLiveId(uId,recoderId,1);
								
							}
							if(recoderSale!=null||liveSale!=null){
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
						live.setVideoUrl(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : urls[RandomUtils.getRandomData(9, 0)]);
					}
				live.setOwnJucaiBills(ownJucaiBills);
				live.setIsPurch(isPurch);
				teachers.add(teacher);
			}
		}
		String liveList = JsonUtil.getLiveList(videos, teachers);
		new CacheUtils(Constant.VIDEO_CACHE).addToCache("recoderVideo", liveList);
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
		Object cached = DataManager.getCached(Constant.VIDEO_CACHE, "allVideo"+p);
		if(cached!=null){
			return cached.toString();
		}
		
		List<VideoLive> videos = VideoLiveServer.findAll(p);
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
					RecoderVideo recoderVideo;
					Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE, "recoderVideos"+live.getId());
					if(cached2==null){
						recoderVideo=RecoderVideoServer.getLastRecoderVideo(tId);
						new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacheVideos"+tId, recoderVideo);
					}else{
						recoderVideo=(RecoderVideo) cached2;
					}
					
					//直播已经结束 --返回最近的录播url
					// 是否收费 0 否 1 是
					if(recoderVideo!=null){
						live.setCharge(recoderVideo.getLiveIsFree() == 2);
						// 单次直播价格
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
						LiveRecoder resoder;
						Account account = AccountSer.findAccountByUserId(uId);
						live.setGradian(LiveUtil.isGradian(tId, uId));
						Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE, "recoderVideos"+live.getId());
						if(cached2==null){
							 resoder = LiveRecoderSer
									.getRecoderByLiveId(live.getId());
							new CacheUtils(Constant.TEACHER_CACHE).addToCache("recoderVideos"+live.getId(), resoder);
						}else{
							resoder=(LiveRecoder) cached2;
						}
						if(resoder!=null){
							LiveRecoderSale liveSale=null;
							LiveRecoderSale sale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(uId,resoder.getId(),liveType);
							int recoderId = live.getRecoderId();
							if(recoderId>0){
								liveSale= LiveRecoderSaleSer
										.getLiveSaleByUidAndLiveId(uId,recoderId,1);
								
							}
							if(sale!=null||liveSale!=null){
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
						live.setVideoUrl(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : urls[RandomUtils.getRandomData(9, 0)]);
					}
				live.setOwnJucaiBills(ownJucaiBills);
				live.setIsPurch(isPurch);
				teachers.add(teacher);
			}
		}
		String liveList = JsonUtil.getLiveList(videos, teachers);
		new CacheUtils(Constant.VIDEO_CACHE).addToCache("allVideo"+p, liveList);
		return liveList;
	}

	
	/**
	 * @param p
	 * @param uId
	 * @return  初始化文字直播 ---获取正在直播的文字直播
	 */
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
	}
}
