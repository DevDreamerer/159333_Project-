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
    public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * primary key
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * login name
     */
      private String loginName;

      /**
     * user name
     */
      private String userName;

      /**
     * password
     */
      private String password;

      /**
     * gender(1:male 0：female)
     */
      private Integer gender;

      /**
     * email
     */
      private String email;

      /**
     * phone number
     */
      private String mobile;

    private String fileName;

      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;


}
