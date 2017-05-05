package com.jucaipen.main.index.famous;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Account;
import com.jucaipen.model.Answer;
import com.jucaipen.model.AnswerSale;
import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.IdeaSale;
import com.jucaipen.model.LiveRecoder;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.User;
import com.jucaipen.model.Video;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.AnswerSaleSer;
import com.jucaipen.service.AnswerSer;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.IdeaSaleServer;
import com.jucaipen.service.LiveRecoderSaleSer;
import com.jucaipen.service.LiveRecoderSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.TxtLiveSaleSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LiveUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author
 * 
 *   Administrator type (0 �۵�) (1 �ʴ�) (2 ����ֱ��) (3 ��Ƶֱ��)
 */
@SuppressWarnings("serial")
public class QuerryTeacherIdea extends HttpServlet {
	private String result;
	private boolean check=false;
	private String urls[]={"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160817130010-20160817140207.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160818130010-20160818140219.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160819150500-20160819154838.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160822150934-20160822160025.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160823085947-20160823091548.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160825102745-20160825113430.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160830102530-20160830113240.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160901092130-20160901102101.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160905135930-20160905150400.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160906191940-20160906202001.m3u8"
			};
	private String ua;
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
		ua = request.getHeader("User-Agent");
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
								result = JsonUtil.getRetMsg(6, "userId �����쳣");
							}
						} else {
							result = JsonUtil.getRetMsg(5, "isIndex �����쳣");
						}
					} else {
						result = JsonUtil.getRetMsg(4, "page �����쳣");
					}
				} else {
					result = JsonUtil.getRetMsg(3, "typeId �����쳣");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "teacherId �������ָ�ʽ���쳣");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "teacherId ��������Ϊ��");
		}
		out.print(result);
		out.flush();
		out.close();
	}

	private String initTeacherIdeaData(int tId, int type, int p, int isIndex,
			int usId) {
		// ��ʼ����ʦ���Ź۵� �ʴ� ����ֱ�� ֱ����Ϣ
		FamousTeacher teacher = FamousTeacherSer.findFamousTeacherById(tId);
		int isPurch = 1;
		if (usId <= 0) {
			isPurch = 1;
		}
		if (type == 0) {
			// ���Ź۵�
			List<HotIdea> ideas;
			if (isIndex == 0) {
				// ��ҳ
				Object cached = DataManager.getCached(Constant.VIDEO_CACHE,"indexIdea");
				if(cached!=null){
					return cached.toString();
				}
				ideas = HotIdeaServ.findLastIdeaByTeacherId(tId, 3);
			} else {
				Object cached = DataManager.getCached(Constant.VIDEO_CACHE,tId+"allIdea"+usId);
				if(cached!=null){
					return cached.toString();
				}
				ideas = HotIdeaServ.findIdeaByTeacherId(tId, p);
			}

			if (ideas != null) {
				for (HotIdea idea : ideas) {
					// 1 �շ� 0 ���
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
				new CacheUtils(Constant.VIDEO_CACHE).addToCache("indexIdea",ideaList );
			}else{
				new CacheUtils(Constant.VIDEO_CACHE).addToCache(tId+"allIdea"+usId,ideaList );
			}
			return ideaList;
		} else if (type == 1) {
			// �ʴ�
			List<Ask> asks;
			if (isIndex == 0) {
				// ��ҳ
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
		} else if (type == 2) {
			// ����ֱ��
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
				// ��ҳ
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
		} else {
			// ��Ƶֱ��
			if(isIndex==0){
				Object cached = DataManager.getCached(Constant.VIDEO_CACHE,"indexLive");
				if(cached!=null){
					return cached.toString();
				}
			}else{
				Object cached = DataManager.getCached(Constant.VIDEO_CACHE,tId+"allLive"+p);
				if(cached!=null){
					return cached.toString();
				}
			}
			
			int ownJucaiBills = 0;
			VideoLive live = VideoLiveServer.findLiveBytId(tId);
			if (live != null) {
				// ��ʦ�Ƿ�ֱͨ�� 0 �� 1 ��
				live.setLiveVideo(teacher.getIsUserLiveUrl() == 1);
				// ֱ���Ƿ��շ� 0 �� 1 ��
				live.setCharge(teacher.getLiveFree() == 1);
				// ����ֱ���ļ۸�
				live.setLivePrice(teacher.getLivePrice());
				// ��Ƶֱ��url
				live.setVideoUrl(teacher.getAppLiveUrl());
				live.setTeacherName(teacher.getNickName());
				live.setTeacherFace(teacher.getHeadFace());
				live.setRoomId(teacher.getFk_UserId());
			//	if (live.getIsEnd()==2||live.isCharge()) {
					// �շ�
					if (usId > 0) {
						Account account = AccountSer.findAccountByUserId(usId);
						live.setGradian(LiveUtil.isGradian(tId, usId));
						LiveRecoder recoder = LiveRecoderSer
						.getRecoderByLiveId(live.getId());
						if(recoder!=null){
							LiveRecoderSale sale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											recoder.getId(),1);
							if (sale != null) {
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
				// ��ҳ
				String indexVideoLive = JsonUtil.getIndexVideoLive(live);
				new CacheUtils(Constant.VIDEO_CACHE).addToCache("indexLive", indexVideoLive);
				return indexVideoLive;
			}
			List<RecoderVideo>  videos=RecoderVideoServer.getAllRecoderVideo(tId,p);
			Video video2 = VideoServer.findLastVideoByTeacher(tId);
			if (videos != null) {
				for (RecoderVideo video : videos) {
					// �Ƿ�Ϊ������Ƶ 0Ϊ�����Ƶ��1Ϊ������Ƶ
					int recoderId = Integer.parseInt(video.getExt1());
					if(check){
						video.setLiveUrl1(video2!=null&&video2.getVideoUrl().length()>0 ? video2.getVideoUrl() : urls[RandomUtils.getRandomData(9, 0)]);
					}
					if (video.getLiveIsFree()==2&&recoderId!=0) {
						    //¼���Ƿ���
							LiveRecoderSale recoderSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											video.getId(),2);
							//¼����ֱ����ʱ���Ƿ񱻹���
							LiveRecoderSale liveSale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											recoderId,1);
							if (recoderSale != null||liveSale!=null) {
								video.setIsPurch(0);
							} else {
								video.setIsPurch(1);
							}
					}
				}
			}
			String allLive = JsonUtil.getLive(live, videos,p);
			new CacheUtils(Constant.VIDEO_CACHE).addToCache(tId+"allLive"+p, allLive);
			return allLive;
		}
	}

}
