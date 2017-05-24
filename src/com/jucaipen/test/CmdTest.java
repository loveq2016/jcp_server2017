package com.jucaipen.test;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author ���ʷ�
 * 
 *         ����ֱ������Ϣ
 */
public class CmdTest {
	// ��ȡȺ��Ա url
	private static String baseUrl = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info";
	private static Map<String, String> param = new HashMap<String, String>();
	// ��Ѷ��APPID
	private static final String appId = "1400028429";
	// userSign ʧЧ̬
	private static String unValiable="https://console.tim.qq.com/v4/im_open_login_svc/kick";
	// ��ȡ�û� userSign url
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	public static void main(String[] args) {
		//getOnLineInfo(22097);
		kickUser(3680);
	}
	
	
	
	public static void kickUser(int uId){
		JsonObject object=new JsonObject();
		object.addProperty("Identifier", uId+"");
		String string = LoginUtil.sendPostStr(createUrl(unValiable, getSign("admin",0)), object.toString(), null);
		System.out.println(string);
	}
	

	/**
	 * ��ȡ��������ϸ��Ϣ
	 */
	public static String getRoomInfo(int roomId) {
		JsonObject object = new JsonObject();
		object.addProperty("GroupId", roomId+"");
		object.addProperty("Offset", 0);
		return LoginUtil.sendPostStr(createUrl(baseUrl, getSign("admin",0)),
				object.toString(), null);
	}

	/**
	 * @param tId
	 *            ��ʦid
	 */
	public static void getOnLineInfo(int roomId) {
		// ��ȡֱ������Ϣ
		getMember(roomId);
		
	}

	/**
	 * @param roomId
	 * @return ��ȡֱ���ҳ�Ա��Ϣ
	 */
	public static void getMember(int roomId) {
		String roomInfo = getRoomInfo(roomId);
		// 2��ֱ������
		JSONObject object = new JSONObject(roomInfo);
		String ok = object.optString("ActionStatus");
		if ("OK".equals(ok)) {
			int memberNum = object.optInt("MemberNum", 0);
			System.out.println("memberNum="+memberNum);
			JSONArray memberList = object.optJSONArray("MemberList");
			for (int i = 0; i < memberList.length(); i++) {
				JSONObject member = memberList.optJSONObject(i);
				String account = member.optString("Member_Account");
				if (StringUtil.isNotNull(account)
						&& StringUtil.isInteger(account)) {
					int userId = Integer.parseInt(account);
					 getSign(null, userId);
					} 
				}
			 System.out.println("������....");
			}
		}

	/**
	 * @return ��ȡ����Աsign
	 */
	private static String getSign(String a,int uId) {
		param.clear();
		if(uId==0){
			param.put("username", a);
		}else{
			param.put("userid", uId+"");
		}
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object = new JSONObject(signResult);
		boolean isCreate = object.optBoolean("Result");
		if (isCreate) {
			String sign = object.optString("UserSig");
			new CacheUtils(Constant.CACHE_SIGN).addToCache("userSign"+a, sign);
			return sign;
		}
		return null;
	}

	/**
	 * @param base
	 * @param sign
	 * @return ƴ����Ѷ��URL
	 */
	private static  String createUrl(String base, String sign) {
		StringBuffer buffer = new StringBuffer(base);
		buffer.append("?usersig=" + sign + "&");
		buffer.append("identifier=admin" + "&");
		buffer.append("sdkappid=" + appId + "&");
		buffer.append("random=" + RandomUtils.getRandomData(8) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}
