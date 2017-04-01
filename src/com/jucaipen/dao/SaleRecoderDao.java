package com.jucaipen.dao;

public interface SaleRecoderDao {
	
	/**
	 * @param uId
	 * @return  根据用户id获取试看次数
	 */
	public int findSaleCountByRecoder(int uId);

}
