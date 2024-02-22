package com.example.shotlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import com.example.shotlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRecycleBinRespDTO;

public interface RecycleBinService extends IService<ShortLinkDO> {
    void saveShortLinkToRecycleBin(ShortLinkSaveToRecycleBinReqDTO param);

    IPage<ShortLinkPageRecycleBinRespDTO> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param);

    void recoverShortLinkFromRecycleBin(ShortLinkSaveToRecycleBinReqDTO param);

    void removeShortLinkFromRecycleBin(ShortLinkSaveToRecycleBinReqDTO param);
}
