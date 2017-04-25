package com.jucaipen.service;

import com.jucaipen.dao.GradeDao;
import com.jucaipen.daoimp.GradeImp;
import com.jucaipen.model.Grade;

public class GradeService {
	
	/**
	 * @param interal
	 * @return  根据积分获取用户等级
	 */
	public static  Grade findGradByIntegeral(int interal){
		GradeDao dao=new GradeImp();
		return dao.findGradByIntegeral(interal);
	}

}
