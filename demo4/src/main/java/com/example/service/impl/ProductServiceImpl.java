package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;







/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2025-04-07
 */
@Service
@Transactional

public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> queryAll() {
        return productMapper.selectAll();
    }
}
