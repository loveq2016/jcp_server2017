package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.Rebate;
import com.jucaipen.model.User;
import com.jucaipen.service.RebateSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * 
 *         ��ȡ���°���Ϣ type 0 �հ� 1 �°�
 */
public class LatestList extends HttpServlet {
	private static final long serialVersionUID = -1470334903009105509L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String type = request.getParameter("type");
			String teacherId = request.getParameter("teacherId");
			if (!StringUtil.isNotNull(type)) {
				result = JsonUtil.getRetMsg(1, "type ��������Ϊ��");
			} else {
				if (StringUtil.isInteger(type)) {
					int t = Integer.parseInt(type);
					if (StringUtil.isNotNull(teacherId)
							&& StringUtil.isInteger(teacherId)) {
						int tId = Integer.parseInt(teacherId);
						result = initlist(t, tId);
					} else {
						result = JsonUtil.getRetMsg(3, "teacherId �����쳣");
					}
				} else {
					result = JsonUtil.getRetMsg(2, "type �������ָ�ʽ���쳣");
				}
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	public String initlist(int t, int tId) {
		// ��ʼ������Ϣ
		List<Rebate> rebateArray;
		if (t == 0) {
			// �հ� 
			rebateArray = RebateSer.findRebateByTeacher(tId,"day");
		} else if (t == 1) {
			// �°�
			rebateArray = RebateSer.findRebateByTeacher(tId,"month");
		} else {
			// ���
			rebateArray = RebateSer.findRebateByTeacher(tId,"year");
		}
		for (Rebate rebate : rebateArray) {
			int userId = rebate.getFromId();
			User user = UserServer.findUserById(userId);
			rebate.setFromName(user.getNickName());
			rebate.setFromFace(user.getFaceImage());
			rebate.setFromId(userId);
		}
		return JsonUtil.getLateList(rebateArray);
	}

}
