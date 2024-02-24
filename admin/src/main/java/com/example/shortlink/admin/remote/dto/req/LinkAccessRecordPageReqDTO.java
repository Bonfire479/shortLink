package com.example.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shortlink.project.dao.entity.LinkAccessLogsDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LinkAccessRecordPageReqDTO extends Page<LinkAccessLogsDO> {
    private String gid;
    private String fullShortUrl;
    private String startDate;
    private String endDate;
}
