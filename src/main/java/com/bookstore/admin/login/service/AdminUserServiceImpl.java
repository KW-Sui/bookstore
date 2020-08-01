package com.bookstore.admin.login.service;

import com.bookstore.admin.login.dao.IAdminUserDao;
import com.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    private IAdminUserDao iAdminUserDao;
    //管理员登录验证
    @Override
    public User findUserByLogin(User user) {
        return iAdminUserDao.selectUserByLogin(user);
    }
}
