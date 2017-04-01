package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.MyGifts;

/**
 * @author Administrator
 *
 *  我的礼品 （收到   送出礼品记录）
 */
public interface MyGiftsDao {
	/**
	 * @param senderId
	 * @return  获取我送出的礼品
	 */
	public List<MyGifts> findMyGiftBySenderId(int senderId,int page);
	
	/**
	 * @param senderId
	 * @return  获取我送出的礼品
	 */
	public List<MyGifts> findMyGiftDetailBySenderId(int senderId,int giftId,int page);
	
	/**
	 * @param receiverId
	 * @param page
	 * @return 获取收到的礼品
	 */
	public List<MyGifts> findMyGiftByReceiverId(int receiverId,int page);
	
	/**
	 * @param receiverId
	 * @param page
	 * @return 获取收到的礼品
	 */
	public List<MyGifts> findMyGiftDetailByReceiverId(int receiverId,int giftId,int page);
	
	/**
	 * @param gifts
	 * @return 添加礼品记录
	 */
	public int addGifts(MyGifts gifts);

}
