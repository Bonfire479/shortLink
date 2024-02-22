package com.example.shortlink.admin.controller;


import com.example.shortlink.admin.common.convention.result.Result;
import com.example.shortlink.admin.common.convention.result.Results;
import com.example.shortlink.admin.dto.req.GroupDeleteReqDTO;
import com.example.shortlink.admin.dto.req.GroupSaveReqDTO;
import com.example.shortlink.admin.dto.req.GroupSortReqDTO;
import com.example.shortlink.admin.dto.req.GroupUpdateReqDTO;
import com.example.shortlink.admin.dto.resp.ListGroupRespDTO;
import com.example.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Boolean> addGroup(@RequestBody GroupSaveReqDTO param){
        return Results.success(groupService.addGroup(param));
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ListGroupRespDTO>> listGroupByUsername(){
        return Results.success(groupService.listGroupByUsername());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Boolean> updateGroup(@RequestBody GroupUpdateReqDTO param){
        return Results.success(groupService.updateGroup(param));
    }

    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Boolean> deleteGroup(@RequestBody GroupDeleteReqDTO param){

        return Results.success(groupService.deleteGroup(param));
    }

    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<GroupSortReqDTO> param){
        groupService.sortGroup(param);
        return Results.success();
    }

}
