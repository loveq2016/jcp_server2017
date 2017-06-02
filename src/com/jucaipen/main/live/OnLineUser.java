package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.jucaipen.manager.DataManager;
import com.jucaipen.model.User;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;

/**
 * @author ���ʷ�
 * 
 *         ����ֱ������Ϣ
 */
public class OnLineUser extends HttpServlet {
	// ��ȡȺ��Ա url
	private String baseUrl = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info";
	private Map<String, String> param = new HashMap<String, String>();
	// ����id �����б�
	private List<String> ids = new ArrayList<String>();
	private String result;
	// ��������
	private boolean hasCache;
	// ��Ѷ��APPID
	private static final String appId = "1400028429";
	// ��ȡ�û� userSign url
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private static final long serialVersionUID = 1L;

	// �������˺�
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		hasCache = (Boolean) request.getServletContext().getAttribute(
				"hasCache");
		String teacherId = request.getParameter("teacherId");
		String page=request.getParameter("page");
		hasCache = (Boolean) request.getServletContext().getAttribute(
				"hasCache");
		if (StringUtil.isNotNull(teacherId) && StringUtil.isInteger(teacherId)&& StringUtil.isNotNull(page) &&StringUtil.isInteger(page)) {
			int tId = Integer.parseInt(teacherId);
			int p=Integer.parseInt(page);
			result = getOnLineInfo(tId,p);
		} else {
			result = JsonUtil.getRetMsg(1, "�����쳣");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * ��ȡ��������ϸ��Ϣ
	 * @param p 
	 */
	public String getRoomInfo(int roomId, int p) {
		JsonObject object = new JsonObject();
		object.addProperty("GroupId", roomId + "");
		object.addProperty("Limit", 15);
		object.addProperty("Offset", (p-1)*15);
		return LoginUtil.sendPostStr(createUrl(baseUrl, getSign("onLineAdmin")),
				object.toString(), null);
	}

	/**
	 * @param tId
	 *            ��ʦid
	 * @param p 
	 */
	@SuppressWarnings("unchecked")
	private String getOnLineInfo(int tId, int p) {
		// 1������ֵ
		int roomId;
		Object cached = DataManager.getCached(Constant.VIDEO_CACHE, p+"onLine"
				+ tId, hasCache);
		if (cached != null) {
			return cached.toString();
		}
		// ��ȡ��ʦ������Ϣ
		Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE,
				"teacherInfo" + tId, hasCache);
		if (cached2 == null) {
			roomId = FamousTeacherSer.findRoomInfo(tId);
			new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherInfo"
					+ tId, roomId + "");
		} else {
			roomId = Integer.parseInt(cached2.toString());
		}
		// ��ȡֱ������Ϣ
		List<User> list;
		Object cached3 = DataManager.getCached(Constant.TEACHER_CACHE,
				p+"onLineUser" + roomId, hasCache);
		if (cached3 == null) {
			list = getMember(roomId, tId,p);
			new CacheUtils(Constant.TEACHER_CACHE).addToCache(p+"onLineUser"
					+ roomId, list);
		} else {
			try {
				list = (List<User>) cached3;
			} catch (Exception e) {
				list = getMember(roomId, tId,p);
			}

		}
		String onLineData = JsonUtil.getOnLineData(list);
		if (list.size() > 0) {
			new CacheUtils(Constant.VIDEO_CACHE).addToCache(p+"onLine" + tId,
					onLineData);
		}
		return onLineData;
	}

	/**
	 * @param roomId
	 * @param p 
	 * @return ��ȡֱ���ҳ�Ա��Ϣ
	 */
	private List<User> getMember(int roomId, int tId, int p) {
		ids.clear();
		List<User> users = new ArrayList<User>();
		String roomInfo = getRoomInfo(roomId,p);
		// 2��ֱ������
		JSONObject object = new JSONObject(roomInfo);
		String ok = object.optString("ActionStatus");
		if ("OK".equals(ok)) {
			int memberNum = object.optInt("MemberNum", 0);
			JSONArray memberList = object.optJSONArray("MemberList");
			for (int i = 0; memberList!=null&&i < memberList.length(); i++) {
				JSONObject member = memberList.optJSONObject(i);
				String account = member.optString("Member_Account");
				if (StringUtil.isNotNull(account)
						&& StringUtil.isInteger(account)) {
					int userId = Integer.parseInt(account);
					User user;
					Object cached = DataManager.getCached(
							Constant.TEACHER_CACHE, "userInfo" + userId,
							hasCache);
					if (cached == null) {
						user = UserServer.findFaceImageById(userId);
						user.setId(userId);
						new CacheUtils(Constant.TEACHER_CACHE).addToCache(
								"userInfo" + userId, user);
					} else {
						try {
							user = (User) cached;
						} catch (Exception e) {
							user = UserServer.findFaceImageById(userId);
							user.setId(userId);
						}
					}
					int integeral = user.getAllIntegral();
					if(integeral>0){
						user.setUserLeval(integeral < 30001 ? (int) Math
								.ceil((double)integeral / 100)
								: (int) Math.ceil((double) 30001 / 100));
					}else{
						user.setUserLeval(0);
					}
					
					// ��ͳ�Ʒ��䴴����
					if (roomId != user.getId()) {
						user.setOnLineNum(memberNum);
						users.add(user);
					}
				}
			}
		}
		return users;
	}

	/**
	 * @return ��ȡ����Աsign
	 */
	private String getSign(String a) {
		Object cached = DataManager.getCached(Constant.CACHE_SIGN, "userSign"
				+ a, hasCache);
		if (cached != null) {
			return cached.toString();
		}
		param.clear();
		param.put("username", a);
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object = new JSONObject(signResult);
		boolean isCreate = object.optBoolean("Result");
		if (isCreate) {
			String sign = object.optString("UserSig");
			new CacheUtils(Constant.CACHE_SIGN)
					.addToCache("userSign" + a, sign);
			return sign;
		}
		return null;
	}

	/**
	 * @param base
	 * @param sign
	 * @return ƴ����Ѷ��URL
	 */
	private String createUrl(String base, String sign) {
		StringBuffer buffer = new StringBuffer(base);
		buffer.append("?usersig=" + sign + "&");
		buffer.append("identifier=onLineAdmin" + "&");
		buffer.append("sdkappid=" + appId + "&");
		buffer.append("random=" + RandomUtils.getRandomData(8) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}