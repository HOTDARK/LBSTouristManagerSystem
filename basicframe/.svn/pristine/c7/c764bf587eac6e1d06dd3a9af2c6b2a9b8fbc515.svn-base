/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.sfw.core.utils.IOUtils;


/**
 * mime type
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-1 下午1:47:29
 */
public class MimeTypes {
	private final static Logger log = Logger.getLogger(MimeTypes.class);
	
	/**
	 * 存放按照后缀名=>mimeType
	 */
	private static Map<String, String> mime = new HashMap<String, String>();
	
	
	static{
		init();
	}
	
	private static void init(){
		InputStream in = null;
		BufferedReader reader = null;
		try{
			in = MimeTypes.class.getResourceAsStream("mime.types");
			reader = new BufferedReader(new InputStreamReader(in));
			String str = null;
			while((str=reader.readLine())!=null){
				str = str.trim();
				if(str.startsWith("#")){
					continue;
				}
				
				String items[] = str.split("\\s+");
				if(items.length>1){
					for(int i=1;i<items.length;i++){
						//使用的是apache httpd提供的mime.types文件，
						//第一列为mime,之后为后缀名
						mime.put(items[i].toLowerCase(),items[0]);
					}
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}finally{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(reader);
		}

	}
	
	/**
	 * 根据后缀名获取mime type,后缀名不包含.，如gif,jpg
	 * @param ext
	 * @return
	 */
	public static String getMimeType(String ext){
		if(StringUtils.isEmpty(ext)){
			return null;
		}
		return mime.get(ext.toLowerCase());
	}
	
	public static void main(String[] args) {
		System.out.println(MimeTypes.getMimeType("swf"));
	}
}
