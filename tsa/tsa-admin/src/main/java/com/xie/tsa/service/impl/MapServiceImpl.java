package com.xie.tsa.service.impl;

import com.xie.tsa.dao.PlaceInfoMapper;
import com.xie.tsa.dao.PlaceLocationMapper;
import com.xie.tsa.entity.PlaceInfo;
import com.xie.tsa.entity.PlaceLocation;
import com.xie.tsa.service.MapService;
import com.xie.tsa.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    public PlaceInfoMapper mapper;

    @Autowired
    public PlaceLocationMapper locationMapper;

    /**
     * 查找所有的地点类型
     *
     * @return 地点列表
     */
    @Override
    public List<PlaceInfo> findAllPlace() {
        return mapper.findAll();

    }

    /**
     * 插入一个新的地点类型
     *
     * @return 成功与否
     */
    @Override
    public Boolean insertByPlaceInfo(PlaceInfo placeInfo) {

        if (StringUtils.isEmpty(placeInfo)) {
            return false;
        }
        mapper.insert(placeInfo);
        return true;
    }

    /**
     * 删除一个新的地点类型
     *
     * @return 成功与否
     */
    @Override
    public Boolean deleteByPlaceInfo(PlaceInfo placeInfo) {

        if (!StringUtils.isEmpty(placeInfo)) {
            mapper.deleteByPK(placeInfo.getId());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新一个新的地点类型
     *
     * @return 成功与否
     */
    @Override
    public Boolean updateByPlaceInfo(PlaceInfo placeInfo) {
        if (StringUtils.isEmpty(placeInfo)) {
            return false;
        }
        mapper.updateByPK(placeInfo);
        return true;
    }

    /**
     * 增加一出地点
     *
     * @param placeLocation
     * @return
     */
    @Override
    public Boolean insertPlaceLocation(PlaceLocation placeLocation) {
        locationMapper.insert(placeLocation);
        return true;
    }

    @Override
    public List<PlaceLocation> getAllPlace() {
        return locationMapper.findByPage(new PlaceLocation(), 0, 10000);
    }

    /**
     * 根据主键查询地点信息
     *
     * @param placeId
     * @return
     */
    @Override
    public PlaceLocation findPlaceById(Integer placeId) {
        return locationMapper.findByPK(placeId);
    }

    /**
     * 根据主键修改地点信息
     *
     * @param exit
     */
    @Override
    public void updateByPlace(PlaceLocation exit) {
        locationMapper.updateByPK(exit);
    }

    /**
     * 根据主键删除一个地点
     *
     * @param placeId
     */
    @Override
    public void deleteByPlace(Integer placeId) {
        PlaceLocation placeLocation = new PlaceLocation();
        placeLocation.setPlaceId(placeId);
        locationMapper.deleteByPK(placeId);
    }
}
