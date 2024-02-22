package com.example.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shortlink.admin.dao.entity.GroupDO;
import com.example.shortlink.admin.dto.req.GroupDeleteReqDTO;
import com.example.shortlink.admin.dto.req.GroupSaveReqDTO;
import com.example.shortlink.admin.dto.req.GroupSortReqDTO;
import com.example.shortlink.admin.dto.req.GroupUpdateReqDTO;
import com.example.shortlink.admin.dto.resp.ListGroupRespDTO;

import java.util.List;

public interface GroupService extends IService<GroupDO> {
    Boolean addGroup(GroupSaveReqDTO param);

    Boolean addGroup(String username, GroupSaveReqDTO param);

    List<ListGroupRespDTO> listGroupByUsername();

    Boolean updateGroup(GroupUpdateReqDTO param);

    Boolean deleteGroup(GroupDeleteReqDTO param);

    void sortGroup(List<GroupSortReqDTO> param);
}
