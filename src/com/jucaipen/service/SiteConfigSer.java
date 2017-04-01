package com.jucaipen.service;

import com.jucaipen.dao.SiteConfigDao;
import com.jucaipen.daoimp.SiteConfigImp;
import com.jucaipen.model.SiteConfig;

/**
 * @author YLF
 * 
 *
 */
public class SiteConfigSer{

	/**
	 * @return   获取配置信息
	 */
	public static SiteConfig findSiteConfig() {
		SiteConfigDao dao=new SiteConfigImp();
		return dao.findSiteConfig();
	}
}
