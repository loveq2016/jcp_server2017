package com.jucaipen.service;
import java.util.List;

import com.jucaipen.dao.LiveRecoderSaleDao;
import com.jucaipen.daoimp.LiveRecoderSaleImp;
import com.jucaipen.model.LiveRecoderSale;
/**
 * @author 杨朗飞
 *2017年3月11日  下午2:20:50
 *  单次直播记录操作
 */
public class LiveRecoderSaleSer{

	/**
	 * @param uId
	 * @param liveId
	 * @return  获取用户购买单次直播的记录  1直播购买记录，2录播购买记录
	 */
	public static LiveRecoderSale getLiveSaleByUidAndLiveId(int uId, int liveId,int type) {
		LiveRecoderSaleDao dao=new LiveRecoderSaleImp();
		return dao.getLiveSaleByUidAndLiveId(uId, liveId,type);
	}

	/**
	 * @param sale
	 * @return  添加用户购买单次直播的记录
	 */
	public static int addLiveSale(LiveRecoderSale sale) {
		LiveRecoderSaleDao dao=new LiveRecoderSaleImp();
		return dao.addLiveSale(sale);
	}

	public static List<LiveRecoderSale> getMyVideo(int uId, int page, int type) {
		LiveRecoderSaleDao dao=new LiveRecoderSaleImp();
		return dao.getMyVideo(uId, page, type);
	}

}
