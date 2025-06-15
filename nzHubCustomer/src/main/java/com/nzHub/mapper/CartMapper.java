package com.nzHub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzHub.entity.Cart;

/**
 * <p>
 *  Mapper
 * </p>
 */
public interface CartMapper extends BaseMapper<Cart> {
    public int update(Integer id,Integer quantity,Float cost);
    public Float getCostByUserId(Integer id);
}
