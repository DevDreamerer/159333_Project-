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
 *   service implement class
 * </p>
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * user login
     * @param userRegisterForm
     * @return
     */
    @Override
    public User register(UserRegisterForm userRegisterForm) {
        //check the username is available or not
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userRegisterForm.getLoginName());
        User one = this.userMapper.selectOne(queryWrapper);
        if(one != null){
            log.info("[user registration]user name already exist");
            throw new nzHubException(ResponseEnum.USERNAME_EXISTS);
        }
        //Email format validation
        if(!RegexValidateUtil.checkEmail(userRegisterForm.getEmail())){
            log.info("[user registration]incorrect email format");
            throw new nzHubException(ResponseEnum.EMAIL_ERROR);
        }
        //Phone number format validation
        if(!RegexValidateUtil.checkMobile(userRegisterForm.getMobile())){
            log.info("[user registration]incorrect phone number format");
            throw new nzHubException(ResponseEnum.MOBILE_ERROR);
        }
        //save data
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        user.setPassword(MD5Util.getSaltMD5(user.getPassword()));
        int insert = this.userMapper.insert(user);
        if(insert != 1){
            log.info("[user registration]fail to add new user");
            throw new nzHubException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return user;
    }

    /**
     * user login
     * @param userLoginForm
     * @return
     */
    @Override
    public User login(UserLoginForm userLoginForm) {
        //check username exist or not
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userLoginForm.getLoginName());
        User user = this.userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("[user login]user name already exist");
            throw new nzHubException(ResponseEnum.USERNAME_NOT_EXISTS);
        }
        //check password is correct or not
        boolean saltverifyMD5 = MD5Util.getSaltverifyMD5(userLoginForm.getPassword(), user.getPassword());
        if(!saltverifyMD5){
            log.info("[user login]incorrect password");
            throw new nzHubException(ResponseEnum.PASSWORD_ERROR);
        }
        return user;
    }
}
