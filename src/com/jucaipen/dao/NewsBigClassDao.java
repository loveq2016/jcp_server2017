package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.NewsBigClass;

public interface NewsBigClassDao {

	public List<NewsBigClass> findAll();

	public NewsBigClass findClassById(int id);

}
