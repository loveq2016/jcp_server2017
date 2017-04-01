package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.MyGiftsDao;
import com.jucaipen.daoimp.MyGiftsImp;
import com.jucaipen.model.MyGifts;

public class MyGiftsSer{

	/**
	 * @param senderId
	 * @param page
	 * @return  获取我送出的礼品
	 */
	public static List<MyGifts> findMyGiftBySenderId(int senderId, int page) {
		MyGiftsDao dao=new MyGiftsImp();
		return dao.findMyGiftBySenderId(senderId, page);
	}

	/**
	 * @param receiverId
	 * @param page
	 * @return  获取我收到的礼品
	 */
	public static List<MyGifts> findMyGiftByReceiverId(int receiverId, int page) {
		MyGiftsDao dao=new MyGiftsImp();
		return dao.findMyGiftByReceiverId(receiverId, page);
	}

	/**
	 * @param gifts
	 * @return 添加礼品记录
	 */
	public static int addGifts(MyGifts gifts) {
		MyGiftsDao dao=new MyGiftsImp();
		return dao.addGifts(gifts);
	}
	
	public static List<MyGifts> findMyGiftDetailBySenderId(int senderId, int giftId,
			int page){
		MyGiftsDao dao=new MyGiftsImp();
		return dao.findMyGiftDetailBySenderId(senderId, giftId, page);
	}
	
	public static List<MyGifts> findMyGiftDetailByReceiverId(int receiverId,
			int giftId, int page) {
		MyGiftsDao dao=new MyGiftsImp();
		return dao.findMyGiftDetailByReceiverId(receiverId, giftId, page);
	}

}
