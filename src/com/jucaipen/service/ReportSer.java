package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.ReportDao;
import com.jucaipen.daoimp.ReportImp;
import com.jucaipen.model.Report;

public class ReportSer {

	public static Report findRepoterById(int id) {
		ReportDao dao=new ReportImp();
		return dao.findRepoterById(id);
	}

	public static List<Report> findRepoterByUserId(int userId, int page) {
		ReportDao dao=new ReportImp();
		return dao.findRepoterByUserId(userId, page);
	}

	public static List<Report> findRepoterByUserIdAndType(int userId, int type,
			int page) {
		ReportDao dao=new ReportImp();
		return dao.findRepoterByUserIdAndType(userId, type, page);
	}

	public static int addRepoter(Report report) {
		ReportDao dao=new ReportImp();
		return dao.addRepoter(report);
	}

}
