package com.example.service;

import com.example.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.ProductCategory;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
public interface ProductService extends IService<Product> {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    void deleteProduct(Integer id);

    List<Product> findByCategoryId(int level, int categoryId);
    List<Product> searchByName(String keyword);

    List<ProductCategory> getCategoriesByParentId(Integer parentId);
    List<ProductCategory> getCategoriesByType(Integer type);
}
