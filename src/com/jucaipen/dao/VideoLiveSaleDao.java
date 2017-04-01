package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.VideoLiveSale;

public interface VideoLiveSaleDao {
	
	public VideoLiveSale findSaleByUidAndLiveId(int uId,int liveId);
	
	
	public List<VideoLiveSale>  findSaleByUserId(int userId);
	
	
	public int addSale(VideoLiveSale sale);

}
