package com.hd.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.dao.SysPropMapper;
import com.hd.sys.entity.SysProp;
import com.hd.sys.service.base.BaseService;

/**
 * @version	0.0.1
 * @author	somnuscy
 * @date	2014-9-2 下午2:15:22
 */
@Service
public class SysPropServiceImpl implements BaseService<SysProp, String> {

	@Autowired
	private SysPropMapper sysPropMapper;

	@Override
	public void save(SysProp entity) throws Exception {
		sysPropMapper.insert(entity);
	}
	
	@Override
	public void deleteById(String id) throws Exception {
		sysPropMapper.deleteByPK(id);
	}
	
	@Override
	public void updateById(SysProp p) {
		sysPropMapper.updateByPK(p);
	}

	@Override
	public SysProp findById(String id) throws Exception {
		return null;
	}

	@Override
	public List<SysProp> findByCondition(SysProp entity) throws Exception {
		return sysPropMapper.findByCondition(entity);
	}

	@Override
	public Pagination<SysProp> findPageByCondition(SysProp entity) throws Exception {
		return null;
	}

}
