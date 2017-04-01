package com.jucaipen.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Administrator
 *
 *  发送邮件
 */
public class EmailUtils {
	private static String HOST = "smtp.163.com";
	private static String PROTOCOL = "smtp";
	private static int PORT = 25;
	private static String FROM="13****1375@163.com";
	private static String PWD="******";

	/**
	 * @param email
	 * @param content
	 * @return  获取session
	 */
	public static Session getsession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", "true");
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
		};
		return Session.getDefaultInstance(props, authenticator);
	}
	
	/**
	 * @param toEmail
	 * @param content
	 *     发送邮件
	 */
	public static String send(String toEmail,String content,String subject){
		//获取session
		Session session=getsession();
		try {
			Message msg=new MimeMessage(session);
			//设置发件地址
			msg.setFrom(new InternetAddress(FROM));
			//设置收件地址
			InternetAddress address[]={new InternetAddress(toEmail)};
			msg.setRecipients(Message.RecipientType.TO, address);
			//设置邮件主题
			msg.setSubject(subject);
			//设置邮件发送时间
			msg.setSentDate(new Date());
			//设置邮件内容
			msg.setContent(content, "text/html;charset=utf-8");
			//发送邮件
			Transport.send(msg);
			return JsonUtil.getRetMsg(0,"邮件发送成功，请注意查收");
		} catch (AddressException e) {
			e.printStackTrace();
			return JsonUtil.getRetMsg(1,"邮件地址有误");
		} catch (MessagingException e) {
			e.printStackTrace();
			return JsonUtil.getRetMsg(2, "发送失败");
		}
		
	}

}
