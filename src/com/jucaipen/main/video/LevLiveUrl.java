package com.jucaipen.main.video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.jucaipen.model.LevBean;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author Administrator
 *
 *  获取乐视视频播放参数
 */
public class LevLiveUrl extends HttpServlet {
	private static final long serialVersionUID = -4265030426313381260L;
	private String result;
	private static Map<String, String> param=new HashMap<String, String>();
	private static final String LEVT_PATH="http://www.jucaipen.com/ashx/AndroidUser.ashx?action=VideoDetail";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		PrintWriter out = response.getWriter();
		String videoId=request.getParameter("videoId");
		if(StringUtil.isNotNull(videoId)&&StringUtil.isInteger(videoId)){
			int vId=Integer.parseInt(videoId);
			result=initVideoParam(vId);
		}else{
			result=JsonUtil.getRetMsg(1,"videoId 参数异常" );
		}
		out.print(result);
		out.flush();
		out.close();
	}
	private static String initVideoParam(int vId) {
		param.clear();
		param.put("id",vId+"");
		String res = LoginUtil.sendHttpPost(LEVT_PATH, param);
		JSONObject object=new JSONObject(res);
		boolean Result=object.getBoolean("Result");
		String Msg=object.getString("Msg");
		if(Result){
			String vu=object.getString("vu");
			String uu=object.getString("uu");
			LevBean lev=new LevBean();
			lev.setUid(uu);
			lev.setVid(vu);
			return JsonUtil.getLevParament(lev);
		}
		return JsonUtil.getRetMsg(3, Msg);
	}

}
