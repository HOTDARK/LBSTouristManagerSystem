package com.hd.tsa.service.web.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.tsa.dao.WebNewsMapper;
import com.hd.tsa.entity.WebNews;
import com.hd.tsa.service.web.WebNewsService;
import com.hd.sfw.core.model.Pagination;

@Service
public class WebNewsServiceImpl implements WebNewsService{

	@Autowired
	private WebNewsMapper webNewsMapper;
	
	@Override
	public void save(WebNews entity) throws Exception {
		try {
			webNewsMapper.insert(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		try {
			webNewsMapper.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateById(WebNews entity) throws Exception {
		try {
			if(entity.getPublishFlag() != null && entity.getPublishFlag() == 1){
				entity.setPublishTime(new Date());
			}else{
				entity.setPublishTime(null);
			}
			webNewsMapper.updateByPK(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public WebNews findById(Long id) throws Exception {
		return webNewsMapper.findByPK(id);
	}

	@Override
	public List<WebNews> findByCondition(WebNews entity) throws Exception {
		return webNewsMapper.findByCondition(entity);
	}

	@Override
	public Pagination<WebNews> findPageByCondition(WebNews entity) throws Exception {
		int num = webNewsMapper.findNumByCondition(entity);
		if(num > 0){
			return new Pagination<>(num, webNewsMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength()));
		}else{
			return new Pagination<>(0, new ArrayList<>());
		}
	}

}
