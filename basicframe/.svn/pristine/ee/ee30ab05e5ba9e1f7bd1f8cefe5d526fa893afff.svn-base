package com.hd.sfw.core.cache;

import org.apache.commons.lang.StringUtils;

/**
 * 默认key生成器(规则：缓存名称_前缀_方法全路径_参数1_参数2...)
 * @author somnuscy
 *
 */
public class DefaultCacheKeyGenerator implements CacheKeyGenerator {

	@Override
	public Object getKey(CacheableDefine cacheDefine, Object[] args) {
		String keyPrefix = StringUtils.isBlank(cacheDefine.getPrefix()) ? cacheDefine.getName() : cacheDefine.getPrefix();
		StringBuffer sb = new StringBuffer(cacheDefine.getName());
		sb.append("_");
		sb.append(keyPrefix);
		sb.append("_");
		sb.append(cacheDefine.getMethod().getDeclaringClass().getName()).append(".").append(cacheDefine.getMethod().getName());
		if(args != null){
			for(Object ob: args){
				sb.append("_");
				sb.append(ob);
			}
		}
		return sb.toString();
	}

}
