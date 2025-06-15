package com.nzHub.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserLoginForm {
    @NotEmpty(message = "user name can not be empty")
    private String loginName;
    @NotEmpty(message = "password can not be empty")
    private String password;
}
