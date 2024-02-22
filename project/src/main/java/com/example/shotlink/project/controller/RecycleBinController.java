package com.example.shotlink.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.common.convention.result.Results;
import com.example.shotlink.project.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shotlink.project.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shotlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveShortLinkToRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        recycleBinService.saveShortLinkToRecycleBin(param);
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param){
        return Results.success(recycleBinService.pageShortLinkInRecycleBin(param));
    }

    /*@PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverShortLinkFromRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        Results.success(recycleBinService.recoverShortLinkFromRecycleBin(param);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeShortLinkFromRecycleBin(@RequestBody ShortLinkSaveToRecycleBinReqDTO param){
        Results.success(recycleBinService.recoverShortLinkFromRecycleBin(param);
        return Results.success();
    }*/
}
