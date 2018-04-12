package com.hd.api.entity.vo;

import java.util.List;

import com.hd.api.entity.UserHarvestAddr;
import com.hd.sfw.core.model.Pagination;

@SuppressWarnings("serial")
public class UserHarvestAddrList extends Pagination<UserHarvestAddr> implements java.io.Serializable {
	
	private List<UserHarvestAddr> list;

	public List<UserHarvestAddr> getList() {
		return list;
	}

	public void setList(List<UserHarvestAddr> list) {
		this.list = list;
	}

   
}
