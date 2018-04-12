/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.app.model.UserData;


/**
 * 默认实现
 * @version	0.0.1
 */
public class DefaultUserRelationshipMapping implements UserRelationshipMapping{

	@Override
	public String getUid(HttpSession session) {
		return "";
	}

	@Override
	public void fill(List<? extends UserData> list) {
		if(CollectionUtils.isNotEmpty(list)){
			for(UserData data : list){
				data.setUsername("--");
			}
		}
	}

}
