package com.hd.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.dao.SysLogFuncMapper;
import com.hd.sys.entity.SysLogFunc;
import com.hd.sys.entity.vo.SysLogFuncCountVo;
import com.hd.sys.service.SysLogFuncService;

/**
 * <p>类名：SysLogService </p>
 * <p>描述：系统日志业务层实现类 </p>
 * <p>作者：jl </p>
 * <p>时间：2015年1月7日 星期三 </p>
 */
@Service
public class SysLogFuncServiceImpl implements SysLogFuncService {

	@Autowired
	private SysLogFuncMapper sysLogFuncMapper;

	@Override
	public void save(SysLogFunc entity) throws Exception {
		
	}
	
	@Override
	public List<SysLogFuncCountVo> countDescSysLogByArea(SysLogFuncCountVo sysLog) {
		return sysLogFuncMapper.countDescSysLogByArea(sysLog);
	}

	@Override
	public void insertLog(SysLogFunc sysLog) {
		sysLogFuncMapper.insert(sysLog);	
	}
	
	@Override
	public List<SysLogFuncCountVo> countSysLogByArea(SysLogFuncCountVo sysLog) {
		return sysLogFuncMapper.countSysLogByArea(sysLog);
	}

/*	@Override
	public List<WorkflowProcess> getAllProcessWithoutDetail() {
		return logMapper.getAllProcessWithoutDetail();
	}*/

	@Override
	public SysLogFunc findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysLogFunc> findByCondition(SysLogFunc entity) throws Exception {
		return sysLogFuncMapper.findByCondition(entity);
	}

	@Override
	public void updateById(SysLogFunc entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination<SysLogFunc> findPageByCondition(SysLogFunc entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
