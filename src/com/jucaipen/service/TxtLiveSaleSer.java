package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.TxtLiveSaleDao;
import com.jucaipen.daoimp.TxtLiveSaleImp;
import com.jucaipen.model.TxtLiveSale;

public class TxtLiveSaleSer {

	public static TxtLiveSale findSaleByUidAndTxtId(int uId, int txtId) {
		TxtLiveSaleDao dao=new TxtLiveSaleImp();
		return dao.findSaleByUidAndTxtId(uId, txtId);
	}

	public static List<TxtLiveSale> findSaleByUserId(int userId,int page) {
		TxtLiveSaleDao dao=new TxtLiveSaleImp();
		return dao.findSaleByUserId(userId,page);
	}

	public static int addSale(TxtLiveSale sale) {
		TxtLiveSaleDao dao=new TxtLiveSaleImp();
		return dao.addSale(sale);
	}
	
	public static List<TxtLiveSale> findSaleByUserIdAndTiD(int userId, int teacherId) {
		TxtLiveSaleDao dao=new TxtLiveSaleImp();
		return dao.findSaleByUserIdAndTiD(userId, teacherId);
	}

}
