package com.example.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shortlink.project.dao.entity.ShortLinkDO;
import com.example.shortlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkRecoverFromRecycleBinReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shortlink.project.dto.resp.ShortLinkPageRecycleBinRespDTO;

public interface RecycleBinService extends IService<ShortLinkDO> {
    void saveShortLinkToRecycleBin(ShortLinkSaveToRecycleBinReqDTO param);

    IPage<ShortLinkPageRecycleBinRespDTO> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param);

    void recoverShortLinkFromRecycleBin(ShortLinkRecoverFromRecycleBinReqDTO param);

    void removeShortLinkFromRecycleBin(ShortLinkRecycleBinRemoveReqDTO param);
}
