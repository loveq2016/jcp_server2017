package com.jucaipen.service;

import com.jucaipen.dao.ApplyDetailsDao;
import com.jucaipen.daoimp.ApplyDetailsImp;
import com.jucaipen.model.ApplyDetails;

public class ApplyDetailsSer {

	public static ApplyDetails findDetailsByApplyId(int applyId) {
		ApplyDetailsDao dao = new ApplyDetailsImp();
		return dao.findDetailsByApplyId(applyId);
	}

}
