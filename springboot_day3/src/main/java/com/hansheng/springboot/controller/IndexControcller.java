package com.hansheng.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControcller {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/errorPage")
    public String errorPage(){
        System.out.println("errorPage");
        return "errorPage";
    }


}
