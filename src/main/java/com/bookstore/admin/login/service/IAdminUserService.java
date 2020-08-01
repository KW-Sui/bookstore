package com.bookstore.admin.login.service;

import com.bookstore.commons.beans.User;

public interface IAdminUserService {
    //管理员登录验证
    User findUserByLogin(User user);
}
