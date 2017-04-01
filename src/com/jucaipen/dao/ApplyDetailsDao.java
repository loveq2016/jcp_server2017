package com.jucaipen.dao;

import com.jucaipen.model.ApplyDetails;

public interface ApplyDetailsDao {
	
	public ApplyDetails findDetailsByApplyId(int applyId);

}
