package com.example.zipkinservice2;

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
public class ZipkinService2Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinService2Application.class, args);
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

    @GetMapping(value="/calledBy")
    public String calledBy()
    {
        LOG.info("zipkinService2 is called by..");

        return "Hello...";
    }

    @GetMapping(value="/call")
    public String call() {
        LOG.info("Inside zipkinService 2..");
        String response = (String) restTemplate.exchange("http://localhost:8081/calledBy",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        return "Hi...";
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
