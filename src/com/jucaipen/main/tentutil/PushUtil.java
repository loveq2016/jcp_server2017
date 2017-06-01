package com.jucaipen.main.tentutil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.RandomUtils;
public class PushUtil {
	private static String baseUrl="https://console.tim.qq.com/v4/openim/im_push";
	private static Map<String, String> param = new HashMap<String, String>();
	private static final String GET_SIGN = "http://www.jucaipen.com/ashx/AndroidUser.ashx?action=GetUserSig";
	
	public static void pushMsg(String tag){
		StringBuilder builder=new StringBuilder();
		builder.append("{");
		builder.append("\"MsgRandom\": "+Integer.parseInt(RandomUtils.getRandomData(7))+",");
		builder.append("\"Condition\": {");
		builder.append("\"TagsAnd\": [\""+tag+"\",\""+"teacherId"+"\"]");
		builder.append("},");
		builder.append(" \"MsgBody\": [");
		builder.append("{");  
		builder.append("\"MsgType\": \"TIMTextElem\","); 
		builder.append(" \"MsgContent\": {");  
		builder.append(" \"Text\": \"hi, beauty\"");        
		builder.append("  }");     
		builder.append("  }");     
		builder.append("  ]");     
		builder.append("  }"); 
		String result = LoginUtil.sendPostStr(appUrl(getSign("onLineAdmin"), "onLineAdmin"), builder.toString(), null);
		System.out.println("result:"+result);
	}
	
	public static void main(String[] args) {
		pushMsg("1");
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
	
	
	public static String appUrl(String usersig,String identifier){
		StringBuffer buffer=new StringBuffer(baseUrl);
		buffer.append("?usersig="+usersig+"&");
		buffer.append("identifier="+identifier+"&");
		buffer.append("sdkappid=1400028429&");
		buffer.append("random="+RandomUtils.getRandomData(7)+"&");
		buffer.append("contenttype=json");
		return buffer.toString();
	}

}
