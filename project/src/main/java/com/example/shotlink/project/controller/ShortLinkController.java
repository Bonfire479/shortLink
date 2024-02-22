package com.example.shotlink.project.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.common.convention.result.Results;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import com.example.shotlink.project.dto.req.*;
import com.example.shotlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRespDTO;
import com.example.shotlink.project.service.ShortLinkService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    @GetMapping("/api/short-link/v1/ss")
    public void getShortLink(){

    }

    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO param){
        return Results.success(shortLinkService.createShortLink(param));
    }

    @PostMapping("/api/short-link/v1/create/batch")
    public Result<ShortLinkCreateBatchRespDTO> createShortLinkBatch(@RequestBody ShortLinkCreateBatchReqDTO param){
        return Results.success(shortLinkService.createShortLinkBatch(param));
    }

    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO param){
        return Results.success(shortLinkService.pageShortLink(param));
    }

    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountRespDTO>> groupCountShortLink(@RequestParam("gidList") List<String> gidList){
        return Results.success(shortLinkService.groupCountShortLink(gidList));
    }

    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(UpdateShortLinkReqDTO param){
        shortLinkService.updateShortLink(param);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/changeGroup")
    public Result<Boolean> changeShortLinkGroup(ShortLinkChangeGroupReqDTO param){
        return Results.success(shortLinkService.changeShortLinkGroup(param));
    }

    @GetMapping("/{shortUrl}")
    public void restoreUrl(@PathVariable String shortUrl, ServletRequest request, ServletResponse response){
        shortLinkService.restoreUrl(shortUrl, request, response);
    }

}
