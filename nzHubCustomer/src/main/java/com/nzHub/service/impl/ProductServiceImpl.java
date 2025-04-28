package com.nzHub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.Product;
import com.nzHub.mapper.ProductMapper;
import com.nzHub.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-11-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAllByTypeAndProductCategoryId(Integer type, Integer id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        String column = null;
        switch (type){
            case 1:
                column = "categorylevelone_id";
                break;
            case 2:
                column = "categoryleveltwo_id";
                break;
            case 3:
                column = "categorylevelthree_id";
                break;
        }
        queryWrapper.eq(column,id);
        return this.productMapper.selectList(queryWrapper);
    }
}
