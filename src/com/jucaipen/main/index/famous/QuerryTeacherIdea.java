package com.jucaipen.main.index.famous;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.User;
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
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.MyVideoSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.TxtLiveSaleSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LiveUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author
 * 
 *   Administrator type (0 观点) (1 问答) (2 文字直播) (3 视频直播)
 */
@SuppressWarnings("serial")
public class QuerryTeacherIdea extends HttpServlet {
	private String result;
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
		String userId = request.getParameter("userId");
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
		int isPurch = 1;
		if (usId <= 0) {
			isPurch = 1;
		}
		if (type == 0) {
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
			return JsonUtil.getIdeaList(ideas);
		} else if (type == 1) {
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
		} else if (type == 2) {
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
		} else {
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
				// 视频直播url
				live.setVideoUrl(teacher.getAppLiveUrl());
				live.setTeacherName(teacher.getNickName());
				live.setTeacherFace(teacher.getHeadFace());
				live.setRoomId(teacher.getFk_UserId());
				if (live.isCharge()) {
					// 收费
					if (usId > 0) {
						Account account = AccountSer.findAccountByUserId(usId);
						live.setGradian(LiveUtil.isGradian(tId, usId));
						LiveRecoder recoder = LiveRecoderSer
						.getRecoderByLiveId(live.getId());
						if(recoder!=null){
							LiveRecoderSale sale = LiveRecoderSaleSer
									.getLiveSaleByUidAndLiveId(usId, 
											recoder.getId());
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
				}
				live.setOwnJucaiBills(ownJucaiBills);
			}
			if (isIndex == 0) {
				// 首页
				return JsonUtil.getIndexVideoLive(live);
			}
			
			List<RecoderVideo>  videos=RecoderVideoServer.getAllRecoderVideo(tId,p);
			if (videos != null) {
				for (RecoderVideo video : videos) {
					// 是否为付费视频 0为免费视频，1为付费视频
					if (video.getLiveIsFree()==2) {
							MyVideo mVideo = MyVideoSer.findIsMyVideo(usId,
									video.getId());
							if (mVideo != null) {
								if (TimeUtils.isLive(mVideo.getStartDate(),
										mVideo.getEndDate())) {
									video.setIsPurch(0);
								} else {
									video.setIsPurch(1);
								}
							} else {
								video.setIsPurch(1);
							}
					}
				}
			}
			return JsonUtil.getLive(live, videos,p);
		}
	}

}
