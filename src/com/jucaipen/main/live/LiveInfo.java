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
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.User;
import com.jucaipen.service.ContributeSer;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞
 * 
 *   更新直播间信息
 */
public class LiveInfo extends HttpServlet {
	private String baseUrl="https://console.tim.qq.com/v4/group_open_http_svc/get_group_info";
	private Map<String, String> param=new HashMap<String, String>();
	private List<String> ids=new ArrayList<String>();
	private String result;
	private static final String GET_SIGN="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	private static final long serialVersionUID = 1L;
	private static final String account="admin";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String teacherId=request.getParameter("teacherId");
		if(StringUtil.isNotNull(teacherId)&&StringUtil.isInteger(teacherId)){
			int tId=Integer.parseInt(teacherId);
			result=getOnLineInfo(tId);
		}else{
			result=JsonUtil.getRetMsg(1,"参数异常");
		}
		out.println(result);
		out.flush();
		out.close();
	}
	
	/**
	 * 获取聊天室详细信息
	 */
	public   String getRoomInfo(List<String>  ids){
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		buffer.append("\"GroupIdList\":[");
		
		for(String id : ids){
			buffer.append("\""+id+"\"");
			buffer.append(",");
		}
		buffer.replace(buffer.length()-1, buffer.length(), "");
		buffer.append("]");
		buffer.append("}");
		return LoginUtil.sendPostStr(createUrl(baseUrl,getSign(account),account),buffer.toString());
	}
	
	
	/**
	 * @param tId  讲师id
	 */
	private String getOnLineInfo(int tId) {
		//1、贡献总榜
		int bills = ContributeSer.findAllContributeByTid(tId);
		FamousTeacher teacher = FamousTeacherSer.findTeacherBaseInfo(tId);
		int userId = teacher.getFk_UserId();
		//获取直播室信息
		List<User> list = getMember(userId);
		return JsonUtil.getOnLineData(bills,list);
	}

	/**
	 * @param roomId
	 * @return  获取直播室成员信息
	 */
	private List<User> getMember(int roomId) {
		ids.clear();
		List<User> users=new ArrayList<User>();
		ids.add(roomId+"");
		String roomInfo = getRoomInfo(ids);
		//2、直播人气
		JSONObject object=new JSONObject(roomInfo);
		String ok=object.optString("ActionStatus");
		if("OK".equals(ok)){
			JSONArray groupInfo=object.optJSONArray("GroupInfo");
			for(int i=0;i<groupInfo.length();){
				JSONObject detail=groupInfo.optJSONObject(i);
				JSONArray memberList=detail.optJSONArray("MemberList");
				int memberNum=detail.optInt("MemberNum",0);
				if(memberList!=null){
					for(int j=0;j<memberList.length();j++){
						JSONObject member=memberList.optJSONObject(j);
						String account=member.optString("Member_Account");
						if(StringUtil.isNotNull(account)&&StringUtil.isInteger(account)){
							User user = UserServer.findBaseInfoById(Integer.parseInt(account));
							if(roomId!=user.getId()){
								users.add(user);
							}
						}
					}
					return users;
				}
			}
		}
		//3、直播室成员列表
		return users;
	}


	/**
	 * @return 获取管理员sign
	 */
	private String getSign(String a) {
		param.clear();
		param.put("username", a);
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object=new JSONObject(signResult);
		boolean isCreate=object.optBoolean("Result");
		if(isCreate){
			return object.optString("UserSig");
		}
		return null;
	}

	/**
	 * @param base
	 * @param sign
	 * @return  拼接腾讯云URL
	 */
	private String createUrl(String base,String sign,String acct) {
		StringBuffer buffer=new StringBuffer(base);
		buffer.append("?usersig="+sign+"&");
		buffer.append("identifier="+acct+"&");
		buffer.append("sdkappid=1400027389&");
		buffer.append("random="+RandomUtils.getRandomData(8)+"&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}
