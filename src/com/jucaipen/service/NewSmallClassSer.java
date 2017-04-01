package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.NewSmallClassDao;
import com.jucaipen.daoimp.NewSmallClassImp;
import com.jucaipen.model.NewSmallClass;

public class NewSmallClassSer{

	public static List<NewSmallClass> findAll() {
		NewSmallClassDao dao=new NewSmallClassImp();
		return dao.findAll();
	}

	public static List<NewSmallClass> findClassByBigId(int bigId) {
		NewSmallClassDao dao=new NewSmallClassImp();
		return dao.findClassByBigId(bigId);
	}

	public static NewSmallClass findClassById(int id) {
		NewSmallClassDao dao=new NewSmallClassImp();
		return dao.findClassById(id);
	}

}
