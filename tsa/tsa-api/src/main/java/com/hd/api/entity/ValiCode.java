package com.hd.api.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * 验证码实体类
 * @author somnuscy
 *
 */
public class ValiCode {
	
	private static int EXPTHRESHOLD = -5;
	private String mobile;
	private String valiCode;
	private Date expTime;
	
	public ValiCode(String mobile, String valiCode){
		this.mobile = mobile;
		this.valiCode = valiCode;
		this.expTime = new Date();
	}
	
	public String getMobile() {
		return mobile;
	}

	public String getValiCode() {
		return valiCode;
	}

	public Date getExpTime() {
		return expTime;
	}
	
	/**
	 * 判断验证码是否过期
	 * @return
	 */
	public boolean isExpired(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, EXPTHRESHOLD);
		Date date = calendar.getTime();
		return date.after(this.expTime);
	}
	
}
