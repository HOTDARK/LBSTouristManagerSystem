package com.xie.tsa.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;
import com.xie.tsa.entity.PlaceInfo;

import java.util.List;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2018-03-17
 */
public interface PlaceInfoMapper extends BaseMapper<PlaceInfo,Integer>{
    List<PlaceInfo> findAll();
}
