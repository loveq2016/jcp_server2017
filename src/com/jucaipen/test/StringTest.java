package com.jucaipen.test;
import java.math.BigDecimal;

public class StringTest {
	public static void main(String[] args) {
		/*String time = TimeUtils.format(new Date(), "yyyyMMddHHmmssSSSS");
		System.out.println("time:" + time);*/
		BigDecimal decimal=new BigDecimal("12");
		decimal=decimal.add(new BigDecimal("10"));
		System.out.println(decimal.divide(new BigDecimal("2")));
	}
	
	/*public boolean call(Integer t){
		System.out.println("call:"+t);
		return true;
	}*/
	
	
}
