package com.hd.sys.service;

import java.util.List;

import com.hd.sys.entity.SysLogFunc;
import com.hd.sys.entity.vo.SysLogFuncCountVo;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：ISysLogService </p>
 * <p>描述：系统日志业务层接口类 </p>
 * <p>作者：jl </p>
 * <p>时间：2015年1月7日 星期三 </p>
 */
public interface SysLogFuncService extends BaseService<SysLogFunc, Long> {
	/**
	 * 插入一个用户操作日志
	 * @param id
	 */
	public void insertLog(SysLogFunc sysLog);


/*	public List<WorkflowProcess> getAllProcessWithoutDetail();*/


	public List<SysLogFuncCountVo> countSysLogByArea(SysLogFuncCountVo sysLog);


	public List<SysLogFuncCountVo> countDescSysLogByArea(SysLogFuncCountVo sysLog);
}
