package com.example.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.remote.RecycleBinRemoteService;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.admin.service.RecycleBinService;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkPageReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.RemoteServer;

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
        Result<IPage<ShortLinkPageRecycleBinRespDTO>> iPageResult = recycleBinService.pageShortLinkInRecycleBin(param);
        return iPageResult;
    }

    /*@PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Boolean> recoverShortLinkFromRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        return Results.success(recycleBinRemoteService.recoverShortLinkFromRecycleBin(param));
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Boolean> removeShortLinkFromRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        return Results.success(recycleBinRemoteService.removeShortLinkFromRecycleBin(param));
    }*/
}
