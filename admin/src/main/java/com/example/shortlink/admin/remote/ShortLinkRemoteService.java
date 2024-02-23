package com.example.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.example.shortlink.project.dto.resp.ShortLinkGroupCountRespDTO;

import java.util.HashMap;
import java.util.List;


public interface ShortLinkRemoteService {
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO param){

        String resultJsonStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(param));
        return JSON.parseObject(resultJsonStr, new TypeReference<Result<ShortLinkCreateRespDTO>>() {
        });
    }
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO param){
        HashMap<String, Object> map = new HashMap<>();
        map.put("gid", param.getGid());
        map.put("current", param.getCurrent());
        map.put("size", param.getSize());
        String resultJsonStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page", map);
        return JSON.parseObject(resultJsonStr, new TypeReference<Result<IPage<ShortLinkPageRespDTO>>>() {
        });
    }

    default Result<List<ShortLinkGroupCountRespDTO>> groupCountShortLink(List<String> gidList){
        HashMap<String, Object> map = new HashMap<>();
        map.put("gidList", gidList);
        String resultJsonStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count", map);

        return JSON.parseObject(resultJsonStr, new TypeReference<Result<List<ShortLinkGroupCountRespDTO>>>() {
        });
    }
}
