package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.LinkUrl;

/**
 * @author Administrator
 *
 *  连接URL
 */
public interface LinkUrlDao {
	
	/**
	 * @param id
	 * @return 通过id获取连接地址
	 */
	public LinkUrl findUrlById(int id);
	
	/**
	 * @return 获取所有连接地址
	 */
	public List<LinkUrl> findAll();

}
