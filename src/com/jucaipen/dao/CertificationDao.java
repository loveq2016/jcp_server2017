package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.Certification;

/**
 * @author Administrator
 *
 *  证书信息
 */
public interface CertificationDao {
	
	/**
	 * @return  获取所有证书信息
	 */
	public List<Certification> findAllCertification();
	
	/**
	 * @param id
	 * @return 根据id获取证书信息
	 */
	public Certification findCertificationById(int id);

}
