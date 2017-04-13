package com.jucaipen.timetask;
import java.util.TimerTask;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jucaipen.utils.RandomUtils;

public class LiveTask extends TimerTask{
	private String baseUrl="https://console.tim.qq.com/v4/group_open_http_svc/send_group_msg";

	@Override
	public void run() {
		//
		String createUrl = createUrl("eJxljl1PgzAARd-5FYRXjekHHeCbsho6RF3c3PCFMNqNbg5qqRvO7L9PcYkk3tdzbu79smzbdib3z1d5UdQflcnMpxKOfW07wLn8g0pJnuUmw5r-g6JVUossXxqhOwgJIQiAviO5qIxcyrOR862serjhm6zb*O2732XkYT-oK3LVwYSmIRsPFyvVsjBGAVExcxs9uy1rQ*8OcVoGzS5aX0D3cZo8HCave1beJCj0xmw0HwzTp80bjRZmPljP-CLc12lISUtHMpr63st7yXqTRm7F*VAAAXYBxj26E7qRddUJCEACEQY-cayjdQLsvVyZ");
		JsonObject object=new JsonObject();
		object.addProperty("GroupId", "1002");
		object.addProperty("Random", RandomUtils.getRandomData(8));
		
		JsonArray array=new JsonArray();
		object.add("MsgBody", array);
	}

	private String createUrl(String sign) {
		StringBuffer buffer=new StringBuffer(baseUrl);
		buffer.append("?usersig="+sign+"&");
		buffer.append("identifier=admin&");
		buffer.append("sdkappid=1400027389&");
		buffer.append("random="+RandomUtils.getRandomData(8)+"&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}
