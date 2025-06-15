package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.ProductCategory;
import com.nzHub.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  service class
 * </p>
 *
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> buildProductCategoryMenu();
    public List<ProductCategoryVO> findAllProductByCategoryLevelOne();
}
