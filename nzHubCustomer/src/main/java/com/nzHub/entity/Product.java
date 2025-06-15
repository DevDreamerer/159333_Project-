package com.nzHub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Product implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * primary key
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * name
     */
      private String name;

      /**
     * description
     */
      private String description;

      /**
     * price
     */
      private Float price;

      /**
     * stock
     */
      private Integer stock;

      /**
     * category one
     */
      private Integer categoryleveloneId;

      /**
     * category two
     */
      private Integer categoryleveltwoId;

      /**
     * category three
     */
      private Integer categorylevelthreeId;

      /**
     * file name
     */
      private String fileName;

      /**
       * brand
       */
      private String brand;

      /**
       * origin
       */
      private String origin;

      /**
       * rating
       */
      private Float rating;

  @TableField("shelf_time")  // Specify database column names explicitly
  private LocalDateTime shelfTime; 

}
