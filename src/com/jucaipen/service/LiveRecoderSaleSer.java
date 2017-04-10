package com.jucaipen.service;
import java.util.List;

import com.jucaipen.dao.LiveRecoderSaleDao;
import com.jucaipen.daoimp.LiveRecoderSaleImp;
import com.jucaipen.model.LiveRecoderSale;
/**
 * @author ���ʷ�
 *2017��3��11��  ����2:20:50
 *  ����ֱ����¼����
 */
public class LiveRecoderSaleSer{

	/**
	 * @param uId
	 * @param liveId
	 * @return  ��ȡ�û����򵥴�ֱ���ļ�¼  1ֱ�������¼��2¼�������¼
	 */
	public static LiveRecoderSale getLiveSaleByUidAndLiveId(int uId, int liveId,int type) {
		LiveRecoderSaleDao dao=new LiveRecoderSaleImp();
		return dao.getLiveSaleByUidAndLiveId(uId, liveId,type);
	}

	/**
	 * @param sale
	 * @return  ����û����򵥴�ֱ���ļ�¼
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
