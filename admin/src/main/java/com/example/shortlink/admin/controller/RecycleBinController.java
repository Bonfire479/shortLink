package com.example.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.remote.RecycleBinRemoteService;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkRecoverFromRecycleBinReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.admin.service.RecycleBinService;
import com.example.shortlink.admin.common.convention.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RecycleBinController {
    private final RecycleBinRemoteService recycleBinRemoteService = new RecycleBinRemoteService() {
    };

    private final RecycleBinService recycleBinService;

    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveShortLinkToRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param) {
        return recycleBinRemoteService.saveShortLinkToRecycleBin(param);
    }

    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param) {
        return recycleBinService.pageShortLinkInRecycleBin(param);
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverShortLinkFromRecycleBin(@RequestBody ShortLinkRecoverFromRecycleBinReqDTO param){
        return recycleBinRemoteService.recoverShortLinkFromRecycleBin(param);
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeShortLinkFromRecycleBin(@RequestBody ShortLinkRecycleBinRemoveReqDTO param){
        return recycleBinRemoteService.removeShortLinkFromRecycleBin(param);
    }
}
