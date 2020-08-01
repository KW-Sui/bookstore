package com.bookstore.admin.notices.dao;

import com.bookstore.commons.beans.Notice;

import java.util.List;

public interface IAdminNoticeDao {
    //查询所有公告
    List<Notice> selectAllNotices();

    //根据id查询公告详细信息
    Notice selectByIdNotice(Integer id);

    //编辑公告
    int updateNotice(Notice notice);

    //添加公告
    int insertNotice(Notice notice);

    //删除公告
    int deleteNotice(Integer id);
}
