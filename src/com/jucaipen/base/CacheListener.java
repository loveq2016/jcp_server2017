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
		//��ȡ״̬ STATUS_UNINITIALISED�� STATUS_ALIVE��STATUS_SHUTDOWN
		return null;
	}

	@Override
	public void init() throws CacheException {
		//��ʼ��
		
	}

	@Override
	public void notifyCacheAdded(String arg0) {
		//��ӻ���
		System.out.println("��ӻ���==================");
		
	}

	@Override
	public void notifyCacheRemoved(String arg0) {
		//�Ƴ�����
		
	}

}
