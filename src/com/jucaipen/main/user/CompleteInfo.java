package com.jucaipen.main.user;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.User;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator 修改个人资料
 */
public class CompleteInfo extends HttpServlet {
	private static final long serialVersionUID = 1794034294102325495L;
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
			String userId = request.getParameter("userId");
			if (StringUtil.isNotNull(userId)) {
				if (StringUtil.isInteger(userId)) {
					int uId = Integer.parseInt(userId);
					if (uId > 0) {
						result = initUserInfo(uId, request);
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
		out.println(result);
		out.flush();
		out.close();
	}

	private String initUserInfo(int uId, HttpServletRequest request) {
		// 初始化用户信息
		String nickName = request.getParameter("nickName");
		String trueName = request.getParameter("trueName");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String provinceId=request.getParameter("provinceId");
		String areaId=request.getParameter("areaId");
		String cityId = request.getParameter("cityId");
		String investId = request.getParameter("investId");
		String plain = request.getParameter("plain");
		if (!StringUtil.isNotNull(nickName)) {
			return JsonUtil.getRetMsg(1, "昵称不能为空");
		}
		User user = new User();
		user.setNickName(nickName);
		user.setTrueName(StringUtil.isNotNull(trueName) ? trueName : "");

		if (StringUtil.isNotNull(sex)) {
			user.setSex(sex);
		}

		user.setBirthday(StringUtil.isNotNull(birth) ? birth : "");
		
		if(StringUtil.isNotNull(provinceId)&&StringUtil.isInteger(provinceId)){
			int pId=Integer.parseInt(provinceId);
			if(pId>0){
				user.setProvinceId(pId);
			}
		}
		

		if (StringUtil.isNotNull(cityId) && StringUtil.isInteger(cityId)) {
			int cId = Integer.parseInt(cityId);
			if(cId>0){
				user.setCityId(cId);
			}
		}
		
		if(StringUtil.isNotNull(areaId)&&StringUtil.isInteger(areaId)){
			int aId=Integer.parseInt(areaId);
			if(aId>0){
				user.setAreaId(aId);
			}
		}

		if (StringUtil.isNotNull(investId) && StringUtil.isInteger(investId)) {
			int inveId = Integer.parseInt(investId);
			user.setInvestId(inveId);
		}
		user.setDescript(StringUtil.isNotNull(plain) ? plain : "");
		int isSuccess = UserServer.updateUser(uId, user);
		return isSuccess == 1 ? JsonUtil.getRetMsg(0, "个人资料提交成功") : JsonUtil
				.getRetMsg(1, "个人资料提交失败");
	}

}
