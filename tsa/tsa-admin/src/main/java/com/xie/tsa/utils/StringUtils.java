package com.xie.tsa.utils;

import com.xie.tsa.entity.PlaceInfo;

import java.util.List;
import java.util.Map;

public class StringUtils {
    public static Boolean isEmpty(String str){
        if(str == null | "".equals(str)){
            return true;
        }
        return false;
    }

    public static Boolean isEmpty(Integer str){
        if(str == null){
            return true;
        }
        return false;
    }

    public static<T> Boolean isEmpty(List<T> list){
        if(list == null || list.size()<= 0){
            return true;
        }
        return false;
    }

    public static<T> Boolean isEmpty(Map<String,Object> map){
        if(map == null || map.size()<= 0){
            return true;
        }
        return false;


    }

    public static Boolean isEmpty(Object obj){
        return obj == null ? true:false;
    }

    public static Boolean isEmpty(PlaceInfo obj){
        if(isEmpty(obj.getRemark())&isEmpty(obj.getMapName())&isEmpty(obj.getImageUrl())&isEmpty(obj.getCreatedBy())){
            return true;
        }else{
            return false;
        }
    }
}
