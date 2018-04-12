package com.hd.sys.core.properties.typehandler;

import com.hd.sys.core.properties.TypeHandler;

/**
 * 字符串数据处理
 * @author sunjian
 * @version V1.0, 2013-5-16 下午02:34:14
 */
public class StringTypeHandler implements TypeHandler {

	@Override
	public Object toObj(String str) {
		return str;
	}

	@Override
	public String toStr(Object obj) {
		return (String)obj;
	}

}
