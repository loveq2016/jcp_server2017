package com.jucaipen.utils;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtils {
	private static String sendChatPath = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=add";
	private static String getTopCount = "http://chat.jucaipen.com/ashx/chat_msg.ashx?action=getTopId";

	/**
	 * @param path
	 * @param id
	 *            topId roomId userId
	 * 
	 *            获取聊天记录
	 */
	public static String sendHttpPost(String path, int... id) {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
			conn.setRequestProperty("accept", "*/");
			conn.setReadTimeout(1000 * 5);
			conn.setConnectTimeout(1000 * 5);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print("topId=" + id[0] + "&roomid=" + id[1] + "&userId="
					+ id[2] + "&isServer=" + id[3]);
			out.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream is = conn.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			byte bs[] = new byte[dis.available()];
			dis.read(bs);
			String data = new String(bs, "UTF-8");
			return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @param msgcontent
	 *            发送消息
	 * @param sendusername
	 *            发送用户昵称
	 * @param SendUserId
	 *            发送用户id
	 * @param roomid
	 *            直播间id
	 * @return 发送聊天消息
	 * 
	 * 
	 * 
	 *         /*发送消息接口：http://chat.jucaipen.com/ashx/chat_msg.ashx?action=add
	 * 
	 *         需要传递参数：msgcontent（消息内容）、sendusername（发送人昵称）、fasongface（发送人头像）、
	 *         SendUserId（发送人ID）、roomid（房间ID）、
	 *         jieshouname（接收人昵称）、jieshouid（接收人ID
	 *         ）、ReceiverNameId（接收人用户名）、sendusernameid（发送人用户名）、
	 *         userLevel（用户产品ID）
	 *         、ReceiveLevel（接收产品ID）、ReceiveManger（接收用户是否为管理员）、SendManager
	 *         （发送人是否为管 理员，1为管理员0为普通用户）、
	 *         ReceiveServiceId（接收信息的服务ID）、SendServiceId
	 *         （发送信息的服务ID）、MessType（消息类型，0为群聊，1为私信）、
	 * 
	 * 
	 * 
	 *         聊天记录接口：http://chat.jucaipen.com/ashx/chat_msg.ashx?action=getlist
	 * 
	 *         需要传递参数：topId（显示最新十条消息）、roomid（房间ID）、userId（用户ID，游客用户ID为0）、
	 *         isServer（0为普通用户或游客，1为管理员或客 服）
	 * 
	 * 
	 * 
	 *         请求topId接口:http://chat.jucaipen.com/ashx/chat_msg.ashx?action=
	 *         getTopId
	 * 
	 *         需要传递参数：userType(用户是否为管理员)、topCount（显示条数）、roomId（房间id）、
	 *         isServer（是否为客服）、
	 * 
	 * 
	 *         表情列表接口：http://chat.jucaipen.com/ashx/addface.ashx
	 */
	public static String sendChatMessage(String msgcontent,
			String sendusername, int SendUserId, int roomid) {
		try {
			URL url = new URL(sendChatPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			String content = URLEncoder.encode(msgcontent, "UTF-8");
			String userName = URLEncoder.encode(sendusername, "UTF-8");
			String param = "msgcontent=" + content + "&sendusername="
					+ userName + "&SendUserId=" + SendUserId + "&roomid="
					+ roomid;
			out.print(param);
			out.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream is = conn.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			byte bs[] = new byte[dis.available()];
			dis.read(bs);
			String data = new String(bs, "UTF-8");
			return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @param isManager
	 * @param roomId
	 * @param isServer
	 * @return 获取TopId
	 * 
	 */
	public static String getChatTopCount(int isManager, int roomId, int isServer) {
		try {
			URL url = new URL(getTopCount);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			String param = "userType=" + isManager + "&topCount=8&roomId="
					+ roomId + "&isServer=" + isServer;
			out.print(param);
			out.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream is = conn.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			byte bs[] = new byte[dis.available()];
			dis.read(bs);
			String data = new String(bs, "UTF-8");
			return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @param url
	 * @return 发送 get 请求
	 */
	public static String sendHttpGet(String url) {
		try {
			URL path = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) path.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream is = conn.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				byte bs[] = new byte[dis.available()];
				dis.read(bs);
				String data = new String(bs, "UTF-8");
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
