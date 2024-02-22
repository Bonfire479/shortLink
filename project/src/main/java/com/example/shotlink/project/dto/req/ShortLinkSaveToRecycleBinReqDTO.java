package com.example.shotlink.project.dto.req;


import lombok.Data;

@Data
public class ShortLinkSaveToRecycleBinReqDTO {
    private String gid;
    private String fullShortUrl;
}
