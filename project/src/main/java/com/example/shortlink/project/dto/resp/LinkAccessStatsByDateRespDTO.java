package com.example.shortlink.project.dto.resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class LinkAccessStatsByDateRespDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private long pv;
    private long uv;
    private long uip;
}
