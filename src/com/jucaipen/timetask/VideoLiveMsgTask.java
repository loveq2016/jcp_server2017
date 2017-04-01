package com.jucaipen.timetask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;
import com.jucaipen.main.live.LiveChat;
import com.jucaipen.model.User;
import com.jucaipen.model.VideoLive;
import com.jucaipen.model.VideoLiveMsg;
import com.jucaipen.service.UserServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.JPushUtils;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;

public class VideoLiveMsgTask extends TimerTask {

	private Map<String, String> params = new HashMap<String, String>();
	private int maxId;
	private int userId;
	private int liveId;
	// private static final String
	// GET_LIVE_MSG="http://192.168.1.132/TeacherLive/ashx/VideoLive.ashx?action=GetMsgList";
	private static final String GET_LIVE_MSG = "http://www.jucaipen.com/TeacherLive/ashx/VideoLive.ashx?action=GetMsgList";
	private boolean isManager;

	public VideoLiveMsgTask(int maxId, int userId, int liveId, boolean isManager) {
		this.maxId = maxId;
		this.userId = userId;
		this.liveId = liveId;
		this.isManager = isManager;
	}

	@Override
	public void run() {
		checkMsg(liveId, userId, isManager);
	}

	public void checkMsg(int lId, int uId, boolean isM) {
		User user;
		int serverId = 0;
		int teacherId = 0;
		VideoLive live = VideoLiveServer.getRoomInfo(lId);
		if (live != null) {
			teacherId = live.getTeacherId();
		}
		if (uId > 0) {
			user = UserServer.findUserChatInfo(uId);
		} else {
			user = new User();
		}
		int isRoomAdmin = user.getIsRoomAdmin();
		int fk_roomId = user.getFk_roomTeacherId();
		if (isRoomAdmin == 1 && fk_roomId == teacherId) {
			isM = true;
			serverId = 1;
		} else {
			isM = false;
			serverId = 0;
		}
		params.clear();
		params.put("lid", lId + "");
		params.put("Topid", LiveChat.maxId + "");
		params.put("IsServerId", serverId + "");
		String result = LoginUtil.sendHttpPost(GET_LIVE_MSG, params);
		List<VideoLiveMsg> msgObjs = JsonUtil.repCompleLiveMsgObj(result);
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
			String pushMsg = JsonUtil.createLiveMsg(msgObjs, true, uId);
			JPushClient client = JPushUtils.getJPush();
			PushPayload msgs = JPushUtils.createMsg("msg", "liveMsg", pushMsg,
					null);
			 JPushUtils.pushMsg(client, msgs);
			if (isM) {
				maxId = msgObjs.get(msgObjs.size() - 1).getId();
				LiveChat.maxId = maxId;
			} else {
				maxId = msgObjs.get(msgObjs.size() - 1).getShenhe();
				LiveChat.maxId = maxId;
			}
		}
	}
}
