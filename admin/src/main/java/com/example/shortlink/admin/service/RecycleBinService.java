package com.example.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageRecycleBinReqDTO;

public interface RecycleBinService {
    Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param);
}
