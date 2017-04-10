package com.jucaipen.utils;
import java.util.Collection;
import java.util.HashMap;
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
	 * @return ��ȡ���͹�����
	 */
	public static JPushClient getJPush() {
		if (client == null) {
			client = new JPushClient(masterSecret, appKey);
		}
		return client;
	}
	
	
	/**
	 * @param msg
	 * @return ����֪ͨ Ŀ�� �� android iOS   ȫ��
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
	 * @param key �Զ����ֶ�key1
	 * @param value �Զ����ֶ� value1
	 * @param dataKay �Զ����ֶ� key2
	 * @param dataValue �Զ����ֶ� value2
	 * @param aliases Ҫ���͵ı���
	 * @param msg   ���͵���Ϣ����
	 * @return ����֪ͨ Ŀ�� �� android iOS   ָ������
	 */
	public static PushPayload createNptifyForAliase(String msg, String key,
			Number value, String dataKay, int dataValue,String key1,String valueStr,String teacherFace,
			Collection<String> aliases) {
		Map<String, String> map=new HashMap<String, String>();
		map.put(key, value+"");
		map.put(dataKay, dataValue+"");
		map.put(key1, valueStr);
		map.put("teacherFace",teacherFace);
		return PushPayload
				.newBuilder()
				.setNotification(Notification.android(msg, msg, map))
				.setMessage(
						Message.newBuilder().setMsgContent(msg)
								.addExtra(key, value)
								.addExtra(key1, valueStr)
								.addExtra(dataKay, dataValue)
								.build())
								.setPlatform(Platform.all())
				.setAudience(Audience.alias(aliases)).build();
	}
	
	
	
	/**
	 * @param msg  ��Ϣ����
	 * @param key   �Զ����ֶ�key1
	 * @param value   �Զ����ֶ�value1
	 * @param dataKey  �Զ����ֶ� key2
	 * @param dateValue �Զ����ֶ� value2
	 * @return  ������Ϣ�������豸
	 */
	public static PushPayload createNptifityForAll(String msg,String key,Number value,String dataKey,String dateValue){
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setNotification(Notification.alert(msg))
				.setMessage(Message.newBuilder().setMsgContent(msg).build()).build();
	}
	
	
	
	/**
	 * @param key  �Զ����ֶ� key1
	 * @param value  �Զ����ֶ� value1
	 * @param  dataKay �Զ����ֶ� key2
	 * @param  dataValue �Զ����ֶ� value2
	 * @param tagValues  ��Ҫ���͵�tag
	 * @param msg    ��Ϣ����
	 * @return ����֪ͨ Ŀ�� �� android iOS   ָ��TAG
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
	 * @return ����͸����Ϣ Ŀ�� �� android iOS   ȫ��
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
	 * @return ������Ϣ
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
