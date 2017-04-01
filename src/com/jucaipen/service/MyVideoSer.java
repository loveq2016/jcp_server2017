package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.MyVideoDao;
import com.jucaipen.daoimp.MyVideoImp;
import com.jucaipen.model.MyVideo;

public class MyVideoSer {

	/**
	 * @param id
	 * @return 根据id获取我的视频信息
	 */
	public static MyVideo findVideoById(int id) {
		MyVideoDao dao = new MyVideoImp();
		return dao.findVideoById(id);
	}

	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取我的视频信息
	 */
	public static List<MyVideo> findMyVideoByUserId(int userId, int page) {
		MyVideoDao dao = new MyVideoImp();
		return dao.findMyVideoByUserId(userId, page);
	}

	/**
	 * @param video
	 * @return 添加视频信息
	 */
	public static int addMyVideo(MyVideo video) {
		MyVideoDao dao = new MyVideoImp();
		return dao.addMyVideo(video);
	}

	/**
	 * @param id
	 * @return 删除视频信息
	 */
	public static int removeMyVideo(int id) {
		MyVideoDao dao = new MyVideoImp();
		return dao.removeMyVideo(id);
	}

	/**
	 * @param uId
	 * @param vId
	 * @return 获取是否是购买的视频
	 */
	public static MyVideo findIsMyVideo(int uId, int vId) {
		MyVideoDao dao = new MyVideoImp();
		return dao.findIsMyVideo(uId, vId);
	}

	public static int getPurchVideoNum(int id) {
		MyVideoDao dao = new MyVideoImp();
		return dao.getPurchVideoNum(id);
	}

}
