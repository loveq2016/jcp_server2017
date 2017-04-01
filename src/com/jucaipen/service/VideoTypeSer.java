package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoTypeDao;
import com.jucaipen.daoimp.VideoTypeImp;
import com.jucaipen.model.VideoType;

public class VideoTypeSer {
	/**
	 * @return 获取所有视频分类信息
	 */
	public static List<VideoType> findAll() {
		VideoTypeDao dao = new VideoTypeImp();
		return dao.findAll();
	}

	/**
	 * @param id
	 * @return 根据id 查询分类信息
	 */
	public static VideoType findTypeById(int id) {
		VideoTypeDao dao = new VideoTypeImp();
		return dao.findTypeById(id);
	}
}
