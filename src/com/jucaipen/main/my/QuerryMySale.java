package com.jucaipen.main.my;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.AnswerSale;
import com.jucaipen.model.Ask;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.HotIdea;
import com.jucaipen.model.IdeaSale;
import com.jucaipen.model.LiveDetailSale;
import com.jucaipen.model.LiveRecoderSale;
import com.jucaipen.model.MySpecial;
import com.jucaipen.model.MyVideo;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.Special;
import com.jucaipen.model.Tactics;
import com.jucaipen.model.TacticsSale;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveDetails;
import com.jucaipen.model.TxtLiveSale;
import com.jucaipen.model.User;
import com.jucaipen.model.Video;
import com.jucaipen.service.AnswerSaleSer;
import com.jucaipen.service.AskSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.HotIdeaServ;
import com.jucaipen.service.IdeaSaleServer;
import com.jucaipen.service.LiveDetailSaleSer;
import com.jucaipen.service.LiveRecoderSaleSer;
import com.jucaipen.service.MySpecialSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.SpecialSer;
import com.jucaipen.service.TacticsSaleSer;
import com.jucaipen.service.TacticsSer;
import com.jucaipen.service.TxtLiveDetaileSer;
import com.jucaipen.service.TxtLiveSaleSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;

/**
 * @author Administrator 获取已购买的信息
 * 
 *         typeId 0 视频 1 专辑 2 日志观点 3 文字直播 （整个文字直播） 4 问答 5 战法 6 直播观点 （单个文字直播）
 */
public class QuerryMySale extends HttpServlet {
	private static final long serialVersionUID = -4719886263162666822L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String typeId = request.getParameter("typeId");
		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		if (StringUtil.isNotNull(userId)) {
			if (StringUtil.isInteger(userId)) {
				int uId = Integer.parseInt(userId);
				if (uId > 0) {
					if (StringUtil.isNotNull(typeId)
							&& StringUtil.isInteger(typeId)) {
						int type = Integer.parseInt(typeId);
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
							result = initMySaleInfo(uId, type, p);
						} else {
							result = JsonUtil.getRetMsg(1, "page 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(1, "typeId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(1, "用户还没登录");
				}

			} else {
				result = JsonUtil.getRetMsg(1, "userId 数字格式异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "userId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initMySaleInfo(int uId, int type, int p) {
		if (type == 0) {
			// 0 视频    ---是否过期
			List<RecoderVideo>  recoders=new ArrayList<RecoderVideo>();
			List<LiveRecoderSale> sales=LiveRecoderSaleSer.getMyVideo(uId, p, 2);
			for (LiveRecoderSale sale : sales) {
				int vId = sale.getLiveRecoderId();
				RecoderVideo vidoe=RecoderVideoServer.getRecoderVideioById(vId);
			}
			return JsonUtil.getMyVideoList(recoders);
		} else if (type == 1) {
			// 1 专辑   ---是否过期
			List<MySpecial> mySpecials = MySpecialSer.findSpecialByUid(uId, p);
			for (MySpecial mySpecial : mySpecials) {
				int sId = mySpecial.getSpecialId();
				Special special = SpecialSer.findSpecialById(sId);
				if (special == null) {
					special = new Special();
				}
				if(TimeUtils.isLive(mySpecial.getStartDate(), mySpecial.getEndDate())){
					mySpecial.setIsPurch(0);
				}else{
					mySpecial.setIsPurch(2);
				}
				List<Video> video = VideoServer.findSpecialByLast(1, sId);
				if (video != null && video.size() > 0) {
					FamousTeacher teacher = FamousTeacherSer
							.findFamousTeacherById(video.get(0).getTeacherId());
					if (teacher != null) {
						mySpecial.setTeacherName(teacher.getNickName());
					}
				}
				mySpecial.setSpecialDesc(special.getDescription());
				mySpecial.setSpecialTitle(special.getName());
			}
			return JsonUtil.getMySpecialList(mySpecials);
		} else if (type == 2) {
			// 2 日志观点
			List<IdeaSale> ideaSales = IdeaSaleServer.findTxtLiveSaleByUid(uId,
					p);
			for (IdeaSale ideaSale : ideaSales) {
				int logId = ideaSale.getLogId();
				int teacherId = ideaSale.getTeacherId();
				HotIdea idea = HotIdeaServ.findIdeaById(logId);
				FamousTeacher teacher = FamousTeacherSer
						.findFamousTeacherById(teacherId);
				ideaSale.setTeacherFace(teacher.getHeadFace());
				ideaSale.setTeacherNickName(teacher.getNickName());
				ideaSale.setTeacherIsV(teacher.getIsV());
				ideaSale.setTeacherLeavel(teacher.getLevel());
				ideaSale.setLogTitle(idea.getTitle());
				ideaSale.setLogBody(idea.getBodys());
			}
			return JsonUtil.getMyIdeaList(ideaSales);
		} else if (type == 3) {
			// 3 文字直播 整个
			List<TxtLiveSale> txtSales = TxtLiveSaleSer
					.findSaleByUserId(uId, p);
			if (txtSales != null) {
				for (TxtLiveSale sale : txtSales) {
					int txtId = sale.getFk_txtId();
					int tId = sale.getTeacherId();
					TextLive txt = TxtLiveSer.findTextLiveById(txtId);
					FamousTeacher teacher = FamousTeacherSer
							.findFamousTeacherById(tId);
					Guardian guardian=GuardianSer.findIsGuardian(tId, uId);
					sale.setGurdian(guardian!=null);
					sale.setTeacherName(teacher.getNickName());
					sale.setFk_title(txt.getTitle());
					sale.setFk_txtHits(txt.getXnHits());
					sale.setFk_txtIsEnd(txt.getIsEnd());
				}
			}
			return JsonUtil.getMyTxtList(txtSales);

		} else if (type == 4) {
			// 4 问答
			List<AnswerSale> answerSales = AnswerSaleSer.findSaleByUid(uId, p);
			for (AnswerSale answerSale : answerSales) {
				Ask ask = AskSer.findAskById(answerSale.getAskId());
				User user=UserServer.findBaseInfoById(ask.getUserId());
				answerSale.setAskBody(ask.getAskBody());
				answerSale.setAskUserName(user.getNickName());
				answerSale.setUserFace(user.getFaceImage());
				answerSale.setAskDate(ask.getAskDate());
				answerSale.setReplyCount(ask.getReplyCount());
			}
			return JsonUtil.getMyQusestionList(answerSales);
		} else if (type == 5) {
			// 5 战法   ----是否过期
			List<TacticsSale> tacticsSales = TacticsSaleSer.findSaleByUserId(
					uId, p);
			if (tacticsSales != null) {
				for (TacticsSale tacticsSale : tacticsSales) {
					int tacticeId = tacticsSale.getTacticsId();
					Tactics tactics = TacticsSer.findTacticsById(tacticeId);
					int tId = tactics.getTeacherId();
					FamousTeacher teacher = FamousTeacherSer
							.findFamousTeacherById(tId);
					if(TimeUtils.isLive(tacticsSale.getStartDate(), tacticsSale.getEndDate())){
						tacticsSale.setIsOrder(0);
					}else{
						tacticsSale.setIsOrder(2);
					}
					tacticsSale.setTeacherImage(teacher.getHeadFace());
					tacticsSale.setTacticeImage(tactics.getImageUrl());
					tacticsSale.setTacticsTitle(tactics.getTitle());
					tacticsSale.setTeacherName(teacher.getNickName());
				}
			}
			return JsonUtil.getMyTacticsList(tacticsSales);
		} else {
			// 6 直播详情 单条
			List<LiveDetailSale> txtSales = LiveDetailSaleSer.findSaleByUid(
					uId, p);
			if (txtSales != null) {
				for (LiveDetailSale detailSale : txtSales) {
					int detailId = detailSale.getLiveDetailId();
					int tId = detailSale.getTeacherId();
					TxtLiveDetails details = TxtLiveDetaileSer
							.findTextDetaileById(detailId);
					int liveId=details.getFk_liveId();
					TextLive txtLive=TxtLiveSer.findTextLiveById(liveId);
					FamousTeacher teacher = FamousTeacherSer
							.findFamousTeacherById(tId);
					detailSale.setFk_txtTitle(txtLive.getTitle());
					detailSale.setDetailBody(details.getBodys());
					detailSale.setOwnTeacher(teacher.getNickName());
				}
			}
			return JsonUtil.getMyTxtDetailList(txtSales);
		}
	}

}
