package com.nzHub.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
    public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * primary key
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * user primary key
     */
      private Integer userId;

      /**
     * user name
     */
      private String loginName;

      /**
     * user address
     */
      private String userAddress;

      /**
     * total cost
     */
      private Float cost;

      /**
     * order number
     */
      private String serialnumber;

      /**
     * create time
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * update time
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;


}
