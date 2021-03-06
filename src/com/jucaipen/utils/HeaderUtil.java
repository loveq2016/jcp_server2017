package com.jucaipen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jucaipen.model.ClientOsInfo;
/***
 *   获取访问设备类型    
 */
public class HeaderUtil {
	public static final String OSTYPE_ANDROID="Android";
	public static final String OSTYPE_IOS="Ios";
	public static final String OSTYPE_WP="WINDOWS PHONE";
	public static final String OSTYPE_BLACKBERRY="BLACKBERRY";
	private static boolean isDebug=true;
	/***
	 * pad
	 */
	public static final String DEVICE_TYPE_PAD="Pad";
	/***
	 * 手机
	 */
	public static final String DEVICE_TYPE_PHONE="Phone";
	/**
	 *  手机app 访问
	 */
	public static final int PHONE_APP=0;
	/**
	 *  手机浏览器访问
	 */
	public static final int PHONE_CHAROM=1;
	/**
	 *  浏览器访问
	 */
	public static final int CHAROM=2;

	/***
	 * 校验渠道终端版本号是否合法,eg:0.0.0.3
	 * 
	 * @param clientVersion
	 * @return true-->合法 ;false-->非法
	 */
	public static boolean verifyClientVersion(String clientVersion) {
		boolean result = Pattern.matches("[\\d\\.]+", clientVersion);
		if (result) {
			result = Pattern.matches("^\\d\\.\\d\\.\\d\\.\\d$", clientVersion);
			return result;
		} else {
			return false;
		}
	}
	
	public static int isVaildDevice(ClientOsInfo os,String UA){
		if(isDebug){
			return PHONE_APP;
		}
		if(UA==null||os==null){
			return CHAROM;
		}
		String osType=os.getDeviceType();
		if(StringUtil.isNotNull(osType)){
			String type=osType.toLowerCase();
			if((type.equals("pad")||type.equals("phone"))&&UA.contains("Dalvik")){
				return PHONE_APP;
			}else{
				return PHONE_CHAROM;
			}
		}else{
			return CHAROM;
		}
		
	}
	
	
	/**
	 * 根据useragent和手机厂商查手机型号
	 * 
	 * @param UA
	 * @param vendor
	 * @return
	 */
	public static String getMobModel(String UA, String operatingSystem) {
		if (UA == null) {
			return null;
		}
		// 存放正则表达式
		String rex = "";
		// 苹果产品
		if (operatingSystem.indexOf("IOS") != -1) {
			if (UA.indexOf("IPAD") != -1) {// 判断是否为ipad
				return "IPAD";
			}
			if (UA.indexOf("IPOD") != -1) {// 判断是否为ipod
				return "IPOD";
			}
			if (UA.indexOf("IPONE") != -1) {// 判断是否为ipone
				return "IPONE";
			}
			return "IOS DEVICE";

		}
		// 安卓系统产品
		if (operatingSystem.indexOf("ANDROID") != -1) {
			String re = "BUILD";
			rex = ".*" + ";" + "(.*)" + re;
			Pattern p = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(UA);
			boolean rs = m.find();
			if (rs) {
				return m.group(1);
			}
		}
		return null;
	}

	/**
	 * 判断手机的操作系统 IOS/android/windows phone/BlackBerry
	 * 
	 * @param UA
	 * @return    Dalvik/1.6.0 (Linux; U; Android 4.4.2; PE-TL20 Build/HuaweiPE-TL20)
	 */
	public static ClientOsInfo getMobilOS(String UA) {
		if (UA == null) {
			return null;
		}
		ClientOsInfo osInfo=new  ClientOsInfo();
		UA=UA.toUpperCase();
		// 存放正则表达式
		String rex = "";
		// IOS 判断字符串
		String iosString = " LIKE MAC OS X";
		if (UA.indexOf(iosString) != -1) {
			if(isMatch(UA, "\\([\\s]*iPhone[\\s]*;", Pattern.CASE_INSENSITIVE)){
				osInfo.setDeviceType(DEVICE_TYPE_PHONE);
				osInfo.setMainSys("iPhone");
			}else if(isMatch(UA, "\\([\\s]*iPad[\\s]*;", Pattern.CASE_INSENSITIVE)){
				osInfo.setDeviceType(DEVICE_TYPE_PAD);
				osInfo.setMainSys("iPad");
			}
			rex = ".*" + "[\\s]+(\\d[_\\d]*)" + iosString;
			Pattern p = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(UA);
			boolean rs = m.find();
			if (rs) {
				String osVersion= m.group(1).replace("_", ".");
				osInfo.setVersion(osVersion);     
				osInfo.setOsTypeVersion(OSTYPE_IOS+"_" + osVersion);
				return osInfo;
			}
			osInfo.setOsTypeVersion(OSTYPE_IOS);
			return osInfo;
		}
		// Android 判断
		String androidString = "Android";
		if (UA.indexOf(androidString) != -1) {
			if(isMatch(UA, "\\bMobi", Pattern.CASE_INSENSITIVE)){
				osInfo.setDeviceType(DEVICE_TYPE_PHONE);
				osInfo.setMainSys("Android");
			}else {
				osInfo.setDeviceType(DEVICE_TYPE_PAD);
				osInfo.setMainSys("Pad");
			}
			rex = ".*" + androidString + "[\\s]*(\\d*[\\._\\d]*)";
			Pattern p = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(UA);
			boolean rs = m.find();
			if (rs) {
				String version=m.group(1).replace("_", ".");
				osInfo.setVersion(version);
				osInfo.setOsTypeVersion(OSTYPE_ANDROID+"_" + version);
				return osInfo;
			}
			osInfo.setOsTypeVersion(OSTYPE_ANDROID);
			return osInfo;
		}
		// windows phone 判断
		String wpString = "WINDOWS PHONE";
		if (UA.indexOf(wpString) != -1) {
			rex = ".*" + wpString + "[\\s]*[OS\\s]*([\\d][\\.\\d]*)";
			Pattern p = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(UA);
			boolean rs = m.find();
			if (rs) {
				String version=m.group(1);
				osInfo.setVersion(version);
				osInfo.setOsTypeVersion(OSTYPE_WP+"_" + version);
				return osInfo;
			}
			osInfo.setMainSys("WINDOWS PHONE");
			osInfo.setOsTypeVersion(OSTYPE_WP);
			return osInfo;
		}
		// BlackBerry 黑莓系统判断
		String bbString = "BLACKBERRY";
		if (UA.indexOf(bbString) != -1) {
			rex = ".*" + bbString + "[\\s]*([\\d]*)";
			Pattern p = Pattern.compile(rex, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(UA);
			boolean rs = m.find();
			if (rs) {
				String version=m.group(1);
				osInfo.setVersion(version);
				osInfo.setOsTypeVersion(OSTYPE_BLACKBERRY+"_" + version);
				return osInfo;
			}
			osInfo.setMainSys("BLACKBERRY");
			osInfo.setOsTypeVersion(OSTYPE_BLACKBERRY);
			return osInfo;
		}
		if(UA.contains("LINUX")){//android
			if(isMatch(UA, "\\bMobi", Pattern.CASE_INSENSITIVE)){
				osInfo.setDeviceType(DEVICE_TYPE_PHONE);
				osInfo.setMainSys("ANDROID");
			}else {
				osInfo.setMainSys("Pad");
				osInfo.setDeviceType(DEVICE_TYPE_PAD);
			}
			
			 Pattern p = Pattern.compile("U;\\s*(Adr[\\s]*)?(\\d[\\.\\d]*\\d)[\\s]*;",Pattern.CASE_INSENSITIVE);
		        Matcher m = p.matcher(UA);
		        boolean result = m.find();
		        String find_result = null;
		        if (result)
		        {
		            find_result = m.group(2);
		        }
		        if(StringUtil.isNullOrEmpty(find_result)){
		        	osInfo.setOsTypeVersion(OSTYPE_ANDROID);
		        	return osInfo;
		        }else{
		        	osInfo.setVersion(find_result);
		        	osInfo.setOsTypeVersion(OSTYPE_ANDROID+"_"+find_result);
		        	return osInfo;
		        }
		}
		
		//UCWEB/2.0 (iOS; U; iPh OS 4_3_2; zh-CN; iPh4)
		if(UA.matches(".*((IOS)|(iPAD)).*(IPH).*")){
			if(isMatch(UA, "[\\s]*iPh[\\s]*", Pattern.CASE_INSENSITIVE)){
				osInfo.setDeviceType(DEVICE_TYPE_PHONE);
				osInfo.setMainSys("iPhone");
			}else {
				osInfo.setDeviceType(DEVICE_TYPE_PAD);
				osInfo.setMainSys("iPad");
			}
			 Pattern p = Pattern.compile("U;\\s*(IPH[\\s]*)?(OS[\\s]*)?(\\d[\\._\\d]*\\d)[\\s]*;",Pattern.CASE_INSENSITIVE);
		        Matcher m = p.matcher(UA);
		        boolean result = m.find();
		        String find_result = null;
		        if (result)
		        {
		            find_result = m.group(3);
		        }
		        if(StringUtil.isNullOrEmpty(find_result)){
		        	osInfo.setOsTypeVersion(OSTYPE_IOS);
		        	return osInfo;
		        }else{
		        	String version=find_result.replace("_", ".");
		        	osInfo.setVersion(version);
		        	osInfo.setOsTypeVersion(OSTYPE_IOS+"_"+version);
		        	return osInfo;
		        }
		}
		return osInfo;
	}
	public static boolean isMatch(String source,String regx,int flags){
		Pattern p = Pattern.compile(regx,flags);
        Matcher m = p.matcher(source);
        boolean result = m.find();
        return result;
	}

}
