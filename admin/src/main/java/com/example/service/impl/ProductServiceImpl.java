package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.mapper.ProductCategoryMapper;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getId() == null) {
            productMapper.insert(product);
        } else {
            productMapper.updateById(product);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryId(int level, int categoryId) {
        //return productMapper.findByCategoryId(level, categoryId);
        List<Product> products = productMapper.findByCategoryId(level, categoryId);
        // Ensure that a non-null list is returned
        return products != null ? products : new ArrayList<>();
    }

    @Override
    public List<Product> searchByName(String keyword) {
        //return productMapper.searchByName(keyword);
        List<Product> products = productMapper.searchByName(keyword);
        // Ensure that a non-null list is returned
        return products != null ? products : new ArrayList<>();
    }

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> getCategoriesByParentId(Integer parentId) {
        QueryWrapper<ProductCategory> query = new QueryWrapper<>();
        query.eq("parent_id", parentId);
        return productCategoryMapper.selectList(query);
    }

    @Override
    public List<ProductCategory> getCategoriesByType(Integer type) {
        QueryWrapper<ProductCategory> query = new QueryWrapper<>();
        query.eq("type", type);
        return productCategoryMapper.selectList(query);
    }

}
