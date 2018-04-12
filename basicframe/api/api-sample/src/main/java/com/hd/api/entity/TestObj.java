package com.hd.api.entity;

import java.util.Date;

public class TestObj {
	private Integer aaa;
	private String bbb;
	private Long ccc;
	private Date ddd;
	private Obj obj;
	
	public Integer getAaa() {
		return aaa;
	}

	public void setAaa(Integer aaa) {
		this.aaa = aaa;
	}

	public String getBbb() {
		return bbb;
	}

	public void setBbb(String bbb) {
		this.bbb = bbb;
	}

	public Long getCcc() {
		return ccc;
	}

	public void setCcc(Long ccc) {
		this.ccc = ccc;
	}

	public Date getDdd() {
		return ddd;
	}

	public void setDdd(Date ddd) {
		this.ddd = ddd;
	}

	public Obj getObj() {
		return obj;
	}

	public void setObj(Obj obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "TestObj [aaa=" + aaa + ", bbb=" + bbb + ", ccc=" + ccc + ", ddd=" + ddd + ", obj=" + obj + "]";
	}

	 
	
}
