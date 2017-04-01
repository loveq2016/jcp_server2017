package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.MailMessageDao;
import com.jucaipen.daoimp.MailMessageImp;
import com.jucaipen.model.MailMessage;

public class MailMessageSer{

	/**
	 * @param mailMessage
	 * @return  添加邮件
	 */
	public static int insertMessage(MailMessage mailMessage) {
		MailMessageDao dao=new MailMessageImp();
		return dao.insertMessage(mailMessage);
	}

	/**
	 * @param id
	 * @param msg
	 * @return  根据  id 修改邮件状态
	 */
	public static int upDateMessageType(int id,MailMessage msg) {
		MailMessageDao dao=new MailMessageImp();
		return dao.upDateMessageType(id,msg);
	}

	/**
	 * @param uId
	 * @param count
	 * @return   根据用户id获取最近收到的Email
	 */
	public static List<MailMessage> findMailMessageByLastCount(int uId, int count) {
		MailMessageDao dao=new MailMessageImp();
		return dao.findMailMessageByLastCount(uId, count);
	}

	/**
	 * @param email
	 * @return  根据Email获取邮件
	 */
	public static List<MailMessage> findMailMessageByEmail(String email) {
		MailMessageDao dao=new MailMessageImp();
		return dao.findMailMessageByEmail(email);
	}

	/**
	 * @param uId
	 * @return  根据用户id获取邮件
	 */
	public static List<MailMessage> findMailMessageByUserId(int uId) {
		MailMessageDao dao=new MailMessageImp();
		return dao.findMailMessageByUserId(uId);
	}

}
