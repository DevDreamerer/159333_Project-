package com.example.service;

import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
public interface UserService extends IService<User> {
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(Integer id);
}
