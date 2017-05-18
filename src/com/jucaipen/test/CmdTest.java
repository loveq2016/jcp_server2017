package com.jucaipen.test;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
public class CmdTest {
	public static void main(String[] args) throws Exception {
		CacheUtils utils=new CacheUtils(Constant.DEFAULT_CACHE);
		FamousTeacher user=new FamousTeacher();
		user.setNickName("≈÷¥Û¡≥");
		utils.addToCache("name", "llll");
		//FamousTeacher u=(FamousTeacher) utils.getCache().get("name").getObjectValue();
		System.out.println("c==="+utils.getCache().get("name").getValue());
		
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
