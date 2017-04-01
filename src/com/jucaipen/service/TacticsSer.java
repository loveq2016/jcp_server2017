package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TacticsDao;
import com.jucaipen.daoimp.TacticsImp;
import com.jucaipen.model.Tactics;

/**
 * @author Administrator
 *
 *  战法
 */
public class TacticsSer{

	/**
	 * @param page
	 * @return 获取全部战法信息
	 */
	public static List<Tactics> findAll(int page) {
		TacticsDao dao=new TacticsImp();
		return dao.findAll(page);
	}

	/**
	 * @param id
	 * @return 根据id获取战法信息
	 */
	public static Tactics findTacticsById(int id) {
		TacticsDao dao=new TacticsImp();
		return dao.findTacticsById(id);
	}

	/**
	 * @param tId
	 * @param page
	 * @return 根据讲师id获取战法信息  分页
	 */
	public static List<Tactics> findTacticsByTeacherId(int tId, int page) {
		TacticsDao dao=new TacticsImp();
		return dao.findTacticsByTeacherId(tId, page);
	}
	
	/**
	 * @param tId
	 * @return 根据讲师id获取战法信息  无分页
	 */
	public static List<Tactics> findTacticsByTeacherId(int tId) {
		TacticsDao dao=new TacticsImp();
		return dao.findTacticsByTeacherId(tId);
	}

}
