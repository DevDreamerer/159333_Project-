package com.nzHub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzHub.entity.Product;

/**
 * <p>
 *  Mapper
 * </p>
 *
 */
public interface ProductMapper extends BaseMapper<Product> {
    public Integer updateStockById(Integer id,Integer stock);
    public Integer getStockById(Integer id);
}
