package com.example.mapper;

import com.example.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-04-07
 */
public interface ProductMapper extends BaseMapper<Product> {
    public List<Product> selectAll();
}
