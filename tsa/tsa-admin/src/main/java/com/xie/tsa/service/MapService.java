package com.xie.tsa.service;

import com.xie.tsa.entity.PlaceInfo;
import com.xie.tsa.entity.PlaceLocation;

import java.util.List;

public interface MapService{
    List<PlaceInfo> findAllPlace();
    Boolean insertByPlaceInfo(PlaceInfo placeInfo);
    Boolean deleteByPlaceInfo(PlaceInfo placeInfo);
    Boolean updateByPlaceInfo(PlaceInfo placeInfo);

    Boolean insertPlaceLocation(PlaceLocation placeLocation);


    List<PlaceLocation> getAllPlace();

    PlaceLocation findPlaceById(Integer placeId);

    void updateByPlace(PlaceLocation exit);

    void deleteByPlace(Integer placeId);
}
