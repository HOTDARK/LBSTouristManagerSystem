package com.hd.sys.core.properties.typehandler;

import com.hd.sys.core.properties.TypeHandler;

/**
 * Double类型处理
 * @author sunjian
 * @version V1.0, 2013-5-24 上午11:41:53
 */
public class DoubleTypeHandler implements TypeHandler {

	@Override
	public Object toObj(String str) {
		return Double.parseDouble(str);
	}

	@Override
	public String toStr(Object obj) {
		return String.valueOf(obj);
	}

}
