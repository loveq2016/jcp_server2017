package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Visitor;

public interface VisitorDao {
	
	/**
	 * @return  ��ȡ���зÿ�
	 */
	public List<Visitor>  getAllVisitor();
	
	/**
	 * @param visitor
	 * @return  ���ӷ�������Ϣ
	 */
	public int addVisitor(Visitor visitor);

}