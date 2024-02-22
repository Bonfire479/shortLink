package com.example.shortlink.admin.dto.resp;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListGroupRespDTO {
    private String gid;
    private String name;
    private Integer sortOrder;
    private long shortLinkCount;
}
