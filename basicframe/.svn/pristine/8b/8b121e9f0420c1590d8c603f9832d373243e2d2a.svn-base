/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;

import com.hd.sfw.core.utils.DateUtils;

/**
 * springmvc 参数直接转换为日期
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-11-14 上午10:52:02
 */
public class SpringMVCDateConvert implements Converter<String, Date> {
	static String dateReg = "[\\d]{4}-[\\d]{2}-[\\d]{2}";
	static String timeHHmmReg = "[\\d]{2}:[\\d]{2}";
	static String timeHHmmssReg = "[\\d]{2}:[\\d]{2}:[\\d]{2}";

	static Pattern pDate = Pattern.compile(dateReg);
	static Pattern pDateHhMm = Pattern.compile(dateReg+" "+timeHHmmReg);
	static Pattern pDateHhMmSs = Pattern.compile(dateReg+" "+timeHHmmssReg);
	static Pattern pDateHhMmSsZ1 = Pattern.compile(dateReg+"T"+timeHHmmssReg+"Z");
	static Pattern pDateHhMmSsZ2 = Pattern.compile(dateReg+"T"+timeHHmmssReg+"Z");
	static Pattern pDateHhMmSsZ3 = Pattern.compile(dateReg+"T"+timeHHmmssReg+"z");
	static Pattern pDateHhMmSsZ4 = Pattern.compile(dateReg+"T"+timeHHmmssReg);

	@Override
	public Date convert(String source) {
		
		if (pDate.matcher(source).matches()) {
			 return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD);	
		} else if (pDateHhMm.matcher(source).matches()) {
			 return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM);	
		}else if (pDateHhMmSs.matcher(source).matches()) {
			 return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM_SS);	
		}else if (pDateHhMmSsZ1.matcher(source).matches()) {
			 return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM_SS_Z1);	
		}else if (pDateHhMmSsZ2.matcher(source).matches()) {
			 return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM_SS_Z2);	
		}else if (pDateHhMmSsZ3.matcher(source).matches()) {
			return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM_SS_Z3);	
		}else if (pDateHhMmSsZ4.matcher(source).matches()) {
			return DateUtils.parseDate(source, DateUtils.SDF_YYYY_MM_DD_HH_MM_SS_Z4);	
		}
		return null;
			
	}


}
