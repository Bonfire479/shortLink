package com.example.shortlink.admin.remote.dto.resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LinkAccessStatsByDateRespDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private long pv;
    private long uv;
    private long uip;
}
