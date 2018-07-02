package com.example.demo.Controller;

import com.example.demo.Service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    HiService hiService;
    @RequestMapping("/hi")
    public String hi(@RequestParam String name){
        return hiService.hiService(name);
    }
}
