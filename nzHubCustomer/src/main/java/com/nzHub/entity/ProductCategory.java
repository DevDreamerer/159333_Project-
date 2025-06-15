package com.nzHub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ProductCategory implements Serializable {

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
     * parent id
     */
      private Integer parentId;

      /**
     * level(1:one 2：two 3：three)
     */
      private Integer type;


}
