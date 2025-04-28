package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.Product;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface ProductService extends IService<Product> {
    public List<Product> findAllByTypeAndProductCategoryId(Integer type,Integer id);
}
