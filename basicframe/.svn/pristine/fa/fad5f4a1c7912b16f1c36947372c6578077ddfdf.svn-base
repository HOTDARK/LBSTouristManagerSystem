package com.hd.sfw.core.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * 类处理工具类
 * @author somnuscy
 *
 */
public class ClassUtils {
	
	/**
	 * 反射对象属性取值
	 * @param obj 取值对象
	 * @param fieldName 属性名称
	 * @return 获取到的值
	 */
	public static Object reflexGetVal(Object obj, String fieldName) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
			return pd.getReadMethod().invoke(obj);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 反射对象属性设值
	 * @param obj 设值对象
	 * @param fieldName 属性名称
	 * @param fieldVal 设定值
	 */
	public static void reflexSetVal(Object obj, String fieldName, Object fieldVal){
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
			pd.getWriteMethod().invoke(obj, fieldVal);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
