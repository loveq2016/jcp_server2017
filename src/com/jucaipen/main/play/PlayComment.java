package com.jucaipen.main.play;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.User;
import com.jucaipen.model.UserComm;
import com.jucaipen.service.UserCommSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *    获取视频 证券知识 评论回复信息 commType 评论类型 0 视频评论 1 资讯评论
 */
public class PlayComment extends HttpServlet {
	private static final long serialVersionUID = 6322861183112029194L;
	private String result;
	private List<User> users = new ArrayList<User>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String usertAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(usertAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, usertAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String fkId = request.getParameter("fkId");
			String typeId = request.getParameter("typeId");
			String page = request.getParameter("page");
			String parentId = request.getParameter("parentId");
			if (StringUtil.isNotNull(fkId)) {
				if (StringUtil.isInteger(fkId)) {
					int fId = Integer.parseInt(fkId);
					if (StringUtil.isNotNull(typeId)
							&& StringUtil.isInteger(typeId)) {
						int tId = Integer.parseInt(typeId);
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
							if (StringUtil.isNotNull(parentId)
									&& StringUtil.isInteger(parentId)) {
								int pId = Integer.parseInt(parentId);
								result = initVideComms(fId, tId, p, pId);
							} else {
								result = JsonUtil.getRetMsg(5, "parentId 参数异常");
							}
						} else {
							result = JsonUtil.getRetMsg(4, "page 参数异常");
						}
					} else {
						result = JsonUtil.getRetMsg(3, "typeId 参数异常");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "fkId 参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "fkId 参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initVideComms(int fId, int tId, int p, int pId) {
		// 初始化视频知识 评论 回复信息
		users.clear();
		List<UserComm> comms;
		if (tId == 0) {
			// 视频
			comms = UserCommSer.findCommenBykId(fId, p, pId, 1);
		} else {
			// 资讯
			comms = UserCommSer.findCommenBykId(fId, p, pId, 0);
		}
		for (UserComm comm : comms) {
			int uId = comm.getUserId();
			User user = UserServer.findUserById(uId);
			if (user == null) {
				user = new User();
			}
			users.add(user);
		}
		return JsonUtil.getUserComms(comms, users);
	}
}
