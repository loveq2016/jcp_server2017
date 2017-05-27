package com.jucaipen.main.index.famous;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Account;
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
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LiveUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author
 * 
 *   Administrator type (0 观点) (1 问答) (2 文字直播) (3 视频直播)
 */
@SuppressWarnings("serial")
public class QuerryTeacherIdea extends HttpServlet {
	private String result;
	private String baseUrl = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info";
	private boolean check=false;
	private boolean hasCache;
	private Map<String,String> param=new HashMap<String,String>();
	private String GET_SIGN="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private String appId="1400028429";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isIndex = request.getParameter("isIndex");
		String teacherId = request.getParameter("teacherId");
		String typeId = request.getParameter("typeId");
		String page = request.getParameter("page");
		response.setDateHeader("expires", 0); 
		hasCache=(Boolean) request.getServletContext().getAttribute("hasCache");
		String userId = request.getParameter("userId");
		check = (Boolean) request.getServletContext().getAttribute("check");
		if (StringUtil.isNotNull(teacherId)) {
			if (StringUtil.isInteger(teacherId)) {
				int tId = Integer.parseInt(teacherId);
				if (StringUtil.isNotNull(typeId)
						&& StringUtil.isInteger(typeId)) {
					int type = Integer.parseInt(typeId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						if (StringUtil.isNotNull(isIndex)
								&& StringUtil.isInteger(isIndex)) {
							int index = Integer.parseInt(isIndex);
							if (StringUtil.isNotNull(userId)
									&& StringUtil.isInteger(userId)) {
								int uId = Integer.parseInt(userId);
								result = initTeacherIdeaData(tId, type, p,
										index, uId);
							} else {
								result = JsonUtil.getRetMsg(6, "userId 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(5, "isIndex 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(4, "page 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "typeId 参数异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "teacherId 参数数字格式化异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "teacherId 参数不能为空");
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initTeacherIdeaData(int tId, int type, int p, int isIndex,
			int usId) {
		// 初始化讲师热门观点 问答 文字直播 直播信息
		FamousTeacher teacher = FamousTeacherSer.findFamousTeacherById(tId);
		/*if (type == 0) {
			// 热门观点
			List<HotIdea> ideas;
			if (isIndex == 0) {
				// 首页
				ideas = HotIdeaServ.findLastIdeaByTeacherId(tId, 3);
			} else {
				ideas = HotIdeaServ.findIdeaByTeacherId(tId, p);
			}

			if (ideas != null) {
				for (HotIdea idea : ideas) {
					// 1 收费 0 免费
					int isPay = idea.getIsFree();
					if (isPay == 1) {
						IdeaSale sale = IdeaSaleServer
								.findTxtLiveSaleByUiDAndLiveId(usId,
										idea.getId());
						if (sale != null) {
							isPurch = 0;
						} else {
							isPurch = 1;
						}
					} else {
						isPurch = 1;
					}
					idea.setIsPurch(isPurch);
				}
			}
			String ideaList = JsonUtil.getIdeaList(ideas);
			if(isIndex==0){
				new CacheUtils(Constant.VIDEO_CACHE).addToCache("indexIdea",ideaList);
			}else{
				new CacheUtils(Constant.VIDEO_CACHE).addToCache(tId+"allIdea"+usId,ideaList);
			}
			return ideaList;
		} else */
		
		/*if (type == 1) {
			// 问答
			List<Ask> asks;
			if (isIndex == 0) {
				// 首页
				asks = AskSer.findLastByTeacherId(tId, 3);
			} else {
				asks = AskSer.findAskByTeacherId(tId, p);
			}
			List<User> users = new ArrayList<User>();
			for (Ask ask : asks) {
				int uId = ask.getUserId();
				User user = UserServer.findUserById(uId);
				int isReply = ask.getIsReply();
				List<Answer> answer = AnswerSer.findAnswerByAskId(ask.getId());
				if (answer != null && isReply == 2 && answer.size() > 0) {
					ask.setReplyBody(answer.get(0).getAnswerBody());
				}
				if (user == null) {
					user = new User();
				}
				int isPay = ask.getIsPay();
				if (isPay == 1) {
					AnswerSale sale = AnswerSaleSer.findSaleByUserIdAndAskId(
							usId, ask.getId());
					if (sale != null || usId == uId) {
						isPurch = 0;
					} else {
						isPurch = 1;
					}
				} else {
					if (usId == uId) {
						isPurch = 0;
					} else {
						isPurch = 1;
					}
				}
				ask.setIsPurch(isPurch);
				users.add(user);
			}
			return JsonUtil.getAskList(asks, users, 0);
		} else*/
		
		/*if (type == 2) {
			// 文字直播
			if (usId <= 0) {
				isPurch = 1;
			}
			Guardian guardian = GuardianSer.findIsGuardian(tId, usId);
			int ownJucaiBills = 0;
			TextLive txt = null;
			List<TextLive> txts = TxtLiveSer.findTxtLiveByTeacherIdAndLast(tId,
					1);
			List<TextLive> allTxts = TxtLiveSer.findTextLiveByTeacherId(tId, p);
			if (txts != null && txts.size() > 0) {
				txt = txts.get(0);
				txt.setCharge(teacher.getTxtLiveFree() == 1);
				txt.setTxtPrice(teacher.getTxtLivePrice());
				if (usId > 0 && txt.isCharge()) {
					Account account = AccountSer.findAccountByUserId(usId);
					List<TxtLiveSale> sales = TxtLiveSaleSer
							.findSaleByUserIdAndTiD(usId, tId);
					if (sales != null && sales.size() > 0) {
						for (TxtLiveSale sale : sales) {
							if (TimeUtils.isLive(sale.getStartDate(),
									sale.getEndDate())) {
								txt.setIsPurch(0);
								break;
							} else {
								txt.setIsPurch(1);
							}
						}
					} else {
						txt.setIsPurch(1);
					}

					if (guardian != null) {
						txt.setIsPurch(0);
					}

					if (account != null) {
						ownJucaiBills = account.getJucaiBills();
					}

				} else {
					txt.setIsPurch(1);
				}

				txt.setOwnJucaiBills(ownJucaiBills);
			}

			if (isIndex == 0) {
				// 首页
				return JsonUtil.getIndexTxtArray(txts, guardian);
			}

			if (allTxts != null) {
				for (TextLive tx : allTxts) {
					tx.setCharge(teacher.getTxtLiveFree() == 1);
					tx.setTxtPrice(teacher.getTxtLivePrice());
					if (usId > 0 && txt.isCharge()) {
						List<TxtLiveSale> sales = TxtLiveSaleSer
								.findSaleByUserIdAndTiD(usId, tId);
						if (sales != null && sales.size() > 0) {
							for (TxtLiveSale sale : sales) {
								if (TimeUtils.isLive(sale.getStartDate(),
										sale.getEndDate())) {
									tx.setIsPurch(0);
									continue;
								} else {
									tx.setIsPurch(1);
								}
							}
						} else {
							tx.setIsPurch(1);
						}
					} else {
						tx.setIsPurch(1);
					}
					if (guardian != null) {
						txt.setIsPurch(0);
					}
				}
			}
			return JsonUtil.getTxtLiveByTeacherId(txt, allTxts, guardian);
		} */
		
		
		if(type!=0&&type!=1&&type!=2) {
			// 视频直播
			int ownJucaiBills = 0;
			VideoLive live = VideoLiveServer.findLiveBytId(tId);
			if (live != null) {
				// 讲师是否开通直播 0 否 1 是
				live.setLiveVideo(teacher.getIsUserLiveUrl() == 1);
				// 直播是否收费 0 否 1 是
				live.setCharge(teacher.getLiveFree() == 1);
				// 单次直播的价格
				live.setLivePrice(teacher.getLivePrice());
				int roomId = FamousTeacherSer.findRoomInfo(tId);
				live.setXnRenQi(getRoomInfo(roomId));
				// 视频直播url
				live.setRtmpUrl(teacher.getRtmpUrl());
				live.setVideoUrl(teacher.getAppLiveUrl());
				live.setTeacherName(teacher.getNickName());
				live.setTeacherFace(teacher.getHeadFace());
				live.setRoomId(teacher.getFk_UserId());
			//	if (live.getIsEnd()==2||live.isCharge()) {
					// 收费
					if (usId > 0) {
						Account account = AccountSer.findAccountByUserId(usId);
						live.setGradian(LiveUtil.isGradian(tId, usId));
						LiveRecoder recoder = LiveRecoderSer
						.getRecoderByLiveId(live.getId());
						if(recoder!=null){
							live.setTitle(recoder.getTitle());
							LiveRecoderSale sale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											recoder.getId(),1);
							int isTest=UserServer.findIsTest(usId);
							if (sale != null||isTest==1) {
								live.setIsPurch(0);
							} else {
								live.setIsPurch(1);
							}
						}else{
							live.setIsPurch(1);
						}
						if (account != null) {
							ownJucaiBills = account.getJucaiBills();
						}

					} else {
						live.setIsPurch(1);
					}
			//	}
				live.setOwnJucaiBills(ownJucaiBills);
			}
			if (isIndex == 0) {
				// 首页
				String indexVideoLive = JsonUtil.getIndexVideoLive(live);
				return indexVideoLive;
			}
			List<RecoderVideo>  videos=RecoderVideoServer.getAllRecoderVideo(tId,p,10.0);
			Video video2 = VideoServer.findLastVideoByTeacher(tId);
			if (videos != null) {
				for (RecoderVideo video : videos) {
					// 是否为付费视频 0为免费视频，1为付费视频
					int recoderId = Integer.parseInt(video.getExt1());
					if(check){
						video.setLiveUrl1(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : Constant.urls[RandomUtils.getRandomData(9, 0)]);
					}
					if (video.getLiveIsFree()==2&&recoderId!=0) {
						    //录播是否购买
							LiveRecoderSale recoderSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											video.getId(),2);
							//录播在直播的时候是否被购买
							LiveRecoderSale liveSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											recoderId,1);
							int isTest=UserServer.findIsTest(usId);
							if (recoderSale != null||liveSale!=null||isTest==1) {
								video.setIsPurch(0);
							} else {
								video.setIsPurch(1);
							}
					}
				}
			}
			String allLive = JsonUtil.getLive(live, videos,p);
			return allLive;
		}
		return null;
	}
	
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
