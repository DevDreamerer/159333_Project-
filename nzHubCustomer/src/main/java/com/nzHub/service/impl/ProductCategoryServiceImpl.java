package com.nzHub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nzHub.entity.Product;
import com.nzHub.entity.ProductCategory;
import com.nzHub.mapper.ProductCategoryMapper;
import com.nzHub.mapper.ProductMapper;
import com.nzHub.service.ProductCategoryService;
import com.nzHub.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *   service implement class
 * </p>
 *
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * create product category menu
     * @return
     */
    @Override
    public List<ProductCategoryVO> buildProductCategoryMenu() {
        //1. Query all product category data
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        //2. Convert the datatype into ProductCategoryVO
        List<ProductCategoryVO> productCategoryVOList = productCategoryList.stream().map(ProductCategoryVO::new).collect(Collectors.toList());
        //3. Encapsulate parent-child menus.
        List<ProductCategoryVO> levelOneList = buildMenu(productCategoryVOList);
        return levelOneList;
    }

    @Override
    public List<ProductCategoryVO> findAllProductByCategoryLevelOne() {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 1);
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(queryWrapper);
        List<ProductCategoryVO> productCategoryVOList = productCategoryList.stream().map(ProductCategoryVO::new).collect(Collectors.toList());
        getLevelOneProduct(productCategoryVOList);
        return productCategoryVOList;
    }

    /**
     * check product info for the level one category
     */
    public void getLevelOneProduct(List<ProductCategoryVO> list){
        for (ProductCategoryVO vo : list) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("categorylevelone_id", vo.getId());
            List<Product> productList = this.productMapper.selectList(queryWrapper);
            vo.setProductList(productList);
        }
    }

    /**
     * create menu
     * @param list
     */
    public List<ProductCategoryVO> buildMenu(List<ProductCategoryVO> list){
        //find level one category
        List<ProductCategoryVO> levelOneList = list.stream().filter(c -> c.getParentId() == 0).collect(Collectors.toList());
        for (ProductCategoryVO vo : levelOneList) {
            recursion(list,vo);
        }
        return levelOneList;
    }

    /**
     * Recursive classification
     * @param list
     * @param vo
     */
    public void recursion(List<ProductCategoryVO> list,ProductCategoryVO vo){
        //find submenu
        List<ProductCategoryVO> children = getChildren(list, vo);
        vo.setChildren(children);
        //continue to find subsubmenu
        if(children.size() > 0){
            for (ProductCategoryVO child : children) {
                recursion(list,child);
            }
        }
    }

    /**
     * get submenu
     * @param list
     * @param vo
     */
    public List<ProductCategoryVO> getChildren(List<ProductCategoryVO> list,ProductCategoryVO vo){
        List<ProductCategoryVO> children = new ArrayList<>();
        Iterator<ProductCategoryVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            ProductCategoryVO next = iterator.next();
            if(next.getParentId().equals(vo.getId())){
                children.add(next);
            }
        }
        return children;
    }
}
