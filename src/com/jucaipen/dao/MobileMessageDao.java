package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MobileMessage;

/**
 * @author YLF
 * 
 *    手机短信
 *
 */
public interface MobileMessageDao {
	
	/**
	 * @param message
	 * @return  发送短信
	 */
	public int insertMessage(MobileMessage message);
	
	/**
	 * @param tel
	 * @param code
	 * @return  获取手机号发送的对应最近一条验证码       
	 */
	public MobileMessage findIsRegin(String tel,String code);
	
	/**
	 * @param type
	 * @return  根据id改变短信状态  --成功
	 */
	public int upDateMessageType(String msgId,MobileMessage message);
	
	/**
	 * @param msgId
	 * @param message
	 * @return  根据id修改状态  --Fail 失败
	 */
	public int upDateMessageFailType(String msgId,MobileMessage message);
	
	/**
	 * @param telPhone
	 * @return  通过手机号查询短信
	 */
	public List<MobileMessage> findMobileMessgageByMobileNum(String telPhone);
	
	/**
	 * @param count
	 * @param mobile
	 * @return  根据手机号查询最近的短信内容
	 */
	public List<MobileMessage> findMobileMessageByMobileNumLast(int count,String mobile);

}
