package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.LinkUrlDao;
import com.jucaipen.daoimp.LinkUrlImp;
import com.jucaipen.model.LinkUrl;

/**
 * @author Administrator
 * 连接URL
 */
public class LinkUrlSer  {

	/**
	 * @param id
	 * @return 通过id获取连接URL
	 */
	public  static LinkUrl findUrlById(int id) {
		LinkUrlDao dao=new LinkUrlImp();
		return dao.findUrlById(id);
	}

	/**
	 * @return 获取所有的连接URL
	 */
	public static List<LinkUrl> findAll() {
		LinkUrlDao dao=new LinkUrlImp();
		return dao.findAll();
	}

}
