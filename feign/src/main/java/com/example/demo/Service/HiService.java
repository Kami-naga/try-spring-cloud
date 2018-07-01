package com.example.demo.Service;

import com.example.demo.Service.Impl.HiServiceHystricImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.hi",fallback = HiServiceHystricImpl.class)
@Service
public interface HiService {

    @GetMapping(value="/hi")
    String sayHiFromClientOne(@RequestParam(value="name")String name);
}
