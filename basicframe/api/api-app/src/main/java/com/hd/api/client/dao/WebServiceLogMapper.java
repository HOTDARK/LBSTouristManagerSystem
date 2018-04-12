package com.hd.api.client.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hd.api.client.entity.SysWebServiceLog;
import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.sfw.webservice.model.WebServiceLog;

public interface WebServiceLogMapper extends BaseMapper<SysWebServiceLog,Long>{

	void insertWebServiceLog(WebServiceLog log);

	List<String> getSchemaTitles();

	List<Map<String,String>> getDiagnosisLogReport(@Param(value="startTime")String startTime , @Param(value="endTime")String endTime);
}
