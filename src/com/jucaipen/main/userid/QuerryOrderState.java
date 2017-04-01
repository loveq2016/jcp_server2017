package com.jucaipen.main.userid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jucaipen.utils.LoginUtil;

/**
 * @author Administrator
 * 
 *         »ñÈ¡¶©µ¥×´Ì¬
 */
public class QuerryOrderState {
	public static String orderStatePath = "https://cashier.allinpay.com/gateway/index.do";
	private static Map<String, String> param = new HashMap<String, String>();
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private static int signType = 0;
	private static String merchantId = "008310189990108";
	private static String orderNo = "616091917143368353"; 
	private static String orderDatetime = "20160919171452";
	private static String queryDatetime = sdf.format(new Date());
	private static String version = "v1.5";
	private static String key = "1234567890";

	public static void main(String[] args) {
		param.clear();
		param.put("version", version);
		param.put("signType", signType + "");
		param.put("merchantId", merchantId);
		param.put("orderNo", orderNo);
		param.put("orderDatetime", orderDatetime);
		param.put("queryDatetime", queryDatetime);
		String src = getSignSrc();
		String signMsg = SunMd5.md5(src).trim();
		param.put("signMsg", signMsg);
		String res = LoginUtil.sendHttpGet(orderStatePath, param);
		System.out.println("res:" + res);
		
		
		//payDatetime=&userName=&credentialsType=&pan=&txOrgId=&ext1=%3CUSER%3E201609191778561%3C%2FUSER%3E&payAmount=200&returnDatetime=20160919171847&credentialsNo=&issuerId=&signMsg=E5C06188E63A74C0BCB3E0346B7F6FD1&payType=33&language=1&errorCode=&merchantId=008310189990108&orderDatetime=&version=v1.0&orderNo=616091917143368353&ext2=&signType=0&orderAmount=200&extTL=&paymentOrderId=201609191714423761&payResult=1&

		
		
	}

	public static String getSignSrc() {
		String result = "";
		result = "merchantId="+merchantId+"&version="+ version+"&signType="+signType 
				+ "&orderNo=" + orderNo + "&orderDatetime="
				+ orderDatetime + "&queryDatetime="
				+ queryDatetime+"&key="+key;
		System.out.println(result);
		return result;
	}

}
