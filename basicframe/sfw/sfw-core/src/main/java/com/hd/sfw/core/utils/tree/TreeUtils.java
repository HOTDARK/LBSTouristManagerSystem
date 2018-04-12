/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils.tree;

import java.util.ArrayList;
import java.util.List;

import com.hd.sfw.core.utils.BeanUtils;
import com.hd.sfw.core.utils.BeanUtils.BeanConverter;

/**
 * 树结构工具类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-11-6 上午11:55:52
 */
public class TreeUtils {
	
	public static <T> List<CommonTree> build(List<T> list,int pid,BeanConverter<T, ? extends CommonTree> converter){
		return build(BeanUtils.convert(list, converter),pid);
	}
	
	public static List<CommonTree> build(List<? extends CommonTree> list,int pid){
		List<CommonTree> departmentVos = new ArrayList<CommonTree>();
		
		//查找根节点
		for(CommonTree t : list ){
			if(t.getPid()==pid){
				departmentVos.add(t);
				t.addChilds(build(list,t.getId()));
			}
		}
		
		return departmentVos;
	}
	
}
