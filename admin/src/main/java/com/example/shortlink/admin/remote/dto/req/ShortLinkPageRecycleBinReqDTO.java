package com.example.shortlink.admin.remote.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShortLinkPageRecycleBinReqDTO extends Page<ShortLinkDO> {
    private List<String> gidList;
}