package com.example.service;

import com.example.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-04-07
 */
public interface ProductService extends IService<Product> {
    public List<Product> queryAll();
}
