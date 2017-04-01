package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.SysMessage;

/**
 * @author Administrator
 *
 *  系统消息
 */
public interface SysMessageDao {
	 /**
	 * @param id
	 * @return 根据id获取系统消息
	 */
	public SysMessage findMessageById(int id);
	
	/**
	 * @param senderId
	 * @return 获取我发送的消息
	 */
	public List<SysMessage> findMessageBySenderId(int senderId,int page);
	
	/**
	 * @param receiverId
	 * @return 获取我接受到的系统消息
	 */
	public List<SysMessage> findMessageByReceiverId(int receiverId,int page);
	
	
	/**
	 * @param senderId
	 * @return 根据最大消息id获取我发送的消息
	 */
	public List<SysMessage> findMessageBySenderIdAndMaxId(int senderId,int maxId);
	
	
	/**
	 * @param receiverId
	 * @return 根据最大消息id获取我接受到的系统消息
	 */
	public List<SysMessage> findMessageByReceiverIdAndMaxId(int receiverId,int maxId);
	

}
