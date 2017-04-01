package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Marker;

/**
 * @author Administrator
 *
 *   打赏
 */
public interface MarkerDao {
	
	/**
	 * @return  获取所有打赏信息
	 */
	public List<Marker> findAll();
	
	/**
	 * @param id
	 * @return 通过id获取打赏信息
	 */
	public Marker findMarkerById(int id);
	
	/**
	 * @param uId
	 * @param page
	 * @return 通过用户id获取打赏记录
	 */
	public List<Marker> findMarkerByUserId(int uId,int page);
	
	/**
	 * @param type
	 * @param userId
	 * @return 通过用户id和分类获取打赏信息
	 */
	public List<Marker> findMarkerByUserIdAndType(int type,int userId,int page);
	/**
	 * @param logId
	 * @param top
	 * @return  根据日志获取最近几条打赏信息
	 */
	public List<Marker> findTopMarkerByLogId(int logId ,int top);
	
	/**
	 * @param marker
	 * @return 添加打赏信息
	 */
	public int addMarker(Marker marker);
	
	/**
	 * @param tId
	 * @return  获取讲师打赏的信息    获取观点打赏信息
	 */
	public List<Marker> findMarkerByTeacherId(int tId,int type);

}
