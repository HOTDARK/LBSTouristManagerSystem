package com.hd.api.entity.vo;

import java.util.List;

import com.hd.api.entity.UserInfo;
import com.hd.sfw.core.model.Pagination;

@SuppressWarnings("serial")
public class UserInfoList extends Pagination<UserInfo> implements java.io.Serializable {
	
	private List<UserInfo> list;

	public List<UserInfo> getList() {
		return list;
	}

	public void setList(List<UserInfo> list) {
		this.list = list;
	}
   
}
