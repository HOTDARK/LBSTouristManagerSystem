package com.hd.sfw.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * properties文件工具类
 *
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-19 下午2:32:32
 */
public class PropertiesUtils {
	
	private final static Logger log = Logger.getLogger(PropertiesUtils.class);
	
	/**
	 * 根据properties文件路径加载并转换为Map形式返回
	 * @param path
	 * @return
	 */
	public static Map<String, String> getMap(String path){
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			return getMapByInputStream(in);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
			return new HashMap<String, String>();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 从InputStream中解析Properties文件，并存入map中返回
	 * @param in
	 * @return
	 */
	public static Map<String, String> getMapByInputStream(InputStream in){
		Map<String, String> map = new HashMap<String, String>();
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(in, "utf-8"));
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		
		for(String key : properties.stringPropertyNames()){
			map.put(key, properties.getProperty(key));
		}
		
		return map;
	}
	
	/**
	 * 根据资源文件URL加载为Properties
	 * @param name
	 * @return
	 */
	public static Properties getProperties(String name){
		Properties prop = new Properties();
		InputStream in = null;
		try{
			in = new FileInputStream(name);
			prop.load(in);
		}catch (IOException e) {
			log.error(e.getMessage(),e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}
	
	/**
	 * 根据资源文件名字加载为Properties
	 * @param name
	 * @return
	 */
	public static Properties getProperties(String name,Class<?> clz){
		Properties prop = new Properties();
		InputStream in = null;
		try{
			in = clz.getClassLoader().getResourceAsStream(name+".properties");
			prop.load(in);
		}catch (IOException e) {
			log.error(e.getMessage(),e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}
	
	/**
	 * 
	 * <p>描述：web环境读取配置文件的属性值 </p>
	 * <p>日期：2014-12-22 下午03:09:24 </p>
	 * @param clz 类
	 * @param fileName 文件名,不用加后缀
	 * @param key 要获取的键
	 * @return 返回属性值
	 * @throws Exception
	 */
	public static String getProperty(Class<?> clz, String fileName, String key) {
		String property = null;
		InputStream input = null;
		try {
			input = clz.getClassLoader().getResourceAsStream(fileName+".properties");
			Properties properties = new Properties();
			properties.load(input);
			property = properties.getProperty(key);
		} catch (Exception e) {
			log.error("读取配置文件出错："+e.getMessage(), e);
		} finally{
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				log.error("关闭文件流出错："+e.getMessage(), e);
			}
		}
		return property;
	}
	
	/**
	 * 写入properties信息
	 * 
	 * @param key
	 *            名称
	 * @param value
	 *            值
	 */
	public static void modifyProperties(Class<?> clz,String fileName, String key, String value) {
		try {
			// 从输入流中读取属性列表（键和元素对）
			Properties prop = new Properties();
			prop.load(clz.getClassLoader().getResourceAsStream(fileName+".properties"));
			prop.setProperty(key, value);
			String path = PropertiesUtils.class.getResource("/"+fileName+".properties").getPath();
			FileOutputStream outputFile = new FileOutputStream(path);
			prop.store(outputFile, "modify");
			outputFile.close();
			outputFile.flush();
		} catch (Exception e) {
		}
	}
}
