package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.NewSmallClass;

public interface NewSmallClassDao {
	
	public List<NewSmallClass>  findAll();
	
	
	public List<NewSmallClass>  findClassByBigId(int bigId);
	
	public NewSmallClass findClassById(int id);

}
