/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.commons;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.IOUtils;
import com.hd.sfw.core.utils.PropertiesUtils;

/**
 * 
 * @version	0.0.1
 */
public class AppCommon {
	
	public final static String BASE_PATH = "workflow";
	
	//资源是否对所有用户可见
	private static boolean isShowAll;
	
	//如果资源不是对所有用户可见，那么对特定用户可见则用户id包含在数组中
	private static List<String> exceptUserList; 
	
	static{
		InputStream in = AppCommon.class.getClassLoader().getResourceAsStream("workflow-app.properties");
		Map<String, String> map = PropertiesUtils.getMapByInputStream(in);
		IOUtils.closeQuietly(in);
		isShowAll = Boolean.valueOf(map.get("workflow.app.showAll"));
		String ids = map.get("workflow.app.except.uid");
		if(StringUtils.isNotEmpty(ids)){
			String[] arr = ids.split(",");
			exceptUserList = new ArrayList<String>();
			for(String str :arr){
				exceptUserList.add(str);
			}
		}
	}
	
	/**
	 * 用户是否可以查看所有资源，如果是为false则只显示用户关联的信息
	 * @param uid
	 * @return
	 */
	public static boolean isShowAll(String uid){
		if (CollectionUtils.isEmpty(exceptUserList)) {
			return false;
		}
		return isShowAll || exceptUserList.contains(uid);
	}
	
	
}
