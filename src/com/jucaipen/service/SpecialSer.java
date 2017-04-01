package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.SpecialDao;
import com.jucaipen.daoimp.SpecialImp;
import com.jucaipen.model.Special;

/**
 * @author Administrator
 *
 * ×¨¼­
 */
public class SpecialSer {

	public static Special findSpecialById(int id) {
		SpecialDao dao=new SpecialImp();
		return dao.findSpecialById(id);
	}

	public static List<Special> findAll(int page) {
		SpecialDao dao=new SpecialImp();
		return dao.findAll(page);
	}

}
