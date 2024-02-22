package com.example.shortlink.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDO extends BaseDO{
    @TableId(type = IdType.AUTO)
    private Long id;
    private String gid;
    private String name;
    private String username;
    private Integer sortOrder;
    private Boolean delFlag;
}
