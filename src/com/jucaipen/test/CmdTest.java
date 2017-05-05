package com.jucaipen.test;
import com.jucaipen.utils.CacheUtils;
public class CmdTest {
	public static void main(String[] args) throws Exception {
		CacheUtils utils=new CacheUtils("cache_default");
		utils.addToCache("name", "≈÷¥Û¡≥");
		System.out.println("c==="+utils.getCacheElement("name"));
		
	}
	public static int ran(int min,int max){
	    // Random random=new Random();
		// return random.nextInt(max)%(max-min+1) + min;
		return (int)((max-min)*Math.random()+min);
	}
	
	public static boolean One(){
		System.out.println("One");
		return false;
	}
	
	public static boolean Two(){
		System.out.println("Two");
		return true;
	}
	
}
