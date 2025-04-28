package com.nzHub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nzHub.entity.ProductCategory;
import com.nzHub.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> buildProductCategoryMenu();
    public List<ProductCategoryVO> findAllProductByCategoryLevelOne();
}
