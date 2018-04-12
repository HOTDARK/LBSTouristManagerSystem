package com.hd.sfw.core.utils;

import java.io.UnsupportedEncodingException;

public class EncodingUtils {  
	/**
	 * 中文乱码转换
	 * @param str
	 * @return
	 */
    public static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
} 