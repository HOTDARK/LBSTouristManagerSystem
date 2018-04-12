package com.hd.api.entity.vo;

import java.util.List;

import com.hd.api.entity.UserBackRelation;
import com.hd.sfw.core.model.Pagination;

@SuppressWarnings("serial")
public class UserBackRelationList extends Pagination<UserBackRelation> implements java.io.Serializable {
	
	private List<UserBackRelation> list;

	public List<UserBackRelation> getList() {
		return list;
	}

	public void setList(List<UserBackRelation> list) {
		this.list = list;
	}

   
}
