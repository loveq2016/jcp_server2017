/*package com.jucaipen.main.live;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jucaipen.model.User;
import com.jucaipen.service.UserServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
import com.jucaipen.utils.StringUtil;
*//**
 * @author 杨朗飞
 *   在线群人数更新
 *//*
public class LiveInfoNotify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  String baseUrl="https://console.tim.qq.com/v4/group_open_http_svc/send_group_msg";
	
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	//腾讯云APPID
	private static final String appId="1400028429";
	private Map<String, String> param = new HashMap<String, String>();
	private List<User> userArray=new ArrayList<User>();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletInputStream stream = request.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		StringBuffer sb = new StringBuffer();  
        String temp;  
        while ((temp = reader.readLine()) != null) {  
            sb.append(temp);  
        }  
        reader.close();
        parseOnLineNum(sb.toString());
		out.print("success");
		out.flush();
		out.close();
	}
	
	*//**
	 * @param string
	 * 
	 *   解析上线、下线人数
	 *//*
	private void parseOnLineNum(String string) {
		int num=0;
		boolean isQuit=false;
		{"CallbackCommand":"Group.CallbackAfterMemberExit",
			"ExitMemberList":[{"Member_Account":"22951"}],
			"ExitType":"Quit","GroupId":"22097","Operator_Account":"22951","Type":"AVChatRoom"}
		
		
		{"CallbackCommand":"Group.CallbackAfterNewMemberJoin","GroupId":"19836","JoinType":"Apply",
			"NewMemberList":[{"Member_Account":"3680"}],"Operator_Account":"3680","Type":"AVChatRoom"}
		JSONObject object=new JSONObject(string);
		String groupId = object.optString("GroupId");
		String exitType=object.optString("ExitType");
		JSONArray jsonArray;
		if("Quit".equals(exitType)){
			//退群
			 isQuit=true;
			 jsonArray = object.optJSONArray("ExitMemberList");
			 num=userArray.size()-jsonArray.length();
		}else{
			//加群
			 isQuit=false;
			 jsonArray = object.optJSONArray("NewMemberList");
			 num=userArray.size()+jsonArray.length();
		}
		for(int i=0;i<jsonArray.length();i++){
			JSONObject member=jsonArray.optJSONObject(i);
			String account = member.optString("Member_Account");
			if(StringUtil.isNotNull(account)&&StringUtil.isInteger(account)){
				int uId=Integer.parseInt(account);
				String userFace = UserServer.findFaceImageById(uId);
				User user=new User();
				user.setId(uId);
				user.setFaceImage(userFace);
				if(isQuit&&userArray.contains(user)){
					userArray.remove(user);
				}else if(!userArray.contains(user)){
					userArray.add(user);
				}
			}
		}
		
		SendOnLineMsg sendOnLineMsg=new SendOnLineMsg(num,groupId);
		Timer timer=new Timer();
		timer.schedule(sendOnLineMsg, 1000*5);
	}

	*//**
	 * @param num
	 * @param userArray2
	 * @param groupId
	 * 
	 *    发送消息到所有客户端
	 *//*
	private void sendMsgToAll(int num, String groupId) {
		String sign=getSign("onLineAdmin");
		
		JsonObject object=new JsonObject();
		object.addProperty("GroupId", groupId);
		object.addProperty("Random", RandomUtils.getRandomData(8));
		
		JsonArray array=new JsonArray();
		
		JsonObject txtMsg=new JsonObject();
		txtMsg.addProperty("MsgType", "TIMTextElem");
		JsonObject content1=new JsonObject();
		content1.addProperty("Text", "");
		txtMsg.add("MsgContent", content1);
		
		JsonObject customMsg=new JsonObject();
		customMsg.addProperty("MsgType", "TIMCustomElem");
		JsonObject custom=new JsonObject();
		String lineInfo = JsonUtil.getOnLineInfo(userArray,num);
		custom.addProperty("Desc",lineInfo );
		customMsg.add("MsgContent", custom);
		
		array.add(customMsg);
		object.add("MsgBody", array);
		LoginUtil.sendPostStr(createUrl(baseUrl, sign), object.toString(), null);
	}
	
	
	*//**
	 * @param base
	 * @param sign
	 * @return 拼接腾讯云URL
	 *//*
	private String createUrl(String base, String sign) {
		StringBuffer buffer = new StringBuffer(base);
		buffer.append("?usersig=" + sign + "&");
		buffer.append("identifier=onLineAdmin" + "&");
		buffer.append("sdkappid="+appId+"&");
		buffer.append("random=" + RandomUtils.getRandomData(8) + "&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}
	
	
	*//**
	 * @return 获取管理员sign
	 *//*
	private String getSign(String a) {
		param.clear();
		param.put("username", a);
		String signResult = LoginUtil.sendHttpPost(GET_SIGN, param);
		JSONObject object = new JSONObject(signResult);
		boolean isCreate = object.optBoolean("Result");
		if(isCreate) {
			return object.optString("UserSig");
		}
		return null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	
	public  class SendOnLineMsg extends TimerTask{
		private int num;
		private String groupId;

		public SendOnLineMsg(int num,String groupId) {
			this.num=num;
			this.groupId=groupId;
		}

		@Override
		public void run() {
			sendMsgToAll(num,groupId);
		}
		
	}

}
*/