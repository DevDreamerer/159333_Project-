package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("product")
public class Product {
  @TableId(type = IdType.AUTO)
  private Integer id;

  private String name;
  private String description;
  private BigDecimal price;
  private Integer stock;

  @TableField("categorylevelone_id")
  private Integer categoryLevelOneId;

  @TableField("categoryleveltwo_id")
  private Integer categoryLevelTwoId;

  @TableField("categorylevelthree_id")
  private Integer categoryLevelThreeId;

  @TableField("file_name")
  private String fileName;

  private String origin;
  private String brand;

}