package com.bookstore.admin.login.dao;

import com.bookstore.commons.beans.User;

public interface IAdminUserDao {
    //管理员登录验证
    User selectUserByLogin(User user);
}
