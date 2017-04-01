package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MessageModel;

/**
 * @author YLF
 * 
 *   消息模版
 *
 */
public interface MessageModelDao {
	
	/**
	 *   查询所有的模版信息
	 */
	public List<MessageModel> querryAllModel();
	
	/**
	 * @param id
	 * @return  根据id获取模版信息
	 */
	public MessageModel findModelById(int id);

}
