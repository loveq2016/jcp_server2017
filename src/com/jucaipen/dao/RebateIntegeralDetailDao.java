package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.RebateIntegeralDetail;

/**
 * @author Administrator
 *
 *  返利积分
 */
public interface RebateIntegeralDetailDao {
	
	/**
	 * @param id
	 * @return 根据id获取返利积分记录
	 */
	public RebateIntegeralDetail findRebateIntegeralById(int id);
	
	/**
	 * @param userId
	 * @param page
	 * @return 通过用户id获取返利积分信息  分页
	 */
	public List<RebateIntegeralDetail> findRebateIntegeralByUserId(int userId,int page);
	
	/**
	 * @param userId
	 * @return  通过用户id获取返利积分信息   无分页
	 */
	public List<RebateIntegeralDetail> findRebateIntegeralByUserId(int userId);
	
	/**
	 * @param inDetail
	 * @return  新增返利积分信息
	 */
	public int addRebateIntegeral(RebateIntegeralDetail inDetail);

}
