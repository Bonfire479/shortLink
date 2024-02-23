package com.example.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shortlink.project.dao.entity.ShortLinkDO;
import com.example.shortlink.project.dto.req.*;
import com.example.shortlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import com.example.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.example.shortlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.example.shortlink.project.dto.resp.ShortLinkPageRespDTO;
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
