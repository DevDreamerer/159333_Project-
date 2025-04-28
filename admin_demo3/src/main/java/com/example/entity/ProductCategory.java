package com.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_category")
public class ProductCategory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer type;
}