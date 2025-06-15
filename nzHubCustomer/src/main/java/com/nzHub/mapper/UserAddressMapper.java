package com.nzHub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzHub.entity.UserAddress;

/**
 * <p>
 *  Mapper
 * </p>
 *
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {
    public int setDefault();
}
