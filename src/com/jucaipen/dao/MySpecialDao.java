package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MySpecial;

/**
 * @author Administrator
 *
 *  我的专辑
 */
public interface MySpecialDao {
	
	/**
	 * @param id
	 * @return 根据id获取专辑信息
	 */
	public MySpecial findSpecialById(int id);
	
	
	/**
	 * @param specialId
	 * @return  专辑购买数量
	 */
	public int getSpecialSallNum(int specialId);
	/**
	 * @param id
	 * @return 获取我的专辑信息
	 */
	public List<MySpecial> findSpecialByUid(int userId,int page);
	
	/**
	 * @param special
	 * @return 添加专辑信息
	 */
	public int addSpecial(MySpecial special);
	/**
	 * @param id
	 * @return  删除专辑
	 */
	public int removeSpecial(int id);
	
	/**
	 * @param uId
	 * @param sId
	 * @return  是否购买专辑
	 */
	public MySpecial getIsMySpecial(int uId,int sId);

}
