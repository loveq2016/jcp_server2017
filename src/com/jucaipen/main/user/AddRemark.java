package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.Account;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Comment;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.JcpNews;
import com.jucaipen.model.RebateIntegeralDetail;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.UserComm;
import com.jucaipen.model.Video;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.CommentSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.JcpNewsSer;
import com.jucaipen.service.RebateIntegeralDetailSer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserCommSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.BaseData;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 * 
 *         添加评论----回复评论 typeId -----分类id （0 资讯评论，回复 ） 
 *                                            （ 1 视频评论，回复 ） 
 *                                            （ 2 观点评论 回复）
 *                                            （ 3 文字直播）
 *                      ParentId 0 评论 非0 回复
 */
public class AddRemark extends HttpServlet {
	private static final long serialVersionUID = 6953704277453032529L;
	private String result;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String typeId = request.getParameter("typeId");
			String userId = request.getParameter("userId");
			String parentId = request.getParameter("parentId");
			String newsId = request.getParameter("newsId");
			String bodys = request.getParameter("bodys");
			if (StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isInteger(parentId)) {
						int pId = Integer.parseInt(parentId);
						if (StringUtil.isInteger(newsId)) {
							int nId = Integer.parseInt(newsId);
							if (StringUtil.isNotNull(bodys)) {
								if (StringUtil.isInteger(typeId)) {
									int type = Integer.parseInt(typeId);
									result = insertRemark(uId, pId, nId, bodys,
											type);
								} else {
									result = JsonUtil.getRetMsg(5,
											"typeId参数数字格式化异常");
								}
							} else {
								result = JsonUtil.getRetMsg(6, "bodys 参数不能为空");
							}
						} else {
							result = JsonUtil.getRetMsg(3, "新闻id参数数字格式化异常");
						}
					} else {
						result = JsonUtil.getRetMsg(2, "parentId 参数数字格式化异常");
					}
				} else {
					result = JsonUtil.getRetMsg(7, "当前用户还没登录");
				}
			} else {
				result = JsonUtil.getRetMsg(6, "用户id参数数字格式化异常");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * @param uId
	 * @param cId
	 * @param nId
	 * @param bodys
	 * 
	 *            添加评论
	 * @return
	 */
	private String insertRemark(int uId, int pId, int nId, String bodys,
			int commType) {
		if (commType == 0) {
			// 资讯
			UserComm uc = new UserComm();
			uc.setBodys(bodys);
			uc.setType(0);
			uc.setGoods(0);
			uc.setInsertDate(TimeUtils
					.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			uc.setIsShow(0);
			uc.setNovId(nId);
			uc.setParentId(pId);
			uc.setReplyCount(0);
			uc.setUserId(uId);
			int isSuccess = UserCommSer.insertComm(uc);
			if (isSuccess == 1) {
				if (pId <= 0) {
					// 评论三次之后不会产生积分
					JcpNews news = JcpNewsSer.findNews(nId);
					List<UserComm> comms = UserCommSer
							.findComment(uId, 0, 1, 0);
					if (comms.size() <= 3) {
						SiteConfig config = SiteConfigSer.findSiteConfig();
						int commIntegeral = config.getCommIntegeral();
						Account a = AccountSer.findAccountByUserId(uId);
						if (a == null) {
							Account account = new Account();
							account.setUserId(uId);
							account.setIntegeral(commIntegeral);
							account.setJucaiBills(0);
							AccountSer.addAccount(account);
							// 更新用户积分信息
							UserServer.updateIntegeral(uId, commIntegeral);
							int leavel = BaseData.getLeavel(commIntegeral);
							UserServer.updateUserLeavel(uId, leavel);
						} else {
							// 更新总账户积分信息
							AccountSer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							// 更新用户积分信息
							UserServer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							int integeral = commIntegeral + a.getIntegeral();
							int leavel = BaseData.getLeavel(integeral);
							UserServer.updateUserLeavel(uId, leavel);
						}
						// 更新返现表
						RebateIntegeralDetail inDetail = new RebateIntegeralDetail();
						inDetail.setUserId(uId);
						inDetail.setIntegralNum(commIntegeral);
						inDetail.setInsertDate(TimeUtils.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						inDetail.setRemark("评论证券知识【" + news.getTitle() + "】");
						inDetail.setType(3);
						inDetail.setFromId(nId);
						RebateIntegeralDetailSer.addRebateIntegeral(inDetail);
					}
				}
				return JsonUtil.getRetMsg(0, "评论提交成功");
			} else {
				return JsonUtil.getRetMsg(1, "评论提交失败");
			}
		} else if (commType == 1) {
			// 视频
			UserComm uc = new UserComm();
			uc.setBodys(bodys);
			uc.setType(1);
			uc.setGoods(0);
			uc.setInsertDate(TimeUtils
					.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			uc.setIsShow(0);
			uc.setNovId(nId);
			uc.setParentId(pId);
			uc.setReplyCount(0);
			uc.setUserId(uId);
			int isSuccess = UserCommSer.insertComm(uc);
			if (isSuccess == 1) {
				// 评论三次之后不会产生积分
				if (pId <= 0) {
					Video video = VideoServer.findVideoById(nId);
					List<UserComm> comms = UserCommSer
							.findComment(uId, 1, 1, 0);
					if (comms.size() <= 3) {
						SiteConfig config = SiteConfigSer.findSiteConfig();
						int commIntegeral = config.getCommIntegeral();
						Account a = AccountSer.findAccountByUserId(uId);
						// 更新总账户积分信息
						if (a != null) {
							AccountSer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							// 更新用户积分信息
							UserServer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							int integeral = commIntegeral + a.getIntegeral();
							int leavel = BaseData.getLeavel(integeral);
							UserServer.updateUserLeavel(uId, leavel);
						} else {
							Account account = new Account();
							account.setUserId(uId);
							account.setIntegeral(commIntegeral);
							account.setJucaiBills(0);
							AccountSer.addAccount(account);
							UserServer.updateIntegeral(uId, commIntegeral);
							int leavel = BaseData.getLeavel(commIntegeral);
							UserServer.updateUserLeavel(uId, leavel);
						}
						// 更新返现表
						RebateIntegeralDetail inDetail = new RebateIntegeralDetail();
						inDetail.setUserId(uId);
						inDetail.setIntegralNum(commIntegeral);
						inDetail.setInsertDate(TimeUtils.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						inDetail.setRemark("评论视频【" + video.getTitle() + "】");
						inDetail.setType(3);
						inDetail.setFromId(nId);
						RebateIntegeralDetailSer.addRebateIntegeral(inDetail);
					}

				}
				return JsonUtil.getRetMsg(0, "评论提交成功");
			} else {
				return JsonUtil.getRetMsg(1, "评论提交失败");
			}
		} else if (commType == 2) {
			// 观点
			Comment comm = new Comment();
			comm.setUserId(uId);
			comm.setBodys(bodys);
			comm.setGoods(0);
			comm.setCommType(1);
			comm.setInsertDate(TimeUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			comm.setIsShow(1);
			comm.setLogOrLiveId(nId);
			comm.setParentId(pId);
			comm.setRepCount(0);
			int isSuccess = CommentSer.insertComm(comm);
			if (isSuccess == 1) {
				if (pId <= 0) {
					// 评论三次之后不会产生积分
					HotIdea idea = HotIdeaServ.findIdeaById(nId);
					List<Comment> comms = CommentSer.findComment(uId, 1, 1, 0);
					if (comms.size() <= 3) {
						SiteConfig config = SiteConfigSer.findSiteConfig();
						int commIntegeral = config.getCommIntegeral();
						Account a = AccountSer.findAccountByUserId(uId);
						// 更新总账户积分信息
						if (a != null) {
							AccountSer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							// 更新用户积分信息
							UserServer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							int integeral = commIntegeral + a.getIntegeral();
							int leavel = BaseData.getLeavel(integeral);
							UserServer.updateUserLeavel(uId, leavel);
						} else {
							Account account = new Account();
							account.setUserId(uId);
							account.setIntegeral(commIntegeral);
							account.setJucaiBills(0);
							AccountSer.addAccount(account);
							UserServer.updateIntegeral(uId, commIntegeral);
							int leavel = BaseData.getLeavel(commIntegeral);
							UserServer.updateUserLeavel(uId, leavel);
						}
						// 更新返现表
						RebateIntegeralDetail inDetail = new RebateIntegeralDetail();
						inDetail.setUserId(uId);
						inDetail.setIntegralNum(commIntegeral);
						inDetail.setInsertDate(TimeUtils.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						inDetail.setRemark("评论观点【" + idea.getTitle() + "】");
						inDetail.setType(3);
						inDetail.setFromId(nId);
						RebateIntegeralDetailSer.addRebateIntegeral(inDetail);
					}
				}
				return JsonUtil.getRetMsg(0, "评论提交成功");
			} else {
				return JsonUtil.getRetMsg(1, "评论提交失败");
			}
		} else {
			// 文字直播
			Comment comm = new Comment();
			comm.setUserId(uId);
			comm.setBodys(bodys);
			comm.setGoods(0);
			comm.setCommType(2);
			comm.setInsertDate(TimeUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			comm.setIsShow(1);
			comm.setLogOrLiveId(nId);
			comm.setParentId(pId);
			comm.setRepCount(2);
			int isSuccess = CommentSer.insertComm(comm);
			if (isSuccess == 1) {
				if (pId <= 0) {
					// 评论三次之后不会产生积分
					TextLive txt = TxtLiveSer.findTextLiveById(nId);
					List<Comment> comms = CommentSer.findComment(uId, 2, 1, 0);
					if (comms.size() <= 3) {
						SiteConfig config = SiteConfigSer.findSiteConfig();
						int commIntegeral = config.getCommIntegeral();
						Account a = AccountSer.findAccountByUserId(uId);
						// 更新总账户积分信息
						if (a != null) {
							AccountSer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							// 更新用户积分信息
							UserServer.updateIntegeral(uId,
									commIntegeral + a.getIntegeral());
							int integeral = commIntegeral + a.getIntegeral();
							int leavel = BaseData.getLeavel(integeral);
							UserServer.updateUserLeavel(uId, leavel);
						} else {
							Account account = new Account();
							account.setUserId(uId);
							account.setIntegeral(commIntegeral);
							account.setJucaiBills(0);
							AccountSer.addAccount(account);
							UserServer.updateIntegeral(uId, commIntegeral);
							int leavel = BaseData.getLeavel(commIntegeral);
							UserServer.updateUserLeavel(uId, leavel);
						}

						// 更新返现表
						RebateIntegeralDetail inDetail = new RebateIntegeralDetail();
						inDetail.setUserId(uId);
						inDetail.setIntegralNum(commIntegeral);
						inDetail.setInsertDate(TimeUtils.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						inDetail.setRemark("评论文字直播【" + txt.getTitle() + "】");
						inDetail.setType(3);
						inDetail.setFromId(nId);
						RebateIntegeralDetailSer.addRebateIntegeral(inDetail);
					}
				}
				return JsonUtil.getRetMsg(0, "评论提交成功");
			} else {
				return JsonUtil.getRetMsg(1, "评论提交失败");
			}
		}

	}

}
