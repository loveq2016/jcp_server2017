package com.jucaipen.main.live;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.jucaipen.model.ChatMsgObject;
import com.jucaipen.model.User;
import com.jucaipen.model.VideoLiveMsg;
import com.jucaipen.service.UserServer;
import com.jucaipen.timetask.StudioMsgTask;
import com.jucaipen.utils.JPushUtils;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 * 
 *         演播室互动
 */
public class StudioChat extends HttpServlet {
	private static final long serialVersionUID = -3343269386742774065L;
	private Timer timer;
	private String result;
	private boolean isManager;
	// 获取是否禁言
	private static final String CHECKSHUT = "http://chat.jucaipen.com/ashx/user.ashx?action=checkshutup";
	// 用户统计
	private static final String LIVE_NUM = "http://chat.jucaipen.com/ashx/user.ashx?action=appuser";
	// 登录用户上线
	private static final int ADDLOGIN = 1;
	// 登录用户下线
	private static final int REMOVELOGIN = 2;
	// 创建游客
	private static final int ADDVISITOR = 3;
	// 游客下线
	private static final int REMOVEVISITOR = 4;
	private static final String GETTOPID = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=getTopId";
	private static final String GET_LIVE_MSG = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=getlist";
	private static final String SEND_LIVE_MSG = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=appadd";
	private Map<String, String> params = new HashMap<String, String>();
	
	public static int maxId=0;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String msgObject = request.getParameter("msgObject");
		if (StringUtil.isNotNull(msgObject)) {
			ChatMsgObject chatMsg = JsonUtil.parseChatMsg(msgObject);
			int userId = chatMsg.getFromId();
			int liveId = chatMsg.getLiveId();
			int opType = chatMsg.getOpType();
			if (opType == 1) {
				// 上线 --推送历史记录
				maxId = requestMsg(userId, liveId);
				timer = new Timer();
				StudioMsgTask task = new StudioMsgTask(userId, liveId,
						isManager);
				timer.scheduleAtFixedRate(task, new Date(), 1000 * 5);
			} else if (opType == 2) {
				// 聊天
				String msg = chatMsg.getMsg();
				int toId = chatMsg.getToId();
				String res = sendMsg(userId, liveId, msg, toId);
				out.print(res);
			} else {
				// 下线
				params.clear();
				if (userId > 0) {
					params.put("userid", userId + "");
					params.put("type", REMOVELOGIN + "");
					LoginUtil.sendHttpPost(LIVE_NUM, params);
				} else {
					// TODO 移除游客处理
					params.put("type", REMOVEVISITOR + "");
					LoginUtil.sendHttpPost(LIVE_NUM, params);
				}
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
				out.print(result);
			}
		}
		out.flush();
		out.close();
	}

	/**
	 * @param uId
	 * @param lId
	 * @param msg
	 * @param toId
	 * @return 发送消息
	 */
	public String sendMsg(int uId, int sId, String msg, int toId) {
		params.clear();
		User fromUser;
		User toUser;
		if (uId > 0) {
			fromUser = UserServer.findUserChatInfo(uId);
			String name = fromUser.getUserName();
			params.put("nickname", name);
			String res = LoginUtil.sendHttpPost(CHECKSHUT, params);
			if (res != null) {
				JSONObject object = new JSONObject(res);
				boolean isShut = object.getBoolean("Result");
				if (isShut) {
					return JsonUtil.getRetMsg(5, "您已经被禁言，暂时不能发言！");
				}
			}
			toUser = UserServer.findUserChatInfo(toId);
			if (toUser == null) {
				toUser = new User();
			}
		} else {
			// TODO 游客禁言处理
			return JsonUtil.getRetMsg(3, "请先登录");
		}
		params.clear();
		params.put("type", 1 + "");
		params.put("roomid", 1 + "");
		params.put("msgcontent", msg);
		params.put("userLevel", fromUser.getBuyProductId() + "");
		params.put("sendusername", fromUser.getNickName());
		params.put("SendUserNameId", fromUser.getUserName());
		params.put("fasongface", fromUser.getFaceImage());
		params.put("SendUserId", uId + "");
		params.put("SendManager", isManager + "");
		params.put("SendServiceId", fromUser.getServerId() + "");
		params.put("MessType", 0 + "");
		String result = LoginUtil.sendHttpPost(SEND_LIVE_MSG, params);
		JSONObject object = new JSONObject(result);
		boolean isSend = object.getBoolean("IsLogin");
		String Msg = object.getString("Msg");
		if (isSend) {
			return JsonUtil.getRetMsg(0, "消息发送成功");
		} else {
			return JsonUtil.getRetMsg(1, Msg);
		}
	}

	/**
	 * @param uId
	 * @param teacherId
	 * @param lId
	 * @param tId
	 *            上线请求消息
	 */
	public int requestMsg(int uId, int sId) {
		params.clear();
		int serverId = 0;
		int teacherId = 0;
		User user;
		if (uId > 0) {
			user = UserServer.findUserChatInfo(uId);
			// 添加登录用户
			params.put("userid", uId + "");
			params.put("type", ADDLOGIN + "");
			LoginUtil.sendHttpPost(LIVE_NUM, params);
		} else {
			user = new User();
			// TODO 创建游客信息
			params.put("type", ADDVISITOR + "");
			result = LoginUtil.sendHttpPost(LIVE_NUM, params);
		}
		int isRoomAdmin = user.getIsRoomAdmin();
		int fk_teacherId = user.getFk_roomTeacherId();
		if (isRoomAdmin == 1 && teacherId == fk_teacherId) {
			isManager = true;
			serverId = 1;
		} else {
			isManager = false;
			serverId = 0;
		}

		int topId = getTopCountId(serverId, isRoomAdmin);
		String msg = getOnLineMsg(topId, uId, serverId);
		List<VideoLiveMsg> msgObjs = JsonUtil.repCompleMsgObj(msg);
		if (msgObjs != null && msgObjs.size() > 0) {
			for (VideoLiveMsg liveMsg : msgObjs) {
				int sendId = liveMsg.getSendUserId();
				int receiverId = liveMsg.getReceiverId();
				User fromUser = UserServer.findBaseInfoById(sendId);
				User toUser = UserServer.findBaseInfoById(receiverId);
				if (fromUser == null) {
					fromUser = new User();
				}
				liveMsg.setSendFace(fromUser.getFaceImage() == null ? ""
						: fromUser.getFaceImage());
				if (toUser == null) {
					toUser = new User();
				}
				liveMsg.setReceiverFace(toUser.getFaceImage());
			}
			String pushMsg = JsonUtil.createStudioMsg(msgObjs, false, uId);
			JPushClient client = JPushUtils.getJPush();
			PushPayload msgs = JPushUtils.createMsg("msg", "studioMsg",
					pushMsg, null);
			PushResult res = JPushUtils.pushMsg(client, msgs);
			if (isManager) {
				return msgObjs.get(msgObjs.size() - 1).getId();
			} else {
				return msgObjs.get(msgObjs.size() - 1).getShenhe();
			}
		}
		return 0;
	}

	private String getOnLineMsg(int topId, int uId, int serverId) {
		params.clear();
		params.put("roomid", 1 + "");
		params.put("topId", topId + "");
		params.put("userId", uId + "");
		params.put("isServer", serverId + "");
		return LoginUtil.sendHttpPost(GET_LIVE_MSG, params);
	}

	private int getTopCountId(int userType, int isServer) {
		params.clear();
		params.put("userType", userType + "");
		params.put("topCount", 5 + "");
		params.put("roomId", 1 + "");
		params.put("isServer", isServer + "");
		String result = LoginUtil.sendHttpPost(GETTOPID, params);
		return StringUtil.isInteger(result) ? Integer.parseInt(result) : 0;
	}

}
