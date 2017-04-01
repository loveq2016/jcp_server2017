package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoColnumnDao;
import com.jucaipen.daoimp.VideoColumnImp;
import com.jucaipen.model.VideoColumn;

/**
 * @author Administrator
 *
 *  直播栏目
 */
public class VideoColumnSer {

	/**
	 * @param id
	 * @return 根据id获取栏目信息
	 */
	public static VideoColumn findVideoColumnById(int id) {
		VideoColnumnDao dao=new VideoColumnImp();
		return dao.findVideoColumnById(id);
	}

	/**
	 * @return 获取所有的直播栏目信息
	 */
	public static List<VideoColumn> findAllColumn() {
		VideoColnumnDao dao=new VideoColumnImp();
		return dao.findAllColumn();
	}

}
