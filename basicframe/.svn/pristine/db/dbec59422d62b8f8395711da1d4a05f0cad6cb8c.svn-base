package com.hd.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.cache.CacheEvict;
import com.hd.sfw.core.cache.Cacheable;
import com.hd.sfw.core.model.Pagination;
import com.hd.sys.dao.SysDictMapper;
import com.hd.sys.entity.SysDict;
import com.hd.sys.service.SysDictService;

/**
 * <p>类名：SysDictServiceImpl </p>
 * <p>描述：业务类型业务层实现类 </p>
 * <p>作者：somnuscy </p>
 */
@Service
public class SysDictServiceImpl implements SysDictService {
	
	@Autowired
	private SysDictMapper sysDictMapper;
	
	@Override
	@CacheEvict(value="globalCache", prefix="#sys.type.dict")
	public void save(SysDict entity) throws Exception {
		//修改父节点类型
		String pid = entity.getParentTypeDictCode();
		SysDict parentDict = sysDictMapper.findByPK(pid);
		if (parentDict != null && parentDict.getTypeLeafNode() == 1L) {
			parentDict.setTypeLeafNode(0L);
			sysDictMapper.updateByPK(parentDict);
		}
		entity.setTypeLeafNode(1L);
		sysDictMapper.insert(entity);
	}

	@Override
	@CacheEvict(value="globalCache", prefix="#sys.type.dict")
	public void updateById(SysDict typeDict) throws Exception {
		sysDictMapper.updateByPK(typeDict);
	}

	@Override
	@CacheEvict(value="globalCache", prefix="#sys.type.dict")
	public void updateDictActivate(SysDict typeDict) throws Exception {
		typeDict.setState(1L);
		sysDictMapper.updateByPK(typeDict);
		typeDict = sysDictMapper.findByPK(typeDict.getTypeDictCode());
		if (typeDict != null) {
			SysDict sysTypeDict = new SysDict();
			sysTypeDict.setTypeDictCode(typeDict.getParentTypeDictCode());
			updateDictActivate(sysTypeDict);
		}
	}
	
	@Override
	@CacheEvict(value="globalCache", prefix="#sys.type.dict")
	public void updateDictFreeze(SysDict typeDict) throws Exception {
		typeDict.setState(0L);
		sysDictMapper.updateByPK(typeDict);
		SysDict pType = new SysDict();
		pType.setParentTypeDictCode(typeDict.getTypeDictCode());
		List<SysDict> typeDicts = sysDictMapper.findByCondition(pType);
		if (typeDicts != null && typeDicts.size() > 0) {
			for (SysDict td : typeDicts) {
				updateDictFreeze(td);
			}
		}
	}

	@Override
	public SysDict findById(String id) throws Exception {
		return sysDictMapper.findByPK(id);
	}

	@Override
	public void deleteById(String id) throws Exception {
		
	}
	
	@Override
	@Cacheable(value="globalCache", prefix="#sys.type.dict")
	public List<SysDict> findSysDictByParent(String parentCode) {
		return sysDictMapper.findSysDictByParent(parentCode);
	}
	
	@Override
	public List<SysDict> findByAppFlag(String parentCode, String applicationFlag) throws Exception {
		return sysDictMapper.findByAppFlag(parentCode, applicationFlag);
	}

	@Override
	public List<SysDict> findByCondition(SysDict entity) throws Exception {
		return sysDictMapper.findByCondition(entity);
	}

	@Override
	public Pagination<SysDict> findPageByCondition(SysDict entity) throws Exception {
		Integer rowCount = sysDictMapper.findNumByCondition(entity);
		List<SysDict> rowList = new ArrayList<SysDict>();
		if(rowCount>0){
			if (entity.getParentTypeDictCode().equals("0")) {
				rowList = sysDictMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			}else{
				rowList = sysDictMapper.findByPage(entity, 0,0);
			}
		}
		return new Pagination<SysDict>(rowCount, rowList);
	}
	
}