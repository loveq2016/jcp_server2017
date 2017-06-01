package com.jucaipen.main.tentutil;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;

public class TagHandler {
	private static String baseUrl = "https://console.tim.qq.com/v4/openim/im_add_tag";
	private static String baseRemove="https://console.tim.qq.com/v4/openim/im_remove_tag";
	// 获取用户 userSign url
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private static Map<String, String> param = new HashMap<String, String>();

	public static void setTag(String identifier, String tag) {
		StringBuilder builder=new StringBuilder();
		builder.append("{");
		builder.append(" \"UserTags\": [");
		builder.append("{");
		builder.append("\"To_Account\": \""+identifier+"\",");
		builder.append("\"Tags\": [\""+tag+"\"]");
		builder.append("}");     
		builder.append("]");
		builder.append("}");     
		String result = LoginUtil.sendPostStr(
				appUrl(baseUrl,getSign("onLineAdmin"), "onLineAdmin"), builder.toString(),
				null);
		System.out.println("result:" + result);

	}

	
	public static void removeTag(String identifier, String tag) {
		StringBuilder builder=new StringBuilder();
		builder.append("{");
		builder.append(" \"UserTags\": [");
		builder.append("{");
		builder.append("\"To_Account\": \""+identifier+"\",");
		builder.append("\"Tags\": [\""+tag+"\"]");
		builder.append("}");     
		builder.append("]");
		builder.append("}");           
		String result = LoginUtil.sendPostStr(
				appUrl(baseRemove,getSign("onLineAdmin"), "onLineAdmin"), builder.toString(),
				null);
		System.out.println("result:" + result);
	}

	public static void main(String[] args) {
		setTag("9060", "1");
		setTag("9060", "teacherId");
	}

	/**
	 * @return 获取管理员sign
	 */
	private static String getSign(String a) {
		param.clear();
		param.put("username", a);
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object = new JSONObject(signResult);
		boolean isCreate = object.optBoolean("Result");
		if (isCreate) {
			String sign = object.optString("UserSig");
			return sign;
		}
		return null;
	}

	public static String appUrl(String url,String usersig, String identifier) {
		StringBuffer buffer = new StringBuffer(url);
		buffer.append("?usersig="+usersig + "&");
		buffer.append("identifier=" + identifier + "&");
		buffer.append("sdkappid=1400028429&");
		buffer.append("random=" + RandomUtils.getRandomData(7) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}
}
