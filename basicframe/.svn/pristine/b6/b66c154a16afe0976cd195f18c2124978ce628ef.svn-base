package com.hd.sfw.core.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author sunjian
 * @version V1.0, 2013-7-5 下午04:33:33
 */
public class SimpleCacheManager implements CacheManager{
	private Map<Method,CacheableDefine> cacheableDefineMap = new HashMap<Method, CacheableDefine>();

	private boolean isOpen = true;
	
	@Override
	public CacheableDefine getCacheableDefine(Method method) {
		return cacheableDefineMap.get(method);
	}

	@Override
	public void putCacheableDefine(CacheableDefine cacheableDefine) {
		if(cacheableDefine==null||cacheableDefine.getMethod()==null){
			throw new IllegalArgumentException("参数不合法");
		}
		cacheableDefineMap.put(cacheableDefine.getMethod(), cacheableDefine);
	}

	@Override
	public List<CacheableDefine> getCacheableDefines() {
		return new ArrayList<CacheableDefine>(cacheableDefineMap.values());
	}

	@Override
	public CacheableDefine getCacheableDefineByID(String id) {
		CacheableDefine result = null;
		 for(Entry<Method, CacheableDefine> data:cacheableDefineMap.entrySet()) {
			 if(data.getValue().getId().equals(id)){
				 result = data.getValue();
				 break;
			 }
		 }
		return result;
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setOpen(boolean open){
		isOpen = open;
	}

}
