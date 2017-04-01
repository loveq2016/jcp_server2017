package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.SysMessageDao;
import com.jucaipen.daoimp.SysMessageImp;
import com.jucaipen.model.SysMessage;

/**
 * @author Administrator
 * 
 *         系统消息
 */
public class SysMessageSer {

	/**
	 * @param id
	 * @return 根据id获取系统消息
	 */
	public static SysMessage findMessageById(int id) {
		SysMessageDao dao = new SysMessageImp();
		return dao.findMessageById(id);
	}

	/**
	 * @param senderId
	 * @param page
	 * @return 获取我发送的系统消息
	 */
	public static List<SysMessage> findMessageBySenderId(int senderId, int page) {
		SysMessageDao dao = new SysMessageImp();
		return dao.findMessageBySenderId(senderId, page);
	}

	public static List<SysMessage> findMessageByReceiverIdAndMaxId(
			int receiverId, int maxId) {
		SysMessageDao dao = new SysMessageImp();
		return dao.findMessageByReceiverIdAndMaxId(receiverId, maxId);
	}

	public static List<SysMessage> findMessageBySenderIdAndMaxId(int senderId,
			int maxId) {
		SysMessageDao dao = new SysMessageImp();
		return dao.findMessageBySenderIdAndMaxId(senderId, maxId);
	}

	/**
	 * @param receiverId
	 * @param page
	 * @return 获取我接收到的系统消息
	 */
	public static List<SysMessage> findMessageByReceiverId(int receiverId,
			int page) {
		SysMessageDao dao = new SysMessageImp();
		return dao.findMessageByReceiverId(receiverId, page);
	}

}
