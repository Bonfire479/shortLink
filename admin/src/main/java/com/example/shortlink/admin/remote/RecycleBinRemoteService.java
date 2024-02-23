package com.example.shortlink.admin.remote;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.common.convention.result.Results;
import com.example.shortlink.admin.remote.dto.req.ShortLinkPageRecycleBinReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkSaveToRecycleBinReqDTO;
import com.example.shortlink.admin.remote.dto.resp.ShortLinkPageRecycleBinRespDTO;
import com.example.shortlink.admin.remote.dto.req.ShortLinkRecoverFromRecycleBinReqDTO;
import java.util.HashMap;

public interface RecycleBinRemoteService {
    default Result<Void> saveShortLinkToRecycleBin(ShortLinkSaveToRecycleBinReqDTO param) {
        HashMap<String, Object> map = new HashMap<>();
        String resultJsonStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/save", JSON.toJSONString(param));
        return Results.success();
    }

    default Result<IPage<ShortLinkPageRecycleBinRespDTO>> pageShortLinkInRecycleBin(ShortLinkPageRecycleBinReqDTO param) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("gidList", param.getGidList());
        map.put("current", param.getCurrent());
        map.put("size", param.getSize());

        String resultJsonStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/page", map);

        Result<IPage<ShortLinkPageRecycleBinRespDTO>> parseObject = JSON.parseObject(resultJsonStr, new TypeReference<Result<IPage<ShortLinkPageRecycleBinRespDTO>>>() {
        });


        return parseObject;
    }

    default Result<Void> recoverShortLinkFromRecycleBin(ShortLinkRecoverFromRecycleBinReqDTO param) {
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/recover", JSON.toJSONString(param));
        return Results.success();
    }

    default Result<Void> removeShortLinkFromRecycleBin(ShortLinkRecycleBinRemoveReqDTO param) {
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/remove", JSON.toJSONString(param));
        return Results.success();
    }

}