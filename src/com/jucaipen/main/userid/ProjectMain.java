package com.jucaipen.main.userid;

public class ProjectMain {

	public static void main(String[] args) {

		/**
		 * 008310107420099 MD5测试: 008310107420099 这个是新移动支付注册:测试的商户号
		 * 008310107630044 这个是Md5用例 商户在二期平台测试用例 第一个参数是：测试商户号: 第三个参数是对应
		 * MD5key：（如果测试不稳定，那就试试下面生产的方法）
		 */

		SunMd5.TextRegister("008310107420099", "0083150026", "1234567890");

		/**
		 * MD5生产: 008310107420054 这个是新移动支付注册:生产的商户号 这个是Md5用例 商户在二期平台生产用例
		 * 第一个参数是：生产商户号: 第三个参数是 MD5key： 生产与测试的主要区别在于访问的路由不同。
		 */

		// 20029000001501304
		// 200290000015013

		/*
		 * String resultsecond = SunMd5.allinpayRegister("008310189990108",
		 * "200290000015013", "1234567890"); System.out.print(resultsecond);
		 */

	}

}
