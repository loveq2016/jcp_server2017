package com.jucaipen.service;

import com.jucaipen.dao.CheckDetailDao;
import com.jucaipen.daoimp.CheckDetailImp;
import com.jucaipen.model.CheckDetail;

/**
 * @author Administrator
 *
 *  审核信息
 */
public class CheckDetailSer {

	/**
	 * @param id
	 * @return  通过id获取申请信息
	 */
	public static CheckDetail findCheckById(int id) {
		CheckDetailDao dao=new CheckDetailImp();
		return dao.findCheckById(id);
	}

	/**
	 * @param applyId
	 * @return 通过申请id获取审核信息
	 */
	public static CheckDetail findCheckByApplyId(int applyId) {
		CheckDetailDao dao=new CheckDetailImp();
		return dao.findCheckByApplyId(applyId);
	}

}
