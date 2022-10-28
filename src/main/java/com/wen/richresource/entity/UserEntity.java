package com.wen.richresource.entity;

import com.wen.releasedao.core.annotation.CreateTime;
import com.wen.releasedao.core.annotation.IdField;
import com.wen.releasedao.core.annotation.TableName;
import com.wen.releasedao.core.annotation.UpdateTime;
import com.wen.releasedao.core.enums.IdTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * User实体类
 *
 * @author calwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserEntity implements Serializable {
    @IdField(idType = IdTypeEnum.AUTO)
    private Integer id;
    private String userName;
    private String loginName;
    private String password;
    private Integer userType;
    private String phoneNumber;
    private String email;
    private Boolean deleted;
    @CreateTime
    private Date createTime;
    @UpdateTime
    private Date updateTime;
}
