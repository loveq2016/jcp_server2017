package com.jucaipen.dao;
import com.jucaipen.model.LiveRecoderSale;
/**
 * @author 杨朗飞
 *2017年3月11日  下午1:40:12
 *
 *  直播购买单次记录
 */
public interface LiveRecoderSaleDao {
	
	/**
	 * @param uId
	 * @param liveId
	 * @return  获取用户购买的单次直播
	 */
	public  LiveRecoderSale  getLiveSaleByUidAndLiveId(int uId,int liveId);
	
	/**
	 * @param sale
	 * @return  添加购买单次直播数据
	 */
	public int addLiveSale(LiveRecoderSale sale);

}
