package com.jucaipen.manager;

import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;

public class DataManager {
	
	/**
	 * @param key
	 * @return
	 * @throws Exception
	 * 
	 *   ªÒ»°ª∫¥Ê
	 */
	public static Object  getCached(String cacheStore,String key){
		CacheUtils utils=new CacheUtils(cacheStore);
		try {
			return utils.getCacheElement(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
