/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.utils.tree;

import java.util.List;


/**
 * 构造包含子节点的树结构
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-11-6 上午11:55:52
 */
public interface CommonTree {
	
	public int getId();
	
	public int getPid();
	
	public void addChilds(List<? extends CommonTree> childs);
}
