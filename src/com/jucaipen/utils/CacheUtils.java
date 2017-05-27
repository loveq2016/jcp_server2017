package com.jucaipen.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class CacheUtils {
	// private static final String
	private static final String localCache="C:\\Users\\Administrator\\git\\jcp_server2017\\AccumulateWealth\\WebRoot\\WEB-INF\\ehcache.xml";
	private static final String norlMal = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/ehcache.xml";
	private static final CacheManager cacheManagerNormal = new CacheManager(
			norlMal);
	private Cache cache;
	public CacheUtils(String chcheName) {
		this.cache = cacheManagerNormal.getCache(chcheName);
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * @param cacheStroeName
	 * 
	 *            �Ƴ����������
	 */
	public void removeCacheElement(String cacheStroeName) {
		Cache cache2 = cacheManagerNormal.getCache(cacheStroeName);
		if (cache2 != null) {
			cacheManagerNormal.removeCache(cacheStroeName);
		}
	}

	/**
	 * �Ƴ����л��棬�رջ���ʵ��
	 */
	public void removeAll() {
		cacheManagerNormal.removalAll();
		cacheManagerNormal.shutdown();
	}

	/**
	 * @param key
	 *            �Ƴ�����key
	 */
	public void removeCacheKey(String key) {
		Element element = cache.get(key);
		if (element != null) {
			cache.remove(element);
		}
	}

	/*
	 * ͨ�����ƴӻ����л�ȡ Serializable ����
	 */
	public Object getCacheElementSerializable(String cacheKey, boolean isCache)
			throws Exception {
		if (!isCache) {
			return null;
		}
		net.sf.ehcache.Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getValue();
	}
	
	/*
	 * ͨ�����ƴӻ����л�ȡ �� Serializable ����
	 */
	public Object getCacheElement(String cacheKey, boolean isCache)
			throws Exception {
		if (!isCache) {
			return null;
		}
		net.sf.ehcache.Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getObjectValue();
	}
	
	
	

	/*
	 * ��������ӵ�������
	 */
	public void addToCache(String cacheKey, Object result) {
		Element element = new Element(cacheKey, result);
		cache.put(element);
	}

}