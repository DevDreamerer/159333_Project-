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
    public class OrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * primary key
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * order primary key
     */
      private Integer orderId;

      /**
     * product primary key
     */
      private Integer productId;

      /**
     * number
     */
      private Integer quantity;

      /**
     * cost
     */
      private Float cost;


}
