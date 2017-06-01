package com.jucaipen.manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.Fans;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.FansSer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JPushUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞 直播通知
 */
public class LiveNotify extends HttpServlet {
	private static final long serialVersionUID = -8777876924239505861L;
	//获取userSign
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String teacherId = request.getParameter("teacherId");
		String liveUrl = request.getParameter("liveUrl");
		out.print("success");
		out.flush();
		out.close();
		if (StringUtil.isNotNull(teacherId) && StringUtil.isInteger(teacherId)) {
			if (StringUtil.isNotNull(liveUrl)) {
				int tId = Integer.parseInt(teacherId);
					pushLiveNotify(tId, liveUrl);
				}
		}
		
	}

	private void pushLiveNotify(int tId, String liveUrl) {
		initAlias(tId, liveUrl);
	}

	
	/**
	 * @param free 
	 * @param tacticsId
	 *            获取关注讲师的用户别名
	 */
	private void initAlias(int teacherId, String liveUrl) {
		List<Fans> fans;
		Collection<String> aliases = new ArrayList<String>();
		FamousTeacher teacher=FamousTeacherSer.findTeacherNameById(teacherId);
		//FamousTeacher teacher = FamousTeacherSer.findTeacherBaseInfo(teacherId);
		//VideoLive live = VideoLiveServer.findLiveBytId(teacherId);
		Object cached = DataManager.getCached(Constant.DEFAULT_CACHE, "fans"+teacherId, true);
		if(cached==null){
			 fans = FansSer.findFansByTeacherId(teacherId);
		}else{
			try {
				fans=(List<Fans>) cached;
			} catch (Exception e) {
				 fans = FansSer.findFansByTeacherId(teacherId);
			}
		}
		 fans = FansSer.findFansByTeacherId(teacherId);
		JPushClient client = JPushUtils.getJPush();
		if (fans != null) {
			for (Fans fan : fans) {
				int uId = fan.getUserId();
				if (uId > 0) {
					aliases.add(uId + "");
				}
			}
			if (!aliases.isEmpty()) {
				String title = "【" + teacher.getNickName()
						+ "】正在直播，小伙伴赶紧来围观...";
				int roomId = teacher.getFk_UserId();
				int isFree=teacher.getLiveFree();
				String teacherFace=teacher.getHeadFace();
				//int liveId=live.getId();
				PushPayload payLoad = JPushUtils.createNptifyForAliase(title,
						"teacherId", teacherId, "roomId", roomId, "liveUrl",
						liveUrl,teacherFace, isFree,0,aliases);
				PushResult result = JPushUtils.pushMsg(client, payLoad);
				System.out.println(result.toString());
			} else {
				System.out.println("no user");
			}
		}
	}

	/**
	 * @param element
	 * @param client
	 * @return 判断当前用户是否注册推送
	 */
	private boolean hasRegin(String element, JPushClient client) {
		try {
			AliasDeviceListResult isAndroidExit = client.getAliasDeviceList(
					element, "android");
			AliasDeviceListResult isIosExit = client.getAliasDeviceList(
					element, "ios");
			return isAndroidExit.registration_ids.toString().length() > 3
					|| isIosExit.registration_ids.toString().length() > 3;
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return false;
	}

}
