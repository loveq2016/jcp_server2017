package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MailMessage;

/**
 * @author YLF
 * EMAU
 *    邮件消息
 *
 */
public interface MailMessageDao {
	
	/**
	 * @param count
	 * @return  获取最近的几条邮件信息
	 */
	public List<MailMessage> findMailMessageByLastCount(int uId,int count);
	
	/**
	 * @param email
	 * @return  根据Email 查询邮件信息
	 */
	public List<MailMessage> findMailMessageByEmail(String email);
	
	/**
	 * @param uId
	 * @return  根据用户id查询邮件信息
	 */
	public List<MailMessage> findMailMessageByUserId(int uId);	
	/**
	 * @param mailMessage
	 * @return  发送邮件信息
	 */
	public int insertMessage(MailMessage mailMessage);
	
	
	/**
	 * @param type
	 * @return   改变邮件信息状态
	 */
	public int upDateMessageType(int id,MailMessage msg);

}
