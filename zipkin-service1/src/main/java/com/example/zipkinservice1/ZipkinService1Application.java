package com.example.zipkinservice1;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@SpringBootApplication
public class ZipkinService1Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinService1Application.class, args);
    }
}

@RestController
class ZipkinController{

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    private static final Logger LOG = Logger.getLogger(ZipkinController.class.getName());

    @GetMapping(value="/call")
    public String call() {
        LOG.info("Inside zipkinService1..");

        String response = (String) restTemplate.exchange("http://localhost:8082/calledBy",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        return "Hi...";
    }

    @GetMapping(value="/calledBy")
    public String calledBy() {
        LOG.info("zipkinService1 is Called by..");
        return "Hello...";
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
