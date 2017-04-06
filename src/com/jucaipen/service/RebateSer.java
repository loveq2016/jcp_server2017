package com.jucaipen.service;
import java.util.List;
import com.jucaipen.dao.RebateDao;
import com.jucaipen.daoimp.RebateImp;
import com.jucaipen.model.Rebate;
/**
 * @author Administrator
 *
 *  返利
 */
public class RebateSer {

	/**
	 * @param id
	 * @return 根据id获取返利信息
	 */
	public static Rebate findRebateById(int id) {
		RebateDao dao=new RebateImp();
		return dao.findRebateById(id);
	}
	
/*	
	*//**
	 * @param teacherId
	 * @return  获取榜单
	 *//*
	public static List<Rebate> findRebateByTeacher(int teacherId,String type){
		RebateDao dao=new RebateImp();
		return dao.findRebateByTeacher(teacherId,type);
	}
*/
	/**
	 * @param teacherId
	 * @param page
	 * @return 根据讲师id获取返利信息   分页
	 */
	public static List<Rebate> findRebateByTid(int teacherId, int page) {
		RebateDao dao=new RebateImp();
		return dao.findRebateByTid(teacherId, page);
	}
	
	/**
	 * @param userId
	 * @param page
	 * @return  获取我的返利信息
	 */
	public static List<Rebate> findRebateByUserId(int userId,int page){
		RebateDao dao=new RebateImp();
		return dao.findRebate(userId, page);
	}
	
	public static int contributeBills(int uId,int tId){
		RebateDao dao=new RebateImp();
		return dao.contributeBills(uId, tId);
	}
	
	/**
	 * @param teacherId
	 * @return 根据讲师id获取返利信息  无分页
	 */
	public static List<Rebate> findRebateByTid(int teacherId) {
		RebateDao dao=new RebateImp();
		return dao.findRebateByTid(teacherId);
	}

	/**
	 * @param rebate
	 * @return 添加返利信息
	 */
	public static int addRebate(Rebate rebate) {
		RebateDao dao=new RebateImp();
		return dao.addRebate(rebate);
	}

	/**
	 * @param uId
	 * @param tId
	 * @return  获取用户贡献讲师聚财币信息
	 */
	public static List<Rebate> findRebate(int uId,int tId){
		RebateDao dao=new RebateImp();
		return dao.findRebate(uId, tId);
	}
	
	/**
	 * @param type
	 * @return  获取讲师  年-月-日总榜
	 */
	public static List<Rebate>  findLastTeacherLast(String type){
		RebateDao dao=new RebateImp();
		return dao.findLastTeacherLast(type);
	}
	
	
}
