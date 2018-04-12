package com.hd.sfw.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * BEAN处理工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-11-6 下午1:29:41
 */
public class BeanUtils extends org.springframework.beans.BeanUtils{
	
	/**
	 * 使用copyProperties转换集合中的对象
	 * @param collection 需要转换的集合
	 * @param desClass 转换后的类，必须有空的构造方法
	 * @return collection为空或null时会返回一个空List
	 */
	public static <T,D> List<D> convert(Collection<T> collection,Class<D> desClass){
		List<D> rs = new ArrayList<D>();
		
		if(CollectionUtils.isNotEmpty(collection)){
			for(T t:collection){
				D d = null;
				try {
					d = desClass.newInstance();
					copyProperties(t, d);
					rs.add(d);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
				
			}
		}
		
		return rs;
	}
	
	/**
	 * 转换给定集合中的对象 
	 * @param list
	 * @param converter
	 * @return
	 */
	public static <T,D> List<D> convert(Collection<T> list,BeanConverter<T, D> converter){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		List<D> rs = new ArrayList<D>();
		for(T t:list){
			rs.add(converter.convert(t));
		}
		
		return rs;
	}
	

	/**
	 * bean转换接口
	 * @version	0.0.1
	 * @date	2014-11-6 下午1:28:44
	 */
	public static interface BeanConverter<T,D> {
		
		/**
		 * 把T转换成D
		 * @param t
		 * @return
		 */
		public D convert(T t);
		
	}
}
