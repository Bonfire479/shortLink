package com.example.shotlink.project.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class ShortLinkPageRecycleBinReqDTO extends Page<ShortLinkDO> {
    private List<String> gidList;
}