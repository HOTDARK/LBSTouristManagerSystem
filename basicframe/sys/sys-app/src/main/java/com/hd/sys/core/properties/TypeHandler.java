package com.hd.sys.core.properties;

/**
 * 对Property的type对应的处理接口
 * @author sunjian
 * @version V1.0, 2013-5-15 下午01:52:58
 */
public interface TypeHandler {

	/**
	 * 把对象转换成字符串
	 * @param obj
	 * @return
	 */
	public String toStr(Object obj);
	
	/**
	 * 把字符串转换成对象
	 * @param str
	 * @return
	 */
	public Object toObj(String str);
}
