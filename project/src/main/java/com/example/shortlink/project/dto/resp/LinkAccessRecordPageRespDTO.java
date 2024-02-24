package com.example.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class LinkAccessRecordPageRespDTO {
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    private String accessTime;
    private String ip;
    private String locale;
    private String os;
    private String browser;
    private String device;
    private String network;
    private String userType;
}
