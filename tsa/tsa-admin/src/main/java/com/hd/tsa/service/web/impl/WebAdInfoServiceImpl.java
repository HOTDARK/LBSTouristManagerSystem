package com.hd.tsa.service.web.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WebAdInfoMapper;
import com.hd.tsa.entity.WebAdInfo;
import com.hd.tsa.service.web.WebAdInfoService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WebAdInfoServiceImpl implements WebAdInfoService {
	
	@Autowired
	private WebAdInfoMapper webAdInfoMapper;

	@Override
	public void save(WebAdInfo entity) throws Exception {
		try {
			webAdInfoMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			webAdInfoMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WebAdInfo entity) throws Exception {
		try {
			webAdInfoMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WebAdInfo findById(Long id) throws Exception {
		return webAdInfoMapper.findByPK(id);
	}

	@Override
	public List<WebAdInfo> findByCondition(WebAdInfo entity) throws Exception {
		return webAdInfoMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WebAdInfo> findPageByCondition(WebAdInfo entity) throws Exception {
		int num = webAdInfoMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<WebAdInfo>(0, new ArrayList<WebAdInfo>());
		} else {
			List<WebAdInfo> webAdInfos = webAdInfoMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<WebAdInfo>(num, webAdInfos);
		}
	}

}
