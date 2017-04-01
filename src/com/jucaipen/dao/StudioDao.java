package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Studio;

/**
 * @author Administrator
 *
 * 演播室
 */
public interface StudioDao {
	
	
	/**
	 * @return 获取所有演播室
	 */
	public List<Studio> findAll(int page);
	
	/**
	 * @param id
	 * @return 根据id获取演播室信息
	 */
	public Studio findStudioById(int id);
	
	/**
	 * @param columnId
	 * @return 根据；栏目id获取演播室信息
	 */
	public List<Studio> findStudioByColumnId(int columnId,int page);
	
	/**
	 * @param week
	 * @return  获取今日演播信息
	 */
	public List<Studio>  findStudioByToday(int week);
	
	/**
	 * @param week
	 * @return  获取演播室人气榜
	 */
	public List<Studio>  findStudioFansByToday(int week);
	
	/**
	 * @param id
	 * @return  通过演播室id获取人气
	 */
	public int findStudioRenQiById(int id);
	
	/**
	 * @param id
	 * @return  根据id修改人气信息
	 */
	public int updateStudioRenQiById(int id,int renQi);

}
