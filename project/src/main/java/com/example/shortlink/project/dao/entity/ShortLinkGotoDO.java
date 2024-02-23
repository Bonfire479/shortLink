package com.example.shortlink.project.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_link_goto")
public class ShortLinkGotoDO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String fullShortUrl;
    private String gid;
}
