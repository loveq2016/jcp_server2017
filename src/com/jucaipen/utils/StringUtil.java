package com.jucaipen.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtil {
	public static final String sendPhoneAccount="jcpxxk";
	public static final String sendPhonePwd="Tch456789";
	public static final String isVaild="访问无效";
	/**
	 *    演播室 m3u8格式视频地址
	 */
	public static final String playUrl_MU="http://hls-w.quklive.com/live/w1469002576632934/playlist.m3u8";
	/**
	 *    演播室  flv格式视频地址
	 */
	public static final String  playUrl_flv="http://hdl-w.quklive.com/live/w1469002576632934.flv";
	public static final String play_IOS="http://recordcdn.quklive.com/upload/vod/user1445329249621940/1468907345907846/2/video.m3u8";
	/**
	 * @param string
	 * @return 判断邮箱正确性
	 */
	public static boolean isMail(String string) {
		if (null != string) {
			if (string
					.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection<?>) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map<?, ?>) obj).isEmpty();
		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		
		return false;
	}
	
	/**
	 * @param card
	 * @return  判断是否是身份证
	 */
	public static boolean isIdCard(String card){
		if(isNotNull(card)){
			return EditCheckUtil.IDCardValidate(card);
		}else{
			return false;
		}
	}
	
	
	/**
	 * @param msg
	 * @param type
	 * @return   图文解析
	 */
	public static String createMsg(String msg,int type){
		if(type==0){
			return "<img src='"+msg+"'>";
		}else{
			return msg;
		}
	}
	
	
	/**
	 * @param url
	 * @return  http url 转换为https    
	 */
	public static  String changeHttps(String url){
		StringBuffer newUrl=new StringBuffer();
		if(url==null||"".equals(url)){
			return url;
		}
		if(url.startsWith("http")){
			String[] urlStr = url.split(":");
			for(int i=0;i<urlStr.length;i++){
				if(i==0){
					newUrl.append(urlStr[i]+"s:");
				}else{
					newUrl.append(urlStr[i]);
				}
			}
		}
		return newUrl.toString();
	}
	
	  
	/**
	 * @param bank
	 * @return  判断是否是银行卡号   
	 * 
	 *   6228 4816 9872 9890 079
	 */
	public static boolean isBankCard(String bank){
		if(isNotNull(bank)){
			return EditCheckUtil.checkBankCard(bank);
		}else{
			return false;
		}
	}
	/**
	 * 判断手机号码的正确性
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNumber(String mobiles) {
		if (null != mobiles) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			return m.matches();
		} else {
			return false;
		}
	}
	
	/**
	 * @param password
	 * @return 密码长度是否在6-23之间
	 */
	public static boolean isPassword(String password) {
		if (password!=null&&password.length() >= 6 && password.length() <= 23) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param nikName
	 * @return 判断用户名长度是否在1-9之间
	 */
	public static boolean isUserName(String nikName) {
		if (nikName.trim().length() > 1 && nikName.trim().length() < 20) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为 null 或者为空字符串
	 * 
	 * @param string
	 * @return false if null || ""
	 */
	public static boolean isNotNull(String string) {
		if (string == null || "".equals(string)) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 字符串长度补齐,方便打印时对齐,美化打印页面,不过中文计算好像有点问题
	 * 
	 * @param strs
	 *            源字符
	 * @param len
	 *            指定字符长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String Fix_String_Lenth(int type, String strs, int len) {
		String strtemp = strs;
		switch (type) {
		case 0:
			while (strtemp.length() < len * "我".length()) {
				strtemp += " ";
			}
			break;
		case 1:
			while (strtemp.length() < len) {
				strtemp += " ";
			}
			break;
		default:

			break;
		}
		return strtemp;
	}
	
	 /**
	 * @param html
	 * @return  过滤HTML 标签
	 */
	public static String clearHTMLCode(String html)
     {
		 String script="<script[\\s\\S]+</script *>";
		 String href=" href *= *[\\s\\S]*script *:";
		 String action=" no[\\s\\S]*=";
		 String ifream="<iframe[\\s\\S]+</iframe *>";
		 String frameset="<frameset[\\s\\S]+</frameset *>";
		 String fm="\\<img[^\\>]+\\>";
		 String p="</p>";
		 String ps="<p>";
		 String n="<[^>]*>";
         html = html.replaceAll(script, ""); //过滤<script></script>标记 
         html = html.replaceAll(href, ""); //过滤href=javascript: (<A>) 属性 
         html =  html.replaceAll(action, " _disibledevent="); //过滤其它控件的on...事件 
         html =  html.replaceAll(ifream, ""); //过滤iframe 
         html =  html.replaceAll(frameset, ""); //过滤frameset 
         html =  html.replaceAll(fm, ""); //过滤frameset 
         html =  html.replaceAll(p, ""); //过滤frameset 
         html =  html.replaceAll(ps, ""); //过滤frameset 
         html =  html.replaceAll(n, "");
         html =  html.replaceAll(" ", "");
         html =  html.replaceAll("</strong>", "");
         html = html.replaceAll("<strong>", "");
         return html;
     }
	
	@SuppressWarnings("deprecation")
	public static String getTime(String date){
		try {
			if(date!=null&&date.contains("/")){
				date=date.replaceAll("/", "-");
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d=sdf.parse(date);
			int year=d.getYear()+1900;
			int month=d.getMonth()+1;
			int day=d.getDate();
			int hour=d.getHours();
			int minte=d.getMinutes();
			int seconds=d.getSeconds();
			String monthStr;
			if(month<10){
				monthStr = "0" + month;
			}else {
				monthStr = month + "";
			}
			String dayStr;
            if (day < 10) {
            	dayStr = "0" + day;
            } else {
            	dayStr = day + "";
            }
			
			 String hourStr;
	            if (hour < 10) {
	                hourStr = "0" + hour;
	            } else {
	                hourStr = hour + "";
	            }
	            String mintStr;
	            if (minte < 10) {
	                mintStr = "0" + minte;
	            } else {
	                mintStr = minte + "";
	            }

	            String secondStr;
	            if (seconds < 10) {
	                secondStr = "0" + seconds;
	            } else {
	                secondStr = seconds + "";
	            }
			return year+"-"+monthStr+"-"+dayStr+" "+hourStr+":"+mintStr+":"+secondStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * @param str
	 * @return    过滤短信中的字符串
	 */
	public static String replaceStr(String str){
		String newStr;
		newStr=str.replaceAll("&nbsp;", " ");
		newStr=newStr.replaceAll("<br />", "");
		newStr=newStr.replaceAll("</p>", "");
		newStr=newStr.replaceAll("<p>", "");
		newStr=newStr.replace("{UserName}", "");
		return newStr;
	}
	
	public static String spliteStr(String old,int count){
		if(old.length()<=count){
			return old;
		}
		return old.substring(0, count);
	}
	


}
