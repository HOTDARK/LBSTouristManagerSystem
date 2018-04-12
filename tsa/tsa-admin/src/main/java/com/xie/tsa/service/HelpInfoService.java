package com.xie.tsa.service;

import com.xie.tsa.entity.HelpInfo;

import java.util.List;

public interface HelpInfoService {
    HelpInfo addHelpInfo(HelpInfo helpInfo);

    Boolean changeState(HelpInfo helpInfo);

    List<HelpInfo> findHelpInfoByActive(int start,int end);

    Integer helpInfoCount();

    HelpInfo assignedTask(String workTelphone);

    void finishHelpInfo(String helpInfoId,String telphone);

    void touristFinishHelpInfo(String helpInfoId);

    List<HelpInfo> getSelfHelpInfo(String telphone);
}
