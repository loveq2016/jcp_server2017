package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.ContributeDao;
import com.jucaipen.daoimp.ContributeImp;
import com.jucaipen.model.Contribute;

/**
 * @author Administrator
 *
 * 贡献
 */
public class ContributeSer{

	/**
	 * @param id
	 * @return 根据id获取贡献信息
	 */
	public static  Contribute findContributeById(int id) {
		ContributeDao dao=new ContributeImp();
		return dao.findContributeById(id);
	}

	/**
	 * @param uId
	 * @return  根据用户id获取贡献榜
	 */
	public static List<Contribute> findContributeByUid(int uId) {
		ContributeDao dao=new ContributeImp();
		return dao.findContributeByUid(uId);
	}

	/**
	 * @param uId
	 * @param tId
	 * @return 获取用户对某个讲师的贡献
	 */
	public static List<Contribute> findContributeByUidAndTid(int uId, int tId) {
		ContributeDao dao=new ContributeImp();
		return dao.findContributeByUidAndTid(uId, tId);
	}

	/**
	 * @param uId
	 * @param fkId
	 * @return 获取用户对关联 id 的贡献
	 */
	public static List<Contribute> findContributeByUidAndFkId(int uId, int fkId) {
		ContributeDao dao=new ContributeImp();
		return dao.findContributeByUidAndFkId(uId, fkId);
	}

	/**
	 * @return  获取所有的贡献
	 */
	public static List<Contribute> findAllContribute() {
		ContributeDao dao=new ContributeImp();
		return dao.findAllContribute();
	}

}
