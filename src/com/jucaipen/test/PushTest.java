package com.jucaipen.test;
import com.jucaipen.utils.JPushUtils;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
public class PushTest {

	public static void main(String[] args) {
		JPushClient client = JPushUtils.getJPush();
		AliasDeviceListResult res;
		try {
			res = client.getAliasDeviceList("6750", "ios");
			System.out.println(res.toString());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		
	}
}
