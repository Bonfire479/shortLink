package com.example.shortlink.admin.dto.req;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupSaveReqDTO {
    /**
     * 短链接分组名
      */
    private String name;

}
