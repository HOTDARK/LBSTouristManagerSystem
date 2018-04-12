package com.xie.tsa.service.impl;

import com.hd.sys.dao.SysUserMapper;
import com.hd.sys.entity.SysUser;
import com.xie.tsa.dao.HelpInfoMapper;
import com.xie.tsa.dao.WorkerHlepRecordMapper;
import com.xie.tsa.entity.HelpInfo;
import com.xie.tsa.entity.UserLocationRecord;
import com.xie.tsa.entity.WorkerHlepRecord;
import com.xie.tsa.service.HelpInfoService;
import com.xie.tsa.service.TsaUserService;
import com.xie.tsa.utils.HelpInfoManager;
import com.xie.tsa.utils.LocationManager;
import com.xie.tsa.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelpInfoServiceImpl implements HelpInfoService {

    @Autowired
    HelpInfoMapper helpInfoMapper;

    @Autowired
    WorkerHlepRecordMapper workerHlepRecordMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    TsaUserService tsaUserService;

    private static Logger logger = LoggerFactory.getLogger(HelpInfoServiceImpl.class);

    /**
     * private String helpInfoId;
     * private Integer createdBy;
     * private String releasePlace;
     * private String remark;
     * private String multimedia;
     * private java.util.Date releaseTime;
     * private String userName;
     * private int state;
     *
     * @param helpInfo
     * @return
     */
    @Override
    public HelpInfo addHelpInfo(HelpInfo helpInfo) {
        if (helpInfo.getCreatedBy() == null) {
            return null;
        }
        helpInfo.setHelpInfoId(UUIDUtils.getUUID());
        helpInfo.setReleaseTime(new Date());
        helpInfo.setState(0);//0表示未解决
        HelpInfoManager.getInstance().addHelpInfo(helpInfo);
        helpInfoMapper.insert(helpInfo);
        return helpInfo;
    }


    @Override
    public Boolean changeState(HelpInfo helpInfo) {
        if (helpInfo.getHelpInfoId() != null) {
            HelpInfo byPK = helpInfoMapper.findByPK(helpInfo.getHelpInfoId());
            if (byPK != null) {
                byPK.setState(1);
                helpInfoMapper.updateByPK(byPK);
                logger.info(byPK.toString());
            }
            HelpInfoManager.getInstance().delete(helpInfo.getHelpInfoId());
            return true;
        }
        return false;
    }


    @Override
    public List<HelpInfo> findHelpInfoByActive(int start, int end) {
        HelpInfoManager instance = HelpInfoManager.getInstance();
        if (instance.isEmpty()) {
            HelpInfo helpInfo = new HelpInfo();
            helpInfo.setState(0);
            List<HelpInfo> byCondition = helpInfoMapper.findByCondition(helpInfo);
            for (HelpInfo helpInfo1 :
                    byCondition) {
                instance.addHelpInfo(helpInfo1);
            }
        }
        return instance.getHelpInfo(start, end);
    }

    @Override
    public Integer helpInfoCount() {
        return HelpInfoManager.getInstance().count();
    }

    /**
     * 分配最近的帮助任务
     *
     * @param workTelphone
     * @return
     */
    @Override
    public HelpInfo assignedTask(String workTelphone) {
        UserLocationRecord workerLocationRecord = LocationManager.getInstance().getWorkerLocationRecord(workTelphone);//根据电话号码查询工作人员的位置和状态
        if (workerLocationRecord.getState() == 1) {//状态为1，不再安排工作人员
            logger.info(workTelphone + "忙碌");
            return null;
        }
        logger.info(workTelphone + "空闲");
        logger.info(workerLocationRecord.toString());
        String jingdu = workerLocationRecord.getUserLongitude();
        String weidu = workerLocationRecord.getUserLatitude();
        logger.info(jingdu + "," + weidu);
        //寻找离此工作人员最近的未安排的求助信息
        HelpInfo recentHelpInfo = HelpInfoManager.getInstance().getRecentHelpInfo(jingdu, weidu);
        if (recentHelpInfo != null) {
            //如果得到的最近的求助消息不为null，则改变此消息状态，分配给这个工作者，并改变这个工作者的状态
            SysUser tourist = sysUserMapper.findByPK((long) recentHelpInfo.getCreatedBy());//这是发布者
            if (tourist == null) {
                return null;
            }
            HelpInfoManager.getInstance().changeSate(recentHelpInfo.getHelpInfoId(), tourist.getTelephone(), workTelphone);
            LocationManager.getInstance().getWorkerLocationRecord(workTelphone).setState(1);
            //插入帮助信息表
            WorkerHlepRecord record = new WorkerHlepRecord();
            //根据电话号码，查出用户和工作人员的id

            SysUser worker = sysUserMapper.findByTelphone(workTelphone);

            record.setRecordId(UUIDUtils.getUUID());
            record.setHelpInfoId(recentHelpInfo.getHelpInfoId());
            record.setTouristId(recentHelpInfo.getCreatedBy());
            record.setWorkerId(Integer.parseInt(worker.getUserId() + ""));
            workerHlepRecordMapper.insert(record);
            return recentHelpInfo;
        }
        return null;
    }

    @Override
    public void finishHelpInfo(String helpInfoId, String telphone) {
        SysUser sysUser = new SysUser();
        sysUser.setTelephone(telphone);
        SysUser byTelphone = sysUserMapper.findByTelphone(telphone);

        WorkerHlepRecord record = new WorkerHlepRecord();
        record.setHelpInfoId(helpInfoId);
        record.setWorkerId(Integer.parseInt(byTelphone.getUserId() + ""));

        //根据helpInfoId和工作人员电话号码查出帮助记录，取第一条
        //设置完成时间更新
        List<WorkerHlepRecord> byCondition1 = workerHlepRecordMapper.findByCondition(record);
        if (byCondition1 != null) {
            for (WorkerHlepRecord workerHlepRecord :
                    byCondition1) {
                workerHlepRecord.setFinishedTime(new Date());
                workerHlepRecordMapper.updateByPK(workerHlepRecord);
            }
        }

        HelpInfo info = new HelpInfo();
        info.setHelpInfoId(helpInfoId);
        changeState(info);

        //更新工作人员状态
        UserLocationRecord workerLocationRecord = LocationManager.getInstance().getWorkerLocationRecord(telphone);
        workerLocationRecord.setState(0);
        LocationManager.getInstance().addWorkLocationRecord(workerLocationRecord);

        //解除与游客临时性的关联
        HelpInfoManager.getInstance().removeRelation(telphone);
    }


    @Override
    public void touristFinishHelpInfo(String helpInfoId) {
        //跟新表中的状态
        HelpInfo byPK = helpInfoMapper.findByPK(helpInfoId);

        if (byPK == null) {
            return;
        }
        byPK.setState(1);
        logger.info("查找到的helpinfo" + byPK);
        helpInfoMapper.updateByPK(byPK);
        //删除管理者中的帮助信息
        HelpInfoManager.getInstance().delete(helpInfoId);
        //插入工作者的帮助记录
        WorkerHlepRecord record = new WorkerHlepRecord();
        record.setHelpInfoId(helpInfoId);
        List<WorkerHlepRecord> byCondition = workerHlepRecordMapper.findByCondition(record);
        if (byCondition != null & byCondition.size() > 0) {
            WorkerHlepRecord record1 = byCondition.get(0);
            record1.setFinishedTime(new Date());
            workerHlepRecordMapper.updateByPK(record1);

            //通过userId,查找工作人员信息，
            Integer workerId = record1.getWorkerId();
            SysUser sysUser = new SysUser();
            sysUser.setUserId((long) workerId);
            List<SysUser> byCondition1 = sysUserMapper.findByCondition(sysUser);
            if (byCondition1 != null & byCondition1.size() > 0) {
                sysUser = byCondition1.get(0);
                //设置工作人员的状态为空闲
                UserLocationRecord workerLocationRecord = LocationManager.getInstance()
                        .getWorkerLocationRecord(sysUser.getTelephone());
                workerLocationRecord.setState(0);
                LocationManager.getInstance().addWorkLocationRecord(workerLocationRecord);

                //解除与游客临时性的关联
                HelpInfoManager.getInstance().removeRelation(sysUser.getTelephone());
            }
        }


    }

    @Override
    public List<HelpInfo> getSelfHelpInfo(String telphone) {
        HelpInfo info = new HelpInfo();
        info.setUserName(telphone);
        return helpInfoMapper.findByCondition(info);
    }
}
