package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TextLive;

public interface TxtLiveDao {
	
	/**
	 * @param textLive
	 * @return   添加文字直播信息
	 */
	public int insertTxtLive(TextLive textLive);
	
	/**
	 * @param condition
	 * @return  获取文字直播总页数
	 */
	public int findTotlePage(String condition);

	/**
	 * @param id
	 * @return  根据id获取直播信息
	 */
	public TextLive findTextLiveById(int id);
	/**
	 * @return  获取全部直播信息
	 */
	public List<TextLive>findAllTextLive(int page);
	/**
	 * @return   获取全部最新推送的直播信息
	 */
	public List<TextLive> findAllNewTextLivesByPush();
	
	/**
	 * @param teacherId
	 * @return  根据讲师id获取直播信息
	 */
	public List<TextLive> findTextLiveByTeacherId(int teacherId,int page);
	
	/**
	 * @param isEnd
	 * @return   根据是否结束获取直播信息
	 */
	public List<TextLive> findTextLiveByIsEnd(int isEnd);
	
	/**
	 * @param teacherId
	 * @param count
	 * @return   获取讲师最近的几次直播
	 */
	public List<TextLive> findTxtLiveByTeacherIdAndLast(int teacherId,int count);
	/**
	 * @param lastId
	 * @return   通过当前直播id，获取最近的直播信息
	 */
	public List<TextLive> findNewLiveByLastId(int lastId);
	/**
	 * @param count
	 * @return   获取最近几条要推送的消息
	 */
	public List<TextLive> findLastPushLive(int count);
	
	public TextLive findHitsAndGoods(int id);
	
	public int addGoods(int id,int goods);
	
	//获取前一天的文字直播信息
	public List<TextLive> findLastDateLive(String date);
	
	/**
	 * @param hits
	 * @param id
	 * @return  更新点击数
	 */
	public int addHits(int hits,int id,int xnHits);
}
