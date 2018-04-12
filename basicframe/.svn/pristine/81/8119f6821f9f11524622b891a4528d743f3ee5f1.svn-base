package com.hd.sys.core.properties;

import java.util.Map;

import com.hd.sys.entity.SysProp;

/**
 * 系统上下文中的属性变量常量存放器，提供多种方法以方便存取属性
 * @author sunjian
 * @version V1.0, 2013-5-6 上午11:12:10
 */
public interface ContextProperties {

	/**
	 * 获取key对应的值
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key);
	
	/**
	 * 获取key对应的值
	 * @param key
	 * @return
	 */
	public Integer getInt(String key);
	
	/**
	 * 获取key对应的值
	 * @param key
	 * @return
	 */
	public String getString(String key);
	
	/**
	 * 获取key对应的值 返回Object
	 * @param key
	 * @return
	 */
	public Object getObject(String key);
	
	/**
	 * 获取key对应的值 返回long
	 * @param key
	 * @return
	 */
	public long getLong(String key);

	/**
	 * 获得所有属性
	 * @return
	 */
	public Map<String, Object> getProperties();
	
	/**
	 * 添加到环境变量中
	 * @param key
	 * @param obj
	 * @return
	 */
	public void put(String key,Object obj);
	
	/**
	 * 添加一个属性到系统变量中并对该值进行持久化
	 * @param key
	 * @param obj
	 * @param state
	 */
	public void save(String key,Object obj,int state);
	
	/**
	 * 按key进行移除操作
	 * @param key
	 */
	public void remove(String key);
	
	/**
	 * 删除 并从持久化中移除
	 * @param key
	 */
	public void delete(String key);
	
	/**
	 * 把字符串转换成对应类型的对象
	 * @param type 类型
	 * @param content
	 * @return
	 */
	public Object convert(String type,String content);
	
	/**
	 * 查找property
	 * @param key
	 * @return 只返回被持久化的property
	 */
	public SysProp findProperty(String key);
	
}
