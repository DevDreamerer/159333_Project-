package com.nzHub.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserRegisterForm {
    @NotEmpty(message = "login name can not be empty")
    private String loginName;
    @NotEmpty(message = "user name can not be empty")
    private String userName;
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotNull(message = "gender can not be empty")
    private Integer gender;
    @NotEmpty(message = "email can not be empty")
    private String email;
    @NotEmpty(message = "phone number can not be empty")
    private String mobile;
}
