package com.example.shortlink.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.project.common.convention.result.Result;
import com.example.shortlink.project.common.convention.result.Results;

import com.example.shortlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkRecoverFromRecycleBinReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import com.example.shortlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shortlink.project.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveShortLinkToRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        recycleBinService.saveShortLinkToRecycleBin(param);
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param){
        return Results.success(recycleBinService.pageShortLinkInRecycleBin(param));
    }

    @PostMapping("/api/short-link/v1/recycle-bin/recover")
    public Result<Void> recoverShortLinkFromRecycleBin(@RequestBody ShortLinkRecoverFromRecycleBinReqDTO param){
        recycleBinService.recoverShortLinkFromRecycleBin(param);
        return Results.success();
    }

    @PostMapping("/api/short-link/v1/recycle-bin/remove")
    public Result<Void> removeShortLinkFromRecycleBin(@RequestBody ShortLinkRecycleBinRemoveReqDTO param){
        recycleBinService.removeShortLinkFromRecycleBin(param);
        return Results.success();
    }
}
