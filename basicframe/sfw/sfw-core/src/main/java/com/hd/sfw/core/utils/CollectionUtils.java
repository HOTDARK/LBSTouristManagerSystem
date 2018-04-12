/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 集合工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-18 下午3:39:19
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils{
	
	/**
	 * 判断集合是否不为空
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return collection!=null&&collection.size()>0;
	}
	
	/**
	 * 判断map是否不为空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map){
		return map!=null&&!map.isEmpty();
	}
	
	   /**
     * <p>array转list </p>
     * <p>日期：2014-12-2 上午11:30:43 </p>
     * @param t 数组
     * @return list
     */
    public static <T> List<T> array2list (T[] t) {
    	if (t.length > 0) {
    		return Arrays.asList(t);
    	}
    	return null;
    }
	
	
}
