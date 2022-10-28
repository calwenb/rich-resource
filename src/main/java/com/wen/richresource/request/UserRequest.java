package com.wen.richresource.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserRequest {
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

    @NotNull(message = "id不能为空 ")
    private Integer id;

    @NotBlank(message = "用户名不能为空 ", groups = {register.class, addAdmin.class})
    private String userName;

    @NotBlank(message = "账号不能为空 ", groups = {login.class, register.class, addAdmin.class})
    private String loginName;

    @NotBlank(message = "密码不能为空 ", groups = {login.class, register.class, upPassword.class, addAdmin.class})
    private String password;

    @NotBlank(message = "新密码不能为空 ", groups = {upPassword.class})
    private String newPassword;
    @NotBlank(message = "新密码不能为空 ", groups = {updateUser.class})
    private Integer userType;
    private String phoneNumber;
    private String email;
    private String avatar;
    private Date registerTime;
    private Boolean remember;

}
