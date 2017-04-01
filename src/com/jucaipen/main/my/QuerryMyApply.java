package com.jucaipen.main.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ApplyTeacher;
import com.jucaipen.model.Bank;
import com.jucaipen.model.Certification;
import com.jucaipen.model.City;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Position;
import com.jucaipen.model.Province;
import com.jucaipen.model.User;
import com.jucaipen.service.ApplyTeacherSer;
import com.jucaipen.service.BankSer;
import com.jucaipen.service.CertificationSer;
import com.jucaipen.service.CityServer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.PositionSer;
import com.jucaipen.service.ProvinceServer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         获取我的申请信息
 */
public class QuerryMyApply extends HttpServlet {
	private static final long serialVersionUID = -6869431560296180425L;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String stepType = request.getParameter("stepType");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(stepType)
						&& StringUtil.isInteger(stepType)) {
					int step = Integer.parseInt(stepType);
					result = getMyApplyInfo(step, uId);
				}else{
					result=JsonUtil.getRetMsg(3,"stepType 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(2,"用户还没登录");
			}
		}else{
			result=JsonUtil.getRetMsg(1,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String getMyApplyInfo(int step, int uId) {
		User users = UserServer.findUserIsCheck(uId);
		int isCheckMobile = users.getIsChechMobile();
		if (isCheckMobile != 1) {
			return JsonUtil.getRetMsg(1, "请先绑定手机号");
		}
		// 2 是否讲师
		FamousTeacher teacher=FamousTeacherSer.findFamousTeacherByUserId(uId);
		if (teacher != null) {
			return JsonUtil.getRetMsg(2, "您已经是讲师了，不能重复申请");
		}
		
		ApplyTeacher apply=ApplyTeacherSer.findLastApplyByUidWithStep(uId, 1, step);
		if(apply!=null){
			if(step==2){
				int provionceId=apply.getFk_ProvinceId();
				int cityId=apply.getFk_CityId();
				int positionId=apply.getFk_PositionId();
				int certificationId=apply.getFk_certificationId();
				Province province=ProvinceServer.getProvince(provionceId);
				City city=CityServer.getCity(cityId);
				Position position=PositionSer.findPositionById(positionId);
				Certification certification=CertificationSer.findCertificationById(certificationId);
				apply.setProvinceName(province!=null ?province.getName() :"");
				apply.setCityName(city!=null ? city.getName() : "");
				apply.setPositionName(position!=null ? position.getName() :"");
				apply.setCertificationName(certification!=null ? certification.getTypeName() :"");
			}else if(step==3){
				int parentId=apply.getFk_ParentUserId();
				int bankId=apply.getFk_BankId();
				User user=UserServer.findBaseInfoById(parentId);
				Bank bank=BankSer.findBankById(bankId);
				apply.setParentAccount(user!=null ? user.getMobileNum() : "");
				apply.setBankName(bank!=null ? bank.getBankName() :"");
			}
			
		}
		return JsonUtil.getMyApplyWithStep(step,apply);
	}

}
