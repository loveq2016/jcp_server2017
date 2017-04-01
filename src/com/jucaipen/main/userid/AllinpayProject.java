package com.jucaipen.main.userid;

import java.security.KeyPair;

import org.json.JSONException;
import org.json.JSONObject;

public class AllinpayProject {

	public static String url_text = "http://ceshi.allinpay.com/usercenter/merchant/UserInfo/reg.do";
	public static String url_real="https://service.allinpay.com/usercenter/merchant/UserInfo/reg.do";
	public final static String signType = "1";// 一期的这个字段是1
	public static String merchantId = "100020091218888";
	public static String partnerUserId = "008310107420024";
	public static String signMsg = "";

	public static String rootPath = ProjectMain.class.getResource("/").getFile().toString();
	
	public static void allinpayRegister(String merchantId, String partnerUserId) {
		AllinpayProject.merchantId = merchantId;
		AllinpayProject.partnerUserId = partnerUserId;
		String result = "";
		String src = getSignSrc();
		
		System.out.println("源串："+src);
		// 取得根目录路径
		
		rootPath = rootPath.substring(1, rootPath.length() - 4)
				+ "libs/testMemberKey.keystore";

		KeyPair kp = RegisterUtils.getKeyFromKeyStore(rootPath, "testMemberKey",
				"testMemberKey", "testMemberKey");
		byte[] signBytes = CertSignUtil.signByPriKey(kp.getPrivate(),
				src.getBytes(), Constants.SHA1_WITH_RSA);
		/**
		 * 针对商户做的方法
		 */
		
		// 签名串转十六进制
		signMsg = CertSignUtil.bytes2HexString(signBytes);
		result = getSignSrcAndSignMsg();
		
		System.out.print("经过加密后获得报文:"+result+"\n");
		String callBack = HttpRequest.sendGet(url_text, result);
		System.out.print(callBack+"\n");
		result = getUserIdByJson(callBack);
		System.out.print(result+"\n");
	}

	public static void allinpayRegisterProduct(String merchantId, String partnerUserId) {
		AllinpayProject.merchantId = merchantId;
		AllinpayProject.partnerUserId = partnerUserId;
		String result = "";
		String src = getSignSrc();
		// 取得根目录路径
		rootPath = rootPath.substring(1, rootPath.length() - 4)
				+ "libs/testMemberKey.keystore";
		KeyPair kp = RegisterUtils.getKeyFromKeyStore(rootPath, "testMemberKey",
				"testMemberKey", "testMemberKey");
		byte[] signBytes = CertSignUtil.signByPriKey(kp.getPrivate(),
				src.getBytes(), Constants.SHA1_WITH_RSA);
		// 签名串转十六进制
		signMsg = CertSignUtil.bytes2HexString(signBytes);
		result = getSignSrcAndSignMsg();
		System.out.print(result+"\n");
		String callBack = HttpRequest.sendGet(url_real, result);
		System.out.print(callBack+"\n");
		result = getUserIdByJson(callBack);
		System.out.print("生产编号："+result+"\n");
	}

	
	
	public static void allinpayRegisterProductMoreParams(String merchantId, String partnerUserId,
			String keystoreName,String PassWard,String alias) {
		AllinpayProject.merchantId = merchantId;
		AllinpayProject.partnerUserId = partnerUserId;
		String result = "";
		String src = getSignSrc();
		// 取得根目录路径
		String rootPath = ProjectMain.class.getResource("/").getFile()
				.toString();
		rootPath = rootPath.substring(1, rootPath.length() - 4)
				+ "libs/"+keystoreName;

		KeyPair kp = RegisterUtils.getKeyFromKeyStore(rootPath, PassWard,
				PassWard, alias);
		byte[] signBytes = CertSignUtil.signByPriKey(kp.getPrivate(),
				src.getBytes(), Constants.SHA1_WITH_RSA);
		// 签名串转十六进制
		signMsg = CertSignUtil.bytes2HexString(signBytes);
		result = getSignSrcAndSignMsg();
		System.out.print(result+"\n");
		String callBack = HttpRequest.sendGet(url_real, result);//
		System.out.print(callBack+"\n");
		result = getUserIdByJson(callBack);
		System.out.print(result+"\n");
	}

	
	
	public static String getSignSrc() {
		String result = "";
		result = "&signType=" + signType + "&merchantId=" + merchantId
				+ "&partnerUserId=" + partnerUserId + "&";
		return result;
	}

	// 通过Md5获得源串
	public static String getSignSrcAndSignMsg() {
		String result = "";
		result = "signType=" + signType + "&merchantId=" + merchantId
				+ "&partnerUserId=" + partnerUserId + "&signMsg=" + signMsg
				+ "";
		return result;
	}

	public static String getUserIdByJson(String return_msg) {
		String result = "";
		JSONObject object;
		try {
			object = new JSONObject(return_msg);
			result = object.optString("userId");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;

	}

	public String getKeystorePath(String KeystoreName)
	{
		String result="";
		result = rootPath.substring(1, rootPath.length() - 4)
				+ "libs/"+KeystoreName;
		return result;
	}
	/**
	 * 测试新的公私钥对
	 */
	
	
	
	
}
