package com.example.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.example.shotlink.project.common.convention.result.Result;
import com.example.shotlink.project.common.convention.result.Results;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public interface UrlTitleRemoteService {
    default Result<String> getTitleByUrl(String url){
        String resultJsonStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/title?url="+url);

        return Results.success(JSON.parseObject(resultJsonStr, new TypeReference<String>() {
        }));
    }
}
