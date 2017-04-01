package com.jucaipen.service;

import com.jucaipen.dao.SaleRecoderDao;
import com.jucaipen.daoimp.SaleRecoderImp;

public class SaleRecoderSer{

	public static int findSaleCountByRecoder(int uId) {
		SaleRecoderDao dao=new SaleRecoderImp();
		return dao.findSaleCountByRecoder(uId);
	}

}
