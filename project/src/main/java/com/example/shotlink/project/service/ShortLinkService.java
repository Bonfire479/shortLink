package com.example.shotlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import com.example.shotlink.project.dto.req.*;
import com.example.shotlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDO> {
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO param);

    ShortLinkCreateBatchRespDTO createShortLinkBatch(ShortLinkCreateBatchReqDTO param);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO param);

    List<ShortLinkGroupCountRespDTO> groupCountShortLink(List<String> gidList);

    void updateShortLink(UpdateShortLinkReqDTO param);

    Boolean changeShortLinkGroup(ShortLinkChangeGroupReqDTO param);

    void restoreUrl(String shortUrl, ServletRequest request, ServletResponse response);
}
