package com.xie.tsa.service.impl;

import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.entity.SysRole;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysRoleService;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.dao.NoticeMapper;
import com.xie.tsa.entity.Notice;
import com.xie.tsa.service.NoticeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    OnlineUserManager onlineUserManager;
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    /**
     * 获取公告
     *
     * @param notice
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<Notice> getNotices(Notice notice, int start, int length) {
        return noticeMapper.findByPage(notice, start, length);
    }

    /**
     * 插入一条公告
     *
     * @param notice
     * @return
     */
    public Boolean insertNotice(Notice notice) {
        //判断权限
        SysUser currentUser = onlineUserManager.getCurrentUser();
        logger.info("当前管理员为："+currentUser.getUserName());
        notice.setUserName(currentUser.getUserName());
        SysUser rolesByUserId;
        SysRole byId = null;
        try {
            rolesByUserId = sysUserService.getRolesByUserId(currentUser.getUserId()).get(0);
            logger.info("roleId为：" + rolesByUserId.getRoleId());
            byId = sysRoleService.findById(rolesByUserId.getRoleId());
            logger.info(currentUser.getRoleId() + " roleID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (!"1".equals(byId.getRoleType())) {
                return false;
            }
            if (notice.getCreatedBy() == null) {
                notice.setCreatedBy(Integer.parseInt(currentUser.getUserId() + ""));
            }
            notice.setCreatedTime(new Date());
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            notice.setStrTime(simple.format(new Date()));
            noticeMapper.insert(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteNotice(Integer id) throws Exception {
        if (!isPermit()) {
            return;
        }
        if (id == null) {
            return;
        }
        noticeMapper.deleteByPK(id);
    }


    /**
     * 管理员权限查询
     * @return
     * @throws Exception
     */
    private boolean isPermit() throws Exception {
        /**
         * 当前系统一个用户只有一个角色,暂时先去第一个
         */
        SysUser currentUser = onlineUserManager.getCurrentUser();
        List<SysUser> rolesByUserId = sysUserService.getRolesByUserId(currentUser.getUserId());
        for (SysUser sysUser :
                rolesByUserId) {
            logger.info("当前用户RoleID为：" +sysUser.getRoleId());
            if(sysUser.getRoleId() == 1){
                return true;
            }
        }
        return false;
    }

    public Notice findNoticeByPk(Integer id){
        return noticeMapper.findByPK(id);
    }


    @Override
    public void updateNotice(Notice exitNotice) {
        noticeMapper.updateByPK(exitNotice);
    }
}
