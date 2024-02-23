package com.example.shortlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShortLinkNotFoundController {
    @GetMapping("/page/notfound")
    public String notFound(){
        return "notfound";
    }
}
