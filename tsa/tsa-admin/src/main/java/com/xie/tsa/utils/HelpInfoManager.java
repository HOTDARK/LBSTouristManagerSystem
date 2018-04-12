package com.xie.tsa.utils;


import com.xie.tsa.entity.HelpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HelpInfoManager {
    private static Logger logger = LoggerFactory.getLogger(HelpInfoManager.class);
    private static HelpInfoManager helpInfoManager  = new HelpInfoManager();
    private static Map<String, HelpInfo> map_1 = new HashMap<>();//存放未分配的帮助消息
    private static Map<String, HelpInfo> map_2 = new HashMap<>();//存放已经分配但是未解决的消息
    private static Map<String,String> relation = new HashMap<>();
    private HelpInfoManager() {

    }

    public static HelpInfoManager getInstance() {
        return helpInfoManager;
    }

    public void setData(Map<String, HelpInfo> map) {
        this.map_1 = map;
    }

    public void remove(String heipInfoId) {
        if (map_1 != null) {
            map_1.remove(heipInfoId);
        }
    }

    public void addHelpInfo(HelpInfo helpInfo) {
        map_1.put(helpInfo.getHelpInfoId(), helpInfo);
    }

    public HelpInfo getHelpInfoById(String id) {
        if (map_1 == null)
            map_1 = new HashMap<>();
        return map_1.get(id);
    }

    public List<HelpInfo> getHelpInfo(int start, int end) {
        Collection<HelpInfo> values = map_1.values();
        Collection<HelpInfo> values1 = map_2.values();
        //values.addAll(values1);
        List<HelpInfo> list = new ArrayList<>(values);
        list.addAll(values1);
        Collections.sort(list, new Comparator<HelpInfo>() {
            @Override
            public int compare(HelpInfo o1, HelpInfo o2) {
                return o1.getReleaseTime().compareTo(o2.getReleaseTime());
            }
        });
        List<HelpInfo> minList = new ArrayList<>();
        for (int i = start; i < end & i < list.size(); i++) {
            minList.add(list.get(i));
        }
        return minList;
    }

    public Boolean isEmpty() {
        if (map_1.size() == 0)
            return true;
        return false;
    }

    public Integer count() {
        return map_1.size();
    }

    public void removeAll() {
        map_1.clear();
        map_2.clear();
    }

    /**
     * 调整帮助信息当前状态
     * @param helpInfoId
     */
    public void changeSate(String helpInfoId,String touristTel,String workerTel) {
        HelpInfo helpInfo = map_1.get(helpInfoId);
        if (helpInfo != null) {
            map_2.put(helpInfoId, helpInfo);
            map_1.remove(helpInfoId);
            relation.put(workerTel,touristTel);
        }

    }

    public void delete(String helpInfoId) {
        map_1.remove(helpInfoId);
        map_2.remove(helpInfoId);
    }

    public HelpInfo getRecentHelpInfo(String longitude, String latitude) {
        logger.info(longitude + "," + latitude);
        double jingdu = Double.parseDouble(longitude);
        double weidu = Double.parseDouble(latitude);
        if (map_1.size() > 0) {
            Set<String> keySet = map_1.keySet();
            double distance = 1000000000;
            HelpInfo recentHelpInfo = null;
            for (String key :
                    keySet) {
                HelpInfo helpInfo = map_1.get(key);
                String[] split = helpInfo.getReleasePlace().split(",");
                double jingduHelp = Double.parseDouble(split[0]);
                double weiduHelp = Double.parseDouble(split[1]);

                double distance2 = (jingdu - jingduHelp) * (jingdu - jingduHelp) + (weidu - weiduHelp) * (weidu - weiduHelp);
                if (distance2 < distance) {
                    distance = distance2;
                    recentHelpInfo = helpInfo;
                }
            }
            return recentHelpInfo;
        } else {
            return null;
        }
    }

    /**
     * 解除与游客的临时性关联
     * @param workTelphone
     */
    public void removeRelation(String workTelphone){
        relation.remove(workTelphone);
    }

    public String getRelatedTelphone(String workTelphone){
        return relation.get(workTelphone);
    }

    public String getWorkerTelphone(String touristTelphone){
        for (String key:
                relation.keySet()) {
            if(touristTelphone ==relation.get(key))
                return relation.get(key);
        }
        return null;
    }
}
