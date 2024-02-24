package com.example.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.project.common.convention.result.Result;
import com.example.shortlink.project.common.convention.result.Results;
import com.example.shortlink.project.dto.req.*;
import com.example.shortlink.project.dto.resp.*;
import com.example.shortlink.project.service.ShortLinkService;

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
        Result<IPage<ShortLinkPageRespDTO>> result = Results.success(shortLinkService.pageShortLink(param));
        String res = "1;";
        return result;
    }

    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountRespDTO>> groupCountShortLink(@RequestParam("gidList") List<String> gidList){
        return Results.success(shortLinkService.groupCountShortLink(gidList));
    }

    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(UpdateShortLinkReqDTO param){
        shortLinkService.updateShortLink(param);
        return Results.success();
    }

    @PostMapping("/api/short-link/v1/changeGroup")
    public Result<Boolean> changeShortLinkGroup(ShortLinkChangeGroupReqDTO param){
        return Results.success(shortLinkService.changeShortLinkGroup(param));
    }

    @GetMapping("/{shortUrl}")
    public void restoreUrl(@PathVariable String shortUrl, ServletRequest request, ServletResponse response){
        shortLinkService.restoreUrl(shortUrl, request, response);
    }

    @GetMapping("/api/short-link/v1/all-stats")
    public Result<LinkAllStatsByDateRespDTO> getLinkAllStatsByDate(LinkAllStatsByDateReqDTO param){
        return Results.success(shortLinkService.getLinkAllStatsByDate(param));
    }

    @GetMapping("/api/short-link/admin/v1/stats/access-record")
    public Result<IPage<LinkAccessRecordPageRespDTO>> pageLinkAccessRecord(LinkAccessRecordPageReqDTO param){
        return Results.success(shortLinkService.pageLinkAccessRecord(param));
    }
}
