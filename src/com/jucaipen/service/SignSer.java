package com.jucaipen.service;

import com.jucaipen.dao.SignDao;
import com.jucaipen.daoimp.SignImp;
import com.jucaipen.model.Sign;

public class SignSer {

	/**
	 * @param userId
	 * @return  获取用户签到信息
	 */
	public static Sign findSignByUid(int userId) {
		SignDao dao=new SignImp();
		return dao.findSignByUid(userId);
	}

	public static int addSign(Sign sign) {
		SignDao dao=new SignImp();
		return dao.addSign(sign);
	}
	
	/**
	 * @param sign
	 * @return 更新签到总表信息
	 */
	public static int updateSign(Sign sign){
		SignDao dao=new SignImp();
		return dao.updateSign(sign);
	}
	/**
	 * @param uId
	 * @return  获取签到次数
	 */
	public static int findSignCount(int uId){
		SignDao dao=new SignImp();
		return dao.findSignCount(uId);
	}

}
