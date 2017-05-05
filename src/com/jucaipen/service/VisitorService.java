package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.VisitorDao;
import com.jucaipen.daoimp.VisitorImp;
import com.jucaipen.model.Visitor;

public class VisitorService {

	public static List<Visitor> getAllVisitor() {
		VisitorDao dao=new VisitorImp();
		return dao.getAllVisitor();
	}

}
