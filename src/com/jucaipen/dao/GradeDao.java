package com.jucaipen.dao;

import com.jucaipen.model.Grade;

public interface GradeDao {
	
	/**
	 * @param interal
	 * @return   ���ݻ��ֻ�ȡ�û��ȼ�
	 */
	public Grade  findGradByIntegeral(int interal);
	
}
