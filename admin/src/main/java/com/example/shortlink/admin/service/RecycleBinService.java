package com.example.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkPageReqDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface RecycleBinService {
    Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param);
}
