package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TxtMsgDao;
import com.jucaipen.daoimp.TxtMsgImp;
import com.jucaipen.model.TxtLiveMsg;

public class TxtMsgSer {
	

	/**
	 * @param count
	 * @param liveId
	 * @param isCheck
	 * @return  读取最近的十条聊天记录
	 */
	public static List<TxtLiveMsg> findLastTxtMsg(int count, int liveId,
			boolean isCheck,int msgType) {
		TxtMsgDao dao=new TxtMsgImp();
		return dao.findLastTxtMsg(count, liveId, isCheck,msgType);
	}

	/**
	 * @param maxId
	 * @param liveId
	 * @param isCheck
	 * @return  获取最新的聊天信息
	 */
	public static List<TxtLiveMsg> findTxtMsgByMaxId(int maxId, int liveId,
			boolean isCheck,int msgType) {
		TxtMsgDao dao=new TxtMsgImp();
		return dao.findTxtMsgByMaxId(maxId, liveId, isCheck,msgType);
	}

	/**
	 * @param msg
	 * @return  添加聊天信息
	 */
	public static int addMsg(TxtLiveMsg msg) {
		TxtMsgDao dao=new TxtMsgImp();
		return dao.addMsg(msg);
	}

}
