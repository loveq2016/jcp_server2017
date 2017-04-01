package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VideoLiveSaleDao;
import com.jucaipen.daoimp.VideoLiveSaleImp;
import com.jucaipen.model.VideoLiveSale;

public class VideoLiveSaleSer {

	public static VideoLiveSale findSaleByUidAndLiveId(int uId, int liveId) {
		VideoLiveSaleDao dao=new VideoLiveSaleImp();
		return dao.findSaleByUidAndLiveId(uId, liveId);
	}

	public static List<VideoLiveSale> findSaleByUserId(int userId) {
		VideoLiveSaleDao dao=new VideoLiveSaleImp();
		return dao.findSaleByUserId(userId);
	}

	public static int addSale(VideoLiveSale sale) {
		VideoLiveSaleDao dao=new VideoLiveSaleImp();
		return dao.addSale(sale);
	}

}
