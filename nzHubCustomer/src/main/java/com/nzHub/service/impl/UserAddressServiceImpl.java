package com.nzHub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.UserAddress;
import com.nzHub.mapper.UserAddressMapper;
import com.nzHub.service.UserAddressService;
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
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
