package com.xie.tsa.service;

import com.xie.tsa.entity.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> getNotices(Notice notice,int start,int length);
    Boolean insertNotice(Notice notice);
    void deleteNotice(Integer Id) throws Exception;

    Notice findNoticeByPk(Integer id);

    void updateNotice(Notice exitNotice);
}
