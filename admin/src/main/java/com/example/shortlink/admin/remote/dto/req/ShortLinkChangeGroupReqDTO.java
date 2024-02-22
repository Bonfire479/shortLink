package com.example.shortlink.admin.remote.dto.req;


import lombok.Data;

@Data
public class ShortLinkChangeGroupReqDTO {
    private String oldGid;
    private String newGid;
    private String fullShortUrl;
}
