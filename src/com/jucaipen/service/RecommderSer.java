package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.RecommderDao;
import com.jucaipen.daoimp.RecommderImp;
import com.jucaipen.model.Recommder;

/**
 * @author Administrator
 *
 *  推荐用户
 */
public class RecommderSer {

	/**
	 * @param id
	 * @return  根据id获取推荐信息
	 */
	public static Recommder findRecommderById(int id) {
		RecommderDao dao=new RecommderImp();
		return dao.findRecommderById(id);
	}

	/**
	 * @param parentId
	 * @param page
	 * @return 获取我推荐的信息
	 */
	public static List<Recommder> findRecommderByParentId(int parentId, int page) {
		RecommderDao dao=new RecommderImp();
		return dao.findRecommderByParentId(parentId, page);
	}

	/**
	 * @param recommder
	 * @return 添加推荐信息
	 */
	public static int addRecommder(Recommder recommder) {
		RecommderDao dao=new RecommderImp();
		return dao.addRecommder(recommder);
	}

}
