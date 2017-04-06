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
	 * @return  根据单个讲师贡献榜     年 、月、日榜
	 */ 
	public static List<Contribute> findContributeByTid(int tId,String type) {
		ContributeDao dao=new ContributeImp();
		return dao.findContributeByTid(tId,type);
	}
	
	
	
	/**
	 * @param type
	 * @return  获取讲师最新的总榜单
	 */
	public static List<Contribute> findContributeGroupByTid(String type){
		ContributeDao dao=new ContributeImp();
		return dao.findContributeGroupByTid(type);
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
	 * @param teacherId
	 * @return  获取总贡献数
	 */
	public static int findAllContributeByTid(int teacherId){
		ContributeDao dao=new ContributeImp();
		return dao.findAllContributeByTid(teacherId);
	}

	/**
	 * @return  获取所有的贡献
	 */
	public static List<Contribute> findAllContribute() {
		ContributeDao dao=new ContributeImp();
		return dao.findAllContribute();
	}
	
	public static int addContribute(Contribute contribute){
		ContributeDao dao=new ContributeImp();
		return dao.addContribute(contribute);
		
	}

}
