package com.hd.sfw.core.cache;

import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 扫描方法 获取Cacheable信息，需要spring注入cacheManager
 * @author sunjian
 * @version V1.0, 2013-7-5 上午10:42:51
 */
public class CacheableDefineScanner implements BeanPostProcessor,InitializingBean {
	
	private final static Logger log = Logger.getLogger(CacheableDefineScanner.class);
	
	private CacheManager cacheManager;
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1)
			throws BeansException {
		ReflectionUtils.doWithMethods(bean.getClass(), new ReflectionUtils.MethodCallback() {
			public void doWith(Method method) {
				Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
				if (cacheable != null) {
					if(StringUtils.isEmpty(cacheable.value())){
						log.warn("Cacheable name is empty,"+method.getDefaultValue());
					}

					Class<? extends CacheKeyGenerator> clazz = cacheable.cacheKeyGenerator(); 
					if(clazz==null){
						log.warn("cacheKeyGenerator is null use default generator DefaultCacheKeyGenerator");
						clazz = DefaultCacheKeyGenerator.class;
					}
					CacheableDefine cacheableDefine = new CacheableDefine();
					cacheableDefine.setMethod(method);
					cacheableDefine.setName(cacheable.value());
					cacheableDefine.setPrefix(cacheable.prefix());
					cacheableDefine.setTimeToIdle(cacheable.timeToIdle());
					cacheableDefine.setTimeToLive(cacheable.timeToLive());
					cacheableDefine.setState(cacheable.state());
					cacheableDefine.setId(getMethodId(method));
					try {
						cacheableDefine.setCacheKeyGenerator(clazz.newInstance());
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					cacheManager.putCacheableDefine(cacheableDefine);
				}
			}
		}, ReflectionUtils.USER_DECLARED_METHODS);
		return bean;
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(cacheManager==null){
			throw new NullPointerException("cacheManage属性不能为null");
		}
	}
	
	private String getMethodId(Method method){
		StringBuilder sb = new StringBuilder();
		sb.append(method.getDeclaringClass().getName()+"."+method.getName()+"(");
		for(Class<?> type:method.getParameterTypes()){
			 sb.append(type.getName()+",");
		}
		
		return DigestUtils.md5Hex(sb.toString());
	}
}
