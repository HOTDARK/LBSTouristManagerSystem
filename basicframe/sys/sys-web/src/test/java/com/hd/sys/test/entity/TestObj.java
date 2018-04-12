package com.hd.sys.test.entity;

import java.io.Serializable;

public class TestObj implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String errorcode;
	private String username;
	private String bandwith;
	private String cityname;
	private String downloadurl;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBandwith() {
		return bandwith;
	}

	public void setBandwith(String bandwith) {
		this.bandwith = bandwith;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDownloadurl() {
		return downloadurl;
	}

	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
