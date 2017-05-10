package com.jucaipen.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtils {  
	private static final CacheManager cacheManagerLocal=new CacheManager("C:\\Users\\Administrator\\git\\jcp_server2017\\AccumulateWealth\\WebRoot\\WEB-INF\\ehcache.xml");
   // private static final CacheManager cacheManagerNormal = new CacheManager("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/ehcache.xml");  
    private Cache cache;
    public CacheUtils(String chcheName){  
    	this.cache=cacheManagerLocal.getCache(chcheName);
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
     *    �Ƴ����������
     */
    public void removeCacheElement(String cacheStroeName){
    	Cache cache2 = cacheManagerLocal.getCache(cacheStroeName);
    	 if(cache2!=null){
    		 cacheManagerLocal.removeCache(cacheStroeName);
    	 }
    }
    
    
    /**
     *    �Ƴ����л��棬�رջ���ʵ��
     */
    public void removeAll(){
    	cacheManagerLocal.removalAll();
    	cacheManagerLocal.shutdown();
    }
    
    /**
     * @param key
     *   �Ƴ�����key
     */
    public void removeCacheKey(String key){
    	Element element = cache.get(key);
    	if(element!=null){
    		cache.remove(element);
    	}
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
     * ���������ӵ������� 
     */  
    public void addToCache(String cacheKey, Object result)  {  
        Element element = new Element(cacheKey, result);  
        cache.put(element);  
    }  
  
  
}  