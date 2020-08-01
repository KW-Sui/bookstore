package com.bookstore.admin.notices.service;

import com.bookstore.commons.beans.Notice;

import java.util.List;

public interface IAdminNoticeService {
    //查询所有公告
    List<Notice> findAllNotices();

    //根据id查询公告详细信息
    Notice findByIdNotice(Integer id);

    //编辑公告
    int editNotice(Notice notice);

    //添加公告
    int addNotice(Notice notice);

    //删除公告
    int removeNotice(Integer id);
}
