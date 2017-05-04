package com.jucaipen.utils;

import java.util.Random;

/**
 * @author Administrator
 *
 *
 *  产生随机数
 */
public class RandomUtils {
	private static Random random;
	
	
	public static int getRandomData(int max,int min){
		if(random==null){
			random=new Random();
		}
		return  random.nextInt(max)+1;
	}
	
	
	public static String getRandomData(int count){
		StringBuffer buffer=new StringBuffer();
		if(random==null){
			random=new Random();
		}
		for(int i=0;i<count;i++){
			int r=random.nextInt(10);
			buffer.append(r);
		}
		return buffer.toString();
	}
	
	
	    public static String getCharAndNumr(int length) {
	        String val = "";
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	            // 输出字母还是数字
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
	            // 字符串
	            if ("char".equalsIgnoreCase(charOrNum)) {
	                // 取得大写字母还是小写字母
	                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
	                val += (char) (choice + random.nextInt(26));
	            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
	                val += String.valueOf(random.nextInt(10));
	            }
	        }
	        return val;
	    }
	
}
