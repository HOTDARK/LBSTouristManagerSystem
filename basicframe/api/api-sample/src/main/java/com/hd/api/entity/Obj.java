package com.hd.api.entity;

import java.util.Arrays;

public class Obj {
	private String aab;
	private String[] abc;

	public String getAab() {
		return aab;
	}

	public void setAab(String aab) {
		this.aab = aab;
	}

	public String[] getAbc() {
		return abc;
	}

	public void setAbc(String[] abc) {
		this.abc = abc;
	}

	@Override
	public String toString() {
		return "Obj [aab=" + aab + ", abc=" + Arrays.toString(abc) + "]";
	}

	
	
}
