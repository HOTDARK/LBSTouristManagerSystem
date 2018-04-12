package com.xie.tsa.utils;

import com.xie.tsa.entity.UserLocationRecord;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationManager {

    private static LocationManager locationManager = new LocationManager();
    Map<String, UserLocationRecord> touristMap = new HashMap<>();
    Map<String, UserLocationRecord> workMap = new HashMap<>();

    private LocationManager() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static synchronized LocationManager getInstance() {
        return locationManager;
    }

    public void addUserLocationRecord(UserLocationRecord userLocationRecord) {
        touristMap.put(userLocationRecord.getTelPhone(), userLocationRecord);
    }

    public void removeAll() {
        touristMap.clear();
        workMap.clear();

    }

    public void addWorkLocationRecord(UserLocationRecord userLocationRecord) {
        workMap.put(userLocationRecord.getTelPhone(), userLocationRecord);
    }

    /**
     * 获取游客的位置信息
     * @param telphone
     * @return
     */
    public UserLocationRecord getTouristLocationRecord(String telphone) {
        return touristMap.get(telphone);
    }

    /**
     * 获取工作人员的位置信息
     * @param telphone
     * @return
     */
    public UserLocationRecord getWorkerLocationRecord(String telphone) {
        return workMap.get(telphone);
    }
}
