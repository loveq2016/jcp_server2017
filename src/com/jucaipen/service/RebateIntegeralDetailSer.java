package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.RebateIntegeralDetailDao;
import com.jucaipen.daoimp.RebateIntegeralDetailImp;
import com.jucaipen.model.RebateIntegeralDetail;

public class RebateIntegeralDetailSer {

	/**
	 * @param id
	 * @return 根据id获取分离积分信息
	 */
	public static RebateIntegeralDetail findRebateIntegeralById(int id) {
		RebateIntegeralDetailDao dao=new RebateIntegeralDetailImp();
		return dao.findRebateIntegeralById(id);
	}

	/**
	 * @param userId
	 * @param page
	 * @return 根据用户id获取返利积分信息   分页
	 */ 
	public static List<RebateIntegeralDetail> findRebateIntegeralByUserId(int userId,
			int page) {
		RebateIntegeralDetailDao dao=new RebateIntegeralDetailImp();
		return dao.findRebateIntegeralByUserId(userId, page);
	}
	
	/**
	 * @param uId
	 * @return 根据id获取返利信息    无分页
	 */
	public static List<RebateIntegeralDetail> findRebateIntegeralByUserId(int uId){
		RebateIntegeralDetailDao dao=new RebateIntegeralDetailImp();
		return dao.findRebateIntegeralByUserId(uId);
	}

	/**
	 * @param inDetail
	 * @return 添加返利积分信息
	 */
	public static int addRebateIntegeral(RebateIntegeralDetail inDetail) {
		RebateIntegeralDetailDao dao=new RebateIntegeralDetailImp();
		return dao.addRebateIntegeral(inDetail);
	}

}
