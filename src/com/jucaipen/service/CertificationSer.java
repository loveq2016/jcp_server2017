package com.jucaipen.service;

import java.util.List;

import com.jucaipen.dao.CertificationDao;
import com.jucaipen.daoimp.CertificationImp;
import com.jucaipen.model.Certification;

/**
 * @author Administrator 证书信息
 */
public class CertificationSer {

	/**
	 * @return 获取所有证书信息
	 */
	public static List<Certification> findAllCertification() {
		CertificationDao dao = new CertificationImp();
		return dao.findAllCertification();
	}

	/**
	 * @param id
	 * @return 根据id获取证书信息
	 */
	public static Certification findCertificationById(int id) {
		CertificationDao dao = new CertificationImp();
		return dao.findCertificationById(id);
	}

}
