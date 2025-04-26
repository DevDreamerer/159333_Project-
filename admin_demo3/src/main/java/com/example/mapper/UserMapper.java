package com.example.mapper;

import com.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */


public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
    int insert(User user);
    void update(User user);
    void delete(Integer id);
}
