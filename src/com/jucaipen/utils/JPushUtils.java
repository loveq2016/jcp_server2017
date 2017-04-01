package com.jucaipen.utils;

import java.util.Collection;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {

	private static final String masterSecret = "9f494f11a07a309a5ff89e93";
	private static final String appKey = "7ea0243b8390b06fa4789b34";
	private static JPushClient client;

	/**
	 * @return 获取推送构造器
	 */
	public static JPushClient getJPush() {
		if (client == null) {
			client = new JPushClient(masterSecret, appKey);
		}
		return client;
	}
	
	
	/**
	 * @param msg
	 * @return 创建通知 目标 ： android iOS   全部
	 */
	public static PushPayload createNptifyForAll(String msg, String key,
			Number value, String dataKay, int dataValue) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setNotification(Notification.alert(msg))
				.setMessage(
						Message.newBuilder().setMsgContent(msg)
								.addExtra(key, value)
								.addExtra(dataKay, dataValue).build())
				.build();
	}

	/**
	 * @param key 自定义字段key1
	 * @param value 自定义字段 value1
	 * @param dataKay 自定义字段 key2
	 * @param dataValue 自定义字段 value2
	 * @param aliases 要推送的别名
	 * @param msg   推送的消息内容
	 * @return 创建通知 目标 ： android iOS   指定别名
	 */
	public static PushPayload createNptifyForAliase(String msg, String key,
			Number value, String dataKay, int dataValue,
			Collection<String> aliases) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setNotification(Notification.alert(msg))
				.setMessage(
						Message.newBuilder().setMsgContent(msg)
								.addExtra(key, value)
								.addExtra(dataKay, dataValue).build())
				.setAudience(Audience.alias(aliases)).build();
	}
	
	
	
	
	
	/**
	 * @param msg  消息内容
	 * @param key   自定义字段key1
	 * @param value   自定义字段value1
	 * @param dataKey  自定义字段 key2
	 * @param dateValue 自定义字段 value2
	 * @return  推送消息到所有设备
	 */
	public static PushPayload createNptifityForAll(String msg,String key,Number value,String dataKey,String dateValue){
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setNotification(Notification.alert(msg))
				.setMessage(Message.newBuilder().setMsgContent(msg).build()).build();
	}
	
	
	
	/**
	 * @param key  自定义字段 key1
	 * @param value  自定义字段 value1
	 * @param  dataKay 自定义字段 key2
	 * @param  dataValue 自定义字段 value2
	 * @param tagValues  所要推送的tag
	 * @param msg    消息内容
	 * @return 创建通知 目标 ： android iOS   指定TAG
	 */
	public static PushPayload createNptifyForTag(String msg, String key,
			Number value, String dataKay, int dataValue,
			Collection<String> tagValues) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setNotification(Notification.alert(msg))
				.setMessage(
						Message.newBuilder().setMsgContent(msg)
								.addExtra(key, value)
								.addExtra(dataKay, dataValue).build())
				.setAudience(Audience.tag(tagValues)).build();
	}

	/**
	 * @param alert
	 * @param title
	 * @param content
	 * @param extras
	 * @return 创建透传消息 目标 ： android iOS   全部
	 */
	public static PushPayload createMsg(String alert, String title,
			String content, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setMessage(
						Message.newBuilder().setMsgContent(content)
								.setTitle(title).build())
				.setAudience(Audience.all()).build();
	}
	
	/**
	 * @param client
	 * @param payLoad
	 * @return 推送消息
	 */
	public static PushResult pushMsg(JPushClient client, PushPayload payLoad) {
		try {
			PushResult result = client.sendPush(payLoad);
			return result;
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return null;

	}

}
