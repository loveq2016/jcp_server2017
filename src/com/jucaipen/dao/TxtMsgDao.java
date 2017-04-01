package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TxtLiveMsg;
/**
 * @author Administrator
 *
 *
 *   直播互动
 */
public interface TxtMsgDao {
	/**
	 * @param count
	 * @param liveId
	 * @return  获取指定文字直播聊天最近的几条记录
	 */
	
	public List<TxtLiveMsg> findLastTxtMsg(int count,int liveId,boolean isCheck,int msgType);
	/**
	 * @param maxId
	 * @param liveId
	 * @param isCheck
	 * @return  根据最大的id实时获取最新的聊天信息
	 */
	
	public List<TxtLiveMsg>  findTxtMsgByMaxId(int maxId,int liveId,boolean isCheck,int msgType);
	
	/**
	 * @param msg
	 * @return  添加聊天信息
	 */
	public int addMsg(TxtLiveMsg msg);
	

}
