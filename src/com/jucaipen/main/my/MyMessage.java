package com.jucaipen.main.my;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.SysMessage;
import com.jucaipen.model.User;
import com.jucaipen.service.SysMessageSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *  获取系统消息
 */
public class MyMessage extends HttpServlet {
	private static final long serialVersionUID = 3438247395328245318L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String typeId = request.getParameter("typeId");
		String page = request.getParameter("page");
		if (StringUtil.isNotNull(userId) && StringUtil.isInteger(userId)) {
			int uId = Integer.parseInt(userId);
			if (uId > 0) {
				if (StringUtil.isNotNull(typeId)
						&& StringUtil.isInteger(typeId)) {
					int type = Integer.parseInt(typeId);
					if (StringUtil.isNotNull(page)
							&& StringUtil.isInteger(page)) {
						int p = Integer.parseInt(page);
						result=initMyMessage(uId, type, p);
					}else{
						result=JsonUtil.getRetMsg(1,"page 参数不能为空");
					}
				}else{
					result=JsonUtil.getRetMsg(2,"typeId 参数异常");
				}
			}else{
				result=JsonUtil.getRetMsg(3,"用户还没登录");
			}
		}else{
			result=JsonUtil.getRetMsg(4,"userId 参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initMyMessage(int uId, int type, int p) {
		List<SysMessage> message;
		if (type == 0) {
			// 获取我的收件箱
			message = SysMessageSer.findMessageByReceiverId(uId, p);
		} else {
			// 获取我的发件箱
			message = SysMessageSer.findMessageBySenderId(uId, p);
		}
		if (message != null) {
			for (SysMessage msg : message) {
				int receiverId = msg.getReceiverId();
				int senderId = msg.getSenderId();
				User sender = UserServer.findBaseInfoById(senderId);
				User receiver = UserServer.findBaseInfoById(receiverId);
				msg.setReceiverName(receiver.getNickName());
				msg.setSendName(sender.getNickName());
			}
		}
		
		return JsonUtil.getMyMessage(message);

	}

}
