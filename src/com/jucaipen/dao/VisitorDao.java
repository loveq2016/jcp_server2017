package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Visitor;

public interface VisitorDao {
	
	/**
	 * @return  获取所有访客
	 */
	public List<Visitor>  getAllVisitor();

}
