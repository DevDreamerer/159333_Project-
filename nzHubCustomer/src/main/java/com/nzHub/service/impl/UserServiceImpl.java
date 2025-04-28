package com.nzHub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.User;
import com.nzHub.exception.nzHubException;
import com.nzHub.form.UserLoginForm;
import com.nzHub.form.UserRegisterForm;
import com.nzHub.mapper.UserMapper;
import com.nzHub.result.ResponseEnum;
import com.nzHub.service.UserService;
import com.nzHub.utils.MD5Util;
import com.nzHub.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param userRegisterForm
     * @return
     */
    @Override
    public User register(UserRegisterForm userRegisterForm) {
        //用户名是否可用
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userRegisterForm.getLoginName());
        User one = this.userMapper.selectOne(queryWrapper);
        if(one != null){
            log.info("【用户注册】用户名已存在");
            throw new nzHubException(ResponseEnum.USERNAME_EXISTS);
        }
        //邮箱格式校验
        if(!RegexValidateUtil.checkEmail(userRegisterForm.getEmail())){
            log.info("【用户注册】邮箱格式错误");
            throw new nzHubException(ResponseEnum.EMAIL_ERROR);
        }
        //手机格式校验
        if(!RegexValidateUtil.checkMobile(userRegisterForm.getMobile())){
            log.info("【用户注册】手机格式错误");
            throw new nzHubException(ResponseEnum.MOBILE_ERROR);
        }
        //存储数据
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        user.setPassword(MD5Util.getSaltMD5(user.getPassword()));
        int insert = this.userMapper.insert(user);
        if(insert != 1){
            log.info("【用户注册】添加用户失败");
            throw new nzHubException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return user;
    }

    /**
     * 用户登录
     * @param userLoginForm
     * @return
     */
    @Override
    public User login(UserLoginForm userLoginForm) {
        //判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userLoginForm.getLoginName());
        User user = this.userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("【用户登录】用户名不存在");
            throw new nzHubException(ResponseEnum.USERNAME_NOT_EXISTS);
        }
        //判断密码是否正确
        boolean saltverifyMD5 = MD5Util.getSaltverifyMD5(userLoginForm.getPassword(), user.getPassword());
        if(!saltverifyMD5){
            log.info("【用户登录】密码错误");
            throw new nzHubException(ResponseEnum.PASSWORD_ERROR);
        }
        return user;
    }
}
