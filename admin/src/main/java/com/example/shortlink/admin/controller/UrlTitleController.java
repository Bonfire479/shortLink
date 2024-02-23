package com.example.shortlink.admin.controller;

import com.example.shortlink.admin.remote.UrlTitleRemoteService;
import com.example.shortlink.project.common.convention.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlTitleController {
    private final UrlTitleRemoteService urlTitleRemoteService = new UrlTitleRemoteService() {
    };

    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url){
        return urlTitleRemoteService.getTitleByUrl(url);
    }

}
