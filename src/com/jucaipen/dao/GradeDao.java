package com.jucaipen.dao;

import com.jucaipen.model.Grade;

public interface GradeDao {
	
	/**
	 * @param interal
	 * @return   根据积分获取用户等级
	 */
	public Grade  findGradByIntegeral(int interal);
	
}
