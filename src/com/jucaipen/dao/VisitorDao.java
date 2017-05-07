package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Visitor;

public interface VisitorDao {
	
	/**
	 * @return  获取所有访客
	 */
	public List<Visitor>  getAllVisitor();
	
	/**
	 * @param visitor
	 * @return  添加访问者信息
	 */
	public int addVisitor(Visitor visitor);

}
