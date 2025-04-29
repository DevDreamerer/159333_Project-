package com.example.mapper;

import com.example.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
public interface ProductMapper extends BaseMapper<Product> {

    // 自定义查询：根据分类 ID 查询商品
    List<Product> findByCategoryId(@Param("categoryLevel") int level, @Param("categoryId") int categoryId);

    // 自定义查询：根据名称模糊搜索
    List<Product> searchByName(@Param("keyword") String keyword);

}
