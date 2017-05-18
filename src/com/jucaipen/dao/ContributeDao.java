package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Contribute;

/**
 * @author Administrator
 * 
 *         贡献榜
 */
public interface ContributeDao {
	/**
	 * @param id
	 * @return 通过id获取贡献榜信息
	 */
	public Contribute findContributeById(int id);
	
	
	

	/**
	 * @param uId
	 * @return 通过用户id获取贡献榜
	 */
	public List<Contribute> findContributeByTid(int teacherIds,String type);
	
	public  List<Contribute> findContributeByTid(int tId,String type,int page);
	
	public  List<Contribute> findTopContributeByTid(int tId,String type,int top);
	
	/**
	 * @param teacherId
	 * @param type
	 * @return  获取所有讲师最新总榜
	 */
	public List<Contribute>  findContributeGroupByTid(String type);
	
	
	
	/**
	 * @param teacherId
	 * @return  获取讲师的总榜单
	 */
	public int  findAllContributeByTid(int teacherId);
	

	/**
	 * @param uId
	 * @param tId
	 * @return 查询用户对某个讲师的贡献
	 */
	public List<Contribute> findContributeByUidAndTid(int uId, int tId);

	/**
	 * @param uId
	 * @param fkId
	 * @return 获取用户关联的贡献信息
	 */
	public List<Contribute> findContributeByUidAndFkId(int uId, int fkId);

	/**
	 * @return 获取所有的贡献信息
	 */
	public List<Contribute> findAllContribute();
	
	/**
	 * @param contribute
	 * @return   添加贡献
	 */  
	public int addContribute(Contribute contribute);
}
