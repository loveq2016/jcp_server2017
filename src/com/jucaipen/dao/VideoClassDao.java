package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.VideoClass;

public interface VideoClassDao {
	
	/**
	 * @return  获取全部视频分类信息
	 */
	public List<VideoClass> findAll();
	/**
	 * @param id
	 * @return   根据id 获取视频分类信息
	 */
	public VideoClass findClassById(int id);
	
	/**
	 * @param pId
	 * @return  根据父级分类获取信息
	 */
	public List<VideoClass>  findClassByPid(int pId);
	
	

}
