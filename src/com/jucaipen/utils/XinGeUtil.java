package com.jucaipen.utils;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

/**
 * @author Administrator
 *
 *
 *   信鸽推送
 */
public class XinGeUtil {
	public static final String APP_KEY = "10f91058cbe939b1972867e74860af07";
	public static final int APP_ID = 2100139212;
	private Map<String, Object> custom = new HashMap<String, Object>();
	private static Message message;
	private static XinGeUtil xUtil;
	private XingeApp xinge;
	private static final String apkPath="http://dd.myapp.com/16891/6162317DC23CBB7E015FF123D3FB4835.apk?fsname=com.example.androidnetwork_1.2_3.apk";
	private static final String activityName="com.jucaipen.seecityactivity.RoomActivity";

	private XinGeUtil() {

	}

	public static XinGeUtil getInstance(boolean isMsg) {
		if (xUtil == null) {
			message = new Message();
			xUtil = new XinGeUtil();
			initMessage(isMsg);
		}
		return xUtil;
	}

	/**
	 * @param isMsg
	 * 
	 *            发送消息类型 true 透传消息 false 通知消息
	 */
	private static void initMessage(boolean isMsg) {
		if (isMsg) {
			// 推送透传消息
			message.setType(Message.TYPE_MESSAGE);
		} else {
			message.setType(Message.TYPE_NOTIFICATION);
		}
		Style style = new Style(1);
		style = new Style(1, 1, 1, 1, 0);
		message.setStyle(style);
		TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
		message.addAcceptTime(acceptTime1);
	}

	
	/**
	 * @param msg
	 * @param account
	 * @return  向指定账号发送聊天信息
	 */
	public JSONObject pushAccountDevice(String msg, String account) {
		if (xinge == null) {
			xinge = new XingeApp(APP_ID, APP_KEY);
		}
		custom.clear();
		custom.put("chatMessage", msg);    
		message.setCustom(custom);          
		message.setContent("1");
		message.setType(Message.TYPE_MESSAGE);
		JSONObject ret = xinge.pushSingleAccount(XingeApp.DEVICE_ALL, account,
				message);
		return ret;
	}

	/** 
	 * @param liveId
	 * @param title
	 * @param msg
	 * @return 推送直播信息
	 */
	public JSONObject pushAllDevice(int liveId, String title, String msg) {
		if (xinge == null) {
			xinge = new XingeApp(APP_ID, APP_KEY);
		}
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_ACTIVITY);
		action.setActivity(activityName);
		action.setAtyAttrIntentFlag(0);
		message.setAction(action);
		custom.clear();
		custom.put("liveId", liveId);
		message.setCustom(custom);
		message.setTitle(title);
		message.setContent(msg);

		JSONObject res = xinge.pushAllDevice(XingeApp.DEVICE_ALL, message);
		return res;
	}

	/**
	 * @param liveId
	 * @param title
	 * @param msg
	 * @return 推送更新信息
	 */
	public JSONObject pushAllUpdateDevice(int versionId, String title,
			String msg) {
		if (xinge == null) {
			xinge = new XingeApp(APP_ID, APP_KEY);
		}
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		action.setUrl(apkPath);
		action.setConfirmOnUrl(0);
		message.setAction(action);
		custom.clear();
		custom.put("liveId", versionId);
		message.setCustom(custom);
		message.setTitle(title);
		message.setContent(msg);

		JSONObject res = xinge.pushAllDevice(XingeApp.DEVICE_ALL, message);
		return res;
	}

	/**
	 * @param token
	 * @param title
	 * @param msg
	 * @return 发送指定Token设备消息
	 */
	public JSONObject pushTokenDevMsg(String token, String title, String msg) {
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		message.setAction(action);
		message.setCustom(custom);
		message.setTitle(title);
		message.setContent(msg);
		JSONObject res = XingeApp.pushTokenAndroid(APP_ID, APP_KEY, title, msg,
				token);
		XingeApp.pushTokenIos(APP_ID, APP_KEY, msg, token, XingeApp.IOSENV_DEV);
		return res;
	}

	/**
	 * @param tag
	 * @param title
	 * @param msg
	 * @return    推送指定Tag设备消息
	 */
	public JSONObject pushTagDevMsg(String tag, String title, String msg) {
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		message.setAction(action);
		message.setCustom(custom);
		message.setTitle(title);
		message.setContent(msg);
		JSONObject res = XingeApp.pushTagAndroid(APP_ID, APP_KEY, title, msg,
				tag);
		XingeApp.pushTagIos(APP_ID, APP_KEY, msg, tag, XingeApp.IOSENV_DEV);
		return res;
	}
	
	
	/**
	 * @param title
	 * @param msg
	 * @return   推送所有设备消息
	 */
	public JSONObject pushAllDevMsg(String title,String msg){
		if (xinge == null) {
			xinge = new XingeApp(APP_ID, APP_KEY);
		}
		ClickAction action = new ClickAction();
		message.setAction(action);
		message.setCustom(custom);
		message.setTitle(title);
		message.setContent(msg);
		JSONObject res = xinge.pushAllDevice(XingeApp.DEVICE_ALL, message);
		return res;
	}

	public JSONObject querryTag() {
		if (xinge == null) {
			xinge = new XingeApp(APP_ID, APP_KEY);
		}
		JSONObject result = xinge.queryTags();
		return result;
	}

}
