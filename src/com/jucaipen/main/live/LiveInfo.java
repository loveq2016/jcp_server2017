package com.jucaipen.main.live;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.jucaipen.manager.DataManager;
import com.jucaipen.model.Contribute;
import com.jucaipen.model.User;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;

/**
 * @author 杨朗飞
 * 
 *         更新直播间信息
 */
public class LiveInfo extends HttpServlet {
	// 获取群成员 url
	private String baseUrl = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_member_info";
	private Map<String, String> param = new HashMap<String, String>();
	// 房间id 集合列表
	private List<String> ids = new ArrayList<String>();
	private String result;
	// 在线人数
	private boolean hasCache;
	private ServletContext context;
	// 腾讯云APPID
	private static final String appId = "1400028429";
	// 获取用户 userSign url
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private static final long serialVersionUID = 1L;

	// 管理者账号
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		context = request.getServletContext();
		hasCache = (Boolean) request.getServletContext().getAttribute(
				"hasCache");
		String teacherId = request.getParameter("teacherId");
		hasCache = (Boolean) request.getServletContext().getAttribute(
				"hasCache");
		if (StringUtil.isNotNull(teacherId) && StringUtil.isInteger(teacherId)) {
			int tId = Integer.parseInt(teacherId);
			result = getOnLineInfo(tId);
		} else {
			result = JsonUtil.getRetMsg(1, "参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * 获取聊天室详细信息
	 */
	public String getRoomInfo(int roomId) {
		JsonObject object = new JsonObject();
		object.addProperty("GroupId", roomId + "");
		object.addProperty("Limit", 10);
		object.addProperty("Offset", 0);
		return LoginUtil.sendPostStr(createUrl(baseUrl, getSign("admin")),
				object.toString(), null);
	}

	/**
	 * @param tId
	 *            讲师id
	 */
	@SuppressWarnings("unchecked")
	private String getOnLineInfo(int tId) {
		// 1、贡献值
		int roomId;
		int bills = 0;
		int number = 0;
		Object cached = DataManager.getCached(Constant.VIDEO_CACHE, "liveInfo"
				+ tId, hasCache);
		if (cached != null) {
			return cached.toString();
		}
		List<Contribute> contributes = ContributeSer
				.findContributeGroupByTid("month");
		// 贡献排行
		for (int i = 0; i < contributes.size(); i++) {
			int teacherId = contributes.get(i).getTeacherId();
			if (teacherId == tId) {
				bills = contributes.get(i).getAllJucaiBills();
				number = i + 1;
				break;
			} else {
				bills = 0;
				number = contributes.size();
			}
		}
		// 获取讲师基本信息
		Object cached2 = DataManager.getCached(Constant.TEACHER_CACHE,
				"teacherInfo" + tId, hasCache);
		if (cached2 == null) {
			roomId = FamousTeacherSer.findRoomInfo(tId);
			new CacheUtils(Constant.TEACHER_CACHE).addToCache("teacherInfo"
					+ tId, roomId + "");
		} else {
			roomId = Integer.parseInt(cached2.toString());
		}
		// 获取直播室信息
		List<User> list;
		Object cached3 = DataManager.getCached(Constant.TEACHER_CACHE,
				"userInfo" + roomId, hasCache);
		if (cached3 == null) {
			list = getMember(roomId, tId);
			new CacheUtils(Constant.TEACHER_CACHE).addToCache("userInfo"
					+ roomId, list);
		} else {
			try {
				list = (List<User>) cached3;
			} catch (Exception e) {
				list = getMember(roomId, tId);
			}

		}
		String onLineData = JsonUtil.getOnLineData(bills, list, number);
		if (list.size() > 0) {
			new CacheUtils(Constant.VIDEO_CACHE).addToCache("liveInfo" + tId,
					onLineData);
		}
		return onLineData;
	}

	/**
	 * @param roomId
	 * @return 获取直播室成员信息
	 */
	private List<User> getMember(int roomId, int tId) {
		ids.clear();
		List<User> users = new ArrayList<User>();
		String roomInfo = getRoomInfo(roomId);
		// 2、直播人气
		JSONObject object = new JSONObject(roomInfo);
		String ok = object.optString("ActionStatus");
		if ("OK".equals(ok)) {
			int memberNum = object.optInt("MemberNum", 0);
			if (memberNum > 0) {
				context.setAttribute("onLine" + tId, memberNum);
			}
			JSONArray memberList = object.optJSONArray("MemberList");
			for (int i = 0; i < memberList.length(); i++) {
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
					
					// 不统计房间创建者
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
	 * @return 获取管理员sign
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
	 * @return 拼接腾讯云URL
	 */
	private String createUrl(String base, String sign) {
		StringBuffer buffer = new StringBuffer(base);
		buffer.append("?usersig=" + sign + "&");
		buffer.append("identifier=admin" + "&");
		buffer.append("sdkappid=" + appId + "&");
		buffer.append("random=" + RandomUtils.getRandomData(8) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}
