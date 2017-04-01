package com.jucaipen.dao;


import java.util.List;

import com.jucaipen.model.VideoColumn;

/**
 * @author Administrator
 *
 *  直播栏目
 */
public interface VideoColnumnDao {
	
	/**
	 * @param id
	 * @return 通过id获取栏目名称
	 */
	public VideoColumn findVideoColumnById(int id);
	
	/**
	 * @return 获取全部的视频栏目
	 */
	public List<VideoColumn> findAllColumn();

}
