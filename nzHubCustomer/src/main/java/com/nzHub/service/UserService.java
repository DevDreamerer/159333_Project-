package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.User;
import com.nzHub.form.UserLoginForm;
import com.nzHub.form.UserRegisterForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface UserService extends IService<User> {
    public User register(UserRegisterForm userRegisterForm);
    public User login(UserLoginForm userLoginForm);
}
