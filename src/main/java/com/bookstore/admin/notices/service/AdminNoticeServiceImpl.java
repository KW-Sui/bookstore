package com.bookstore.admin.notices.service;

import com.bookstore.admin.notices.dao.IAdminNoticeDao;
import com.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminNoticeServiceImpl implements IAdminNoticeService{
    @Autowired
    private IAdminNoticeDao iAdminNoticeDao;
    //查询所有公告
    @Override
    public List<Notice> findAllNotices() {
        return iAdminNoticeDao.selectAllNotices();
    }

    //根据id查询公告详细信息
    @Override
    public Notice findByIdNotice(Integer id) {
        return iAdminNoticeDao.selectByIdNotice(id);
    }

    //编辑公告
    @Override
    public int editNotice(Notice notice) {
        return iAdminNoticeDao.updateNotice(notice);
    }

    //添加公告
    @Override
    public int addNotice(Notice notice) {
        return iAdminNoticeDao.insertNotice(notice);
    }

    //删除公告
    @Override
    public int removeNotice(Integer id) {
        return iAdminNoticeDao.deleteNotice(id);
    }
}
