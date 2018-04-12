package com.hd.tsa.timer;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.hd.sfw.core.utils.DateUtils;
import com.hd.sfw.core.utils.PropertiesUtils;


public class TimerManager{
	
	private static Logger logger = Logger.getLogger(TimerManager.class);
	
	public void timerStart() {
		logger.info("时间定时任务监听启动了");
	    String dayTime = PropertiesUtils.getProperty(TimerManager.class, "common", "dayTime");
	    int hour = Integer.parseInt(dayTime);
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.HOUR_OF_DAY, hour);  
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date callDate = calendar.getTime(); 
	    if (callDate.before(new Date())) { // 避免立即执行
	    	callDate = DateUtils.addDays(callDate, 1);  
	    }
	    logger.info("定时任务已经加载完成了");
	}
	
}
