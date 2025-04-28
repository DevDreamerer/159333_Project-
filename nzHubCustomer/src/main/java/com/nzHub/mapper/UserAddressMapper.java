package com.nzHub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzHub.entity.UserAddress;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {
    public int setDefault();
}
