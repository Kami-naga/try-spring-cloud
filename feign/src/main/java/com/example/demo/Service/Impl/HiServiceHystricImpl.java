package com.example.demo.Service.Impl;

import com.example.demo.Service.HiService;
import org.springframework.stereotype.Component;

@Component
public class HiServiceHystricImpl implements HiService {

    @Override
    public String sayHiFromClientOne(String name){
        return "sorry "+ name + "\nsomething wrong with your service(feign hystrix fallback)";
    }
}
