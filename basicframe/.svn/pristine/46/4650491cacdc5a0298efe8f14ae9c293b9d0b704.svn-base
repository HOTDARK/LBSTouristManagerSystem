/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 通用io工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-1 下午2:53:12
 */
public class IOUtils extends org.apache.commons.io.IOUtils{
	
	/**
	 * 获取classpath下的resource
	 * @param resource
	 * @return
	 */
	public static byte[] readFromResource(String resource) throws IOException{
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            if (in == null) {
                return null;
            }

            return toByteArray(in);
        } finally {
            closeQuietly(in);
        }
	}
	
	/**
	 * 获取文件后缀名 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName){
		int pos = fileName.lastIndexOf(".");
		return pos>-1?fileName.substring(pos+1):null;
	}
}
