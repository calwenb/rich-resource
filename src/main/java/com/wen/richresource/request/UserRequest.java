package com.wen.richresource.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull(message = "id不能为空 ")
    private Integer id;

    @NotBlank(message = "用户名不能为空 ", groups = {register.class, addAdmin.class})
    private String name;

    @NotBlank(message = "账号不能为空 ", groups = {login.class, register.class, addAdmin.class})
    private String account;

    @NotBlank(message = "密码不能为空 ", groups = {login.class, register.class, upPassword.class, addAdmin.class})
    private String password;

    @NotBlank(message = "新密码不能为空 ", groups = {upPassword.class})
    private String newPassword;

    private String type;
    private String phone;
    private String email;
    private Boolean remember;

    public interface login {
    }

    public interface register {
    }

    public interface upPassword {
    }

    public interface updateUser {
    }

    public interface addAdmin {

    }
}
