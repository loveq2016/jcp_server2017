package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.IdeaSale;

public interface IdeaSaleDao {
	/**
	 * @return  获取全部的销售信息
	 */
	public List<IdeaSale> findAllTxtLiveSale(int page);
	/**
	 * @param userId
	 * @return    获取用户购买的信息
	 */
	public List<IdeaSale> findTxtLiveSaleByUid(int userId,int page);
	/**
	 * @param teacherId
	 * @return   根据讲师获取销售信息
	 */
	public List<IdeaSale> findTxtLiveSaleByTeacherId(int teacherId,int page);
	/**
	 * @param liveId
	 * @return   根据直播id获取销售信息
	 */
	public List<IdeaSale> findTxtLiveSaleByLiveId(int liveId);
	/**
	 * @param uId
	 * @param liveId
	 * @return   获取用户购买的特定直播信息
	 */
	public IdeaSale findTxtLiveSaleByUiDAndLiveId(int uId,int liveId);
	/**
	 * @param id
	 * @return   根据id获取购买信息
	 */
	public IdeaSale findTxtLiveSaleById(int id);
	
	/**
	 * @param orderCode
	 * @return   根据订单号获取购买的直播信息
	 */
	public IdeaSale findTxtLiveSaleByOrderCode(String orderCode);
	/**
	 * @param uId
	 * @param count
	 * @return   获取用户最近购买的文字直播记录
	 */
	public List<IdeaSale> findTxtLiveSaleByUserLastCount(int uId,int count);



}
