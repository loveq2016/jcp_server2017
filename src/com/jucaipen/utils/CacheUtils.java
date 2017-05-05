package com.jucaipen.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtils {  
    private static final CacheManager cacheManager = new CacheManager("C:/Users/Administrator/git/jcp_server2017/AccumulateWealth/WebRoot/WEB-INF/ehcache.xml");  
    private Cache cache; 
    public CacheUtils(String chcheName){  
        this.cache=cacheManager.getCache(chcheName);
    }  
  
    public Cache getCache() {  
        return cache;  
    }  
  
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
  
  
        /* 
     * ͨ�����ƴӻ����л�ȡ���� 
     */  
    public Object getCacheElement(String cacheKey) throws Exception {  
            net.sf.ehcache.Element e = cache.get(cacheKey);  
        if (e == null) {  
            return null;  
        }  
        return e.getValue();  
    }  
    /* 
     * ��������ӵ������� 
     */  
    public void addToCache(String cacheKey, Object result)  {  
        Element element = new Element(cacheKey, result);  
        cache.put(element);  
    }  
  
  
}  