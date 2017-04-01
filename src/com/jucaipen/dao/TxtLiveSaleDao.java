package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.TxtLiveSale;

public interface TxtLiveSaleDao {
	public TxtLiveSale findSaleByUidAndTxtId(int uId, int txtId);

	public List<TxtLiveSale> findSaleByUserId(int userId,int page);
	
	public List<TxtLiveSale> findSaleByUserIdAndTiD(int userId,int teacherId);
	

	public int addSale(TxtLiveSale sale);

}
