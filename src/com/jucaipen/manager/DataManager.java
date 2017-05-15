package com.jucaipen.manager;
import com.jucaipen.utils.CacheUtils;
public class DataManager {
	
	/**
	 * @param key
	 * @return
	 * @throws Exception
	 * 
	 *   ªÒ»°ª∫¥Ê
	 */
	public static Object  getCached(String cacheStore,String key,boolean isCache){
		CacheUtils utils=new CacheUtils(cacheStore);
		try {
			return utils.getCacheElement(key,isCache);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
