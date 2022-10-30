package com.wen.richresource.entity;

import com.wen.releasedao.core.annotation.CreateTime;
import com.wen.releasedao.core.annotation.IdField;
import com.wen.releasedao.core.annotation.TableName;
import com.wen.releasedao.core.annotation.UpdateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("resource")
public class ResourceEntity {
    @IdField
    private Integer id;
    private Integer targetId;
    private String name;
    private String type;
    private Boolean valid;
    private String resourceLink;
    private Boolean deleted;
    @CreateTime
    private Date createTime;
    @UpdateTime
    private Date updateTime;
}
