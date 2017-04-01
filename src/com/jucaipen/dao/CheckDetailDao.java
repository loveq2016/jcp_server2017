package com.jucaipen.dao;

import com.jucaipen.model.CheckDetail;

/**
 * @author Administrator
 *
 *  审核详细信息
 */
public interface CheckDetailDao {
	
	/**
	 * @param id
	 * @return  通过id获取审核信息
	 */
	public CheckDetail findCheckById(int id);
	
	/**
	 * @param applyId
	 * @return 通过申请id获取审核信息
	 */
	public CheckDetail findCheckByApplyId(int applyId);

}
