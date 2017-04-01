package com.jucaipen.main.index.famous;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Account;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Guardian;
import com.jucaipen.model.LiveDetailSale;
import com.jucaipen.model.SiteConfig;
import com.jucaipen.model.TextLive;
import com.jucaipen.model.TxtLiveDetails;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.GuardianSer;
import com.jucaipen.service.LiveDetailSaleSer;
import com.jucaipen.service.SiteConfigSer;
import com.jucaipen.service.TxtLiveDetaileSer;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         获取文字直播详细信息
 */
@SuppressWarnings("serial")
public class TxtDetails extends HttpServlet {
	private String result;
	private Account account ;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String txtId = request.getParameter("txtId");
		String userId=request.getParameter("userId");
		if (StringUtil.isNotNull(txtId)) {
			if (StringUtil.isInteger(txtId)) {
				int tId = Integer.parseInt(txtId);
				if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
					int uId=Integer.parseInt(userId);
					result = initTxtDetails(tId,uId);
				}else{
					result = JsonUtil.getRetMsg(3, "userId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(2, "txtId 参数数字格式化异常");
			}
		} else {
			result = JsonUtil.getRetMsg(1, "txtId 参数不能为空");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initTxtDetails(int tId,int uId) {
		// 初始化文字直播详细信息
		int isPurch=1;
		int ownJucaiBills=0;
		if(uId<=0){
			return JsonUtil.getRetMsg(1,"用户还没登录");
		}
		TextLive live = TxtLiveSer.findTextLiveById(tId);
		if(live==null){
			return JsonUtil.getRetMsg(6,"直播信息不存在");
		}
		int teacherId=live.getTeacherId();
		FamousTeacher teacher=FamousTeacherSer.findFamousTeacherById(teacherId);
		List<TxtLiveDetails> txtDetails = TxtLiveDetaileSer
				.findTextDetaileByLiveId(tId,0);		 account =AccountSer.findAccountByUserId(uId);
		Guardian guardian=GuardianSer.findIsGuardian(teacherId, uId);
		if(txtDetails!=null){
			for(TxtLiveDetails detail : txtDetails){
				int isTxtFree=teacher.getTxtLiveFree();
				if(isTxtFree==0){
					LiveDetailSale sale = LiveDetailSaleSer.findSaleByUserIdAndTxtIdAndDetailId(uId, detail.getId());
				    if(sale!=null){
				    	isPurch=0;
				    }else{
				    	isPurch=1;
				    }
				    if(account!=null){
				    	ownJucaiBills=account.getJucaiBills();
				    }
				}
				detail.setIsPurch(isPurch);
				if(isTxtFree==1){
					detail.setIsFree(0);
				}
				detail.setOwnJucaiBills(ownJucaiBills);
			}
		}
		initTxtHits(tId,live.getHits(),live.getXnHits());
		return JsonUtil.getTxtDetails(txtDetails,guardian);
	}

	
	private void initTxtHits(int tId, int hits,int xnHits) {
		SiteConfig config = SiteConfigSer.findSiteConfig();
		TxtLiveSer.addHits(hits+1, tId,xnHits+config.getNewsMom());
		
	}

}
