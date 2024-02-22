package com.example.shortlink.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.remote.ShortLinkRemoteService;
import com.example.shortlink.admin.remote.dto.req.ShortLinkCreateBatchReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkCreateBatchRespDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.example.shotlink.project.common.convention.result.Results;
import com.example.shotlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private static final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };
    @GetMapping("/api/short-link/admin/v1/ss")
    public void getShortLink(){

    }

    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO param){
        return shortLinkRemoteService.createShortLink(param);
    }

    /*@PostMapping("/api/short-link/admin/v1/create/batch")
    public Result<ShortLinkCreateBatchRespDTO> createShortLinkBatch(@RequestBody ShortLinkCreateBatchReqDTO param){
        return Results.success(shortLinkService.createShortLinkBatch(param));
    }*/

    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO param){
        return shortLinkRemoteService.pageShortLink(param);
    }

    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountRespDTO>> groupCountShortLink(@RequestParam("gidList") List<String> gidList){
        return shortLinkRemoteService.groupCountShortLink(gidList);
    }

}
