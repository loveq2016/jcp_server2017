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
	
	

}
