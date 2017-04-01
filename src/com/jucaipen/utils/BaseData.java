package com.jucaipen.utils;

/**
 * @author Administrator
 *
 *  用户等级信息检测类
 */
public class BaseData {
	
	//经验值小于2000的，等级为草根股民
	//  >=0    <2000
	public static final String ONE_LEAVEL="草根股民";
	
	//经验值大于等于2000的，等级为股市达人
	//  >=2000      <5000   
	public static final String TWO_LEAVEL="股市达人";
	//经验值大于等于5000的，等级为股市牛人
	//  >=5000      <10000
	public static final String THREE_LEAVEL="股市牛人";
	//经验值大于等于10000的，等级为股市大神
	//  >=10000
	public static final String FOUR_LEAVEL="股市大神";
	
	public static final int ONE_MIN=0;
	
	public static final int ONE_MAX=2000;
	
	
	public static final int TWO_MAX=5000;
	
	public static final int THREE_MAX=10000;
	public static final String ONE_LEAVEL_RULE="1、经验值小于2000的，等级为草根股民";
	public static final String TWO_LEAVEL_RULE="2、经验值大于等于2000的，等级为股市达人";
	public static final String THREE_LEAVEL_RULE="3、经验值大于等于5000的，等级为股市牛人";
	public static final String FOUR_LEAVEL_RULE="4、经验值大于等于10000的，等级为股市大神";
	
	
	/**
	 * @param integeral
	 * @return  返回等级称号
	 */
	public static String getLeavelStr(int integeral){
		if(integeral>=ONE_MIN&&integeral<ONE_MAX){
			return ONE_LEAVEL;
		}else if(integeral>=ONE_MAX&&integeral<TWO_MAX){
			return TWO_LEAVEL;
		}else if(integeral>=TWO_MAX&&integeral<THREE_MAX){
			return THREE_LEAVEL;
		}else{
			return FOUR_LEAVEL;
		}
	}
	
	
	
	/**
	 * @param integeral
	 * @return  返回等级称号
	 */
	public static int getLeavel(int integeral){
		if(integeral>=ONE_MIN&&integeral<ONE_MAX){
			return 1;
		}else if(integeral>=ONE_MAX&&integeral<TWO_MAX){
			return 2;
		}else if(integeral>=TWO_MAX&&integeral<THREE_MAX){
			return 3;
		}else{
			return 4;
		}
	}
	
	
	public static String getLeavelRule(){
		return ONE_LEAVEL_RULE+"\n"+TWO_LEAVEL_RULE+"\n"+THREE_LEAVEL_RULE+"\n"+FOUR_LEAVEL_RULE;
	}
	
}
