package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoClassDao;
import com.jucaipen.daoimp.VideoClassImp;
import com.jucaipen.model.VideoClass;

public class VideoClassSer {

	public static List<VideoClass> findAll() {
		VideoClassDao dao=new VideoClassImp();
		return dao.findAll();
	}

	public static VideoClass findClassById(int id) {
		VideoClassDao dao=new VideoClassImp();
		return dao.findClassById(id);
	}

	public static List<VideoClass> findClassByPid(int pId) {
		VideoClassDao dao=new VideoClassImp();
		return dao.findClassByPid(pId);
	}

}
