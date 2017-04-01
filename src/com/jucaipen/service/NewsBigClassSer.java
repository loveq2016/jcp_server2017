package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.NewsBigClassDao;
import com.jucaipen.daoimp.NewsBigClassImp;
import com.jucaipen.model.NewsBigClass;

public class NewsBigClassSer {

	public static List<NewsBigClass> findAll() {
		NewsBigClassDao dao = new NewsBigClassImp();
		return dao.findAll();
	}

	public static NewsBigClass findClassById(int id) {
		NewsBigClassDao dao = new NewsBigClassImp();
		return dao.findClassById(id);
	}

}
