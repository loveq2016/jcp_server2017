package com.jucaipen.base;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

public class CacheListener implements CacheManagerEventListener{

	@Override
	public void dispose() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Status getStatus() {
		//»ñÈ¡×´Ì¬ STATUS_UNINITIALISED¡¢ STATUS_ALIVEºÍSTATUS_SHUTDOWN
		return null;
	}

	@Override
	public void init() throws CacheException {
		//³õÊ¼»¯
		
	}

	@Override
	public void notifyCacheAdded(String arg0) {
		//Ìí¼Ó»º´æ
		System.out.println("Ìí¼Ó»º´æ==================");
		
	}

	@Override
	public void notifyCacheRemoved(String arg0) {
		//ÒÆ³ý»º´æ
		
	}

}
