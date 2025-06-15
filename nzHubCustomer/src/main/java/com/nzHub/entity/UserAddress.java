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
    public class UserAddress implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * primary key id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * user primary key
     */
      private Integer userId;

      /**
     * address
     */
      private String address;

      /**
     * comment
     */
      private String remark;

      /**
     * whether is the default address（1:yes 0:no）
     */
      private Integer isdefault;

      /**
     * create time
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;


}
