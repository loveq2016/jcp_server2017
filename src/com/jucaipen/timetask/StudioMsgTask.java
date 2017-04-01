package com.jucaipen.timetask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;

import com.jucaipen.main.live.StudioChat;
import com.jucaipen.model.Studio;
import com.jucaipen.model.User;
import com.jucaipen.model.VideoLive;
import com.jucaipen.model.VideoLiveMsg;
import com.jucaipen.service.StudioSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.JPushUtils;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;

public class StudioMsgTask extends TimerTask {
	private int userId;
	private int liveId;
	private Map<String, String> params = new HashMap<String, String>();
	private boolean isManager;
	private static final String GET_LIVE_MSG = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=getlist";

	public StudioMsgTask(int userId, int liveId, boolean isManager) {
		this.userId = userId;
		this.liveId = liveId;
		this.isManager = isManager;
	}

	@Override
	public void run() {
		checkMsg(liveId, userId, isManager);
	}

	private void checkMsg(int sId, int uId, boolean isM) {
		int serverId = 0;
		int teacherId = 0;
		Studio studio = StudioSer.findStudioById(sId);
		if (studio == null) {
			return;
		}
		int liveId = studio.getVideoLiveId();
		VideoLive live = VideoLiveServer.getRoomInfo(liveId);
		if (live != null) {
			teacherId = live.getTeacherId();
		}
		if (liveId <= 0) {
			return;
		}
		User user;
		if (uId > 0) {
			user = UserServer.findUserChatInfo(uId);
		} else {
			user = new User();
		}
		int isRoomAdmin = user.getIsRoomAdmin();
		int fk_teacherId = user.getFk_roomTeacherId();
		if (isRoomAdmin == 1 && teacherId == fk_teacherId) {
			isM = true;
			serverId = 1;
		} else {
			isM = false;
			serverId = 0;
		}
		params.clear();
		params.put("roomid", 1 + "");
		params.put("topId", StudioChat.maxId + "");
		params.put("userId", uId + "");
		params.put("isServer", serverId + "");
		String result = LoginUtil.sendHttpPost(GET_LIVE_MSG, params);
		List<VideoLiveMsg> msgObjs = JsonUtil.repCompleMsgObj(result);
		if (msgObjs != null && msgObjs.size() > 0) {
			for (VideoLiveMsg liveMsg : msgObjs) {
				int sendId = liveMsg.getSendUserId();
				int receiverId = liveMsg.getReceiverId();
				User fromUser = UserServer.findBaseInfoById(sendId);
				User toUser = UserServer.findBaseInfoById(receiverId);
				if (fromUser == null) {
					fromUser = new User();
				}
				liveMsg.setSendFace(fromUser.getFaceImage());
				if (toUser == null) {
					toUser = new User();
				}
				liveMsg.setReceiverFace(toUser.getFaceImage());
			}
			String pushMsg = JsonUtil.createStudioMsg(msgObjs, true, uId);
			JPushClient client = JPushUtils.getJPush();
			PushPayload msgs = JPushUtils.createMsg("msg", "studioMsg",
					pushMsg, null);
			JPushUtils.pushMsg(client, msgs);
			if (isM) {
				StudioChat.maxId = msgObjs.get(msgObjs.size() - 1).getId();
			} else {
				StudioChat.maxId = msgObjs.get(msgObjs.size() - 1).getShenhe();
			}
		}
	}

}
