package com.staroot.h2multi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    SampleDataQueryService sdqService;
    @GetMapping("/test1")
    public void test(){
        sdqService.queryData();
    }
    @GetMapping("/test2")
    public void home(){
        sdqService.queryData();
    }
}
