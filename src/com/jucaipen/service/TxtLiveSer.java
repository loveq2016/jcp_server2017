package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TxtLiveDao;
import com.jucaipen.daoimp.TextLiveImp;
import com.jucaipen.model.TextLive;

public class TxtLiveSer{
	
	/**
	 * @param textLive
	 * @return  添加文字直播
	 */
	public static int insertTextLive(TextLive textLive){
		TxtLiveDao dao=new TextLiveImp();
		return dao.insertTxtLive(textLive);
	}
	
	public static TextLive findHitsAndGoods(int id){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findHitsAndGoods(id);
	}
	
	
	public static List<TextLive> findLastLive(String date){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findLastDateLive(date);
	}
	
	public static int addGoods(int id, int goods){
		TxtLiveDao dao=new TextLiveImp();
		return dao.addGoods(id, goods);
	}
	
	/**
	 * @param lastId
	 * @param page
	 * @return    根据上次的文字直播id获取最近的文字直播信息
	 */
	public static List<TextLive> findNewLiveByLastId(int lastId){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findNewLiveByLastId(lastId);
	}

	/**
	 * @param id
	 * @return  根据id获取文字直播详细内容
	 */
	public static TextLive findTextLiveById(int id) {
		TxtLiveDao dao=new TextLiveImp();
		return dao.findTextLiveById(id);
	}

	/**
	 * @return  获取所有的文本直播
	 */
	public static List<TextLive> findAllTextLive(int page) {
		TxtLiveDao dao=new TextLiveImp();
		return dao.findAllTextLive(page);
	}

	/**
	 * @param teacherId
	 * @return  根据讲师获取文字直播
	 */
	public static List<TextLive> findTextLiveByTeacherId(int teacherId,int page) {
		TxtLiveDao dao=new TextLiveImp();
		return dao.findTextLiveByTeacherId(teacherId,page);
	}
	

	/**
	 * @param isEnd
	 * @return   根据文字直播状态获取文字直播内容
	 */
	public static List<TextLive> findTextLiveByIsEnd(int isEnd) {
		TxtLiveDao dao=new TextLiveImp();
		return dao.findTextLiveByIsEnd(isEnd);
	}
	
	/**
	 * @param teacherId
	 * @param count
	 * @return  获取讲师最近的几条直播信息
	 */
	public static List<TextLive> findTxtLiveByTeacherIdAndLast(int teacherId,int count){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findTxtLiveByTeacherIdAndLast(teacherId, count);
	}
	
	/**
	 * @return  获取所有需要推送的文字直播信息
	 */
	public static List<TextLive> findAllNewTextLivesByPush(){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findAllNewTextLivesByPush();
	}
	
	/**
	 * @param count
	 * @return  获取最近几条需要推送的直播信息
	 */
	public static List<TextLive> findLastPushLive(int count){
		TxtLiveDao dao=new TextLiveImp();
		return dao.findLastPushLive(count);
	}
	
	/**
	 * @param hits
	 * @param id
	 * @return  更新点击数
	 */
	public static int addHits(int hits,int id,int xnHits){
		TxtLiveDao dao=new TextLiveImp();
		return dao.addHits(hits, id,xnHits);
	}

}
