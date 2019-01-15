package com.qwerty.todomicroservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableEurekaClient
@EnableFeignClients("com.qwerty.todomicroservice")
@SpringBootApplication
public class TodoMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoMicroserviceApplication.class, args);
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }

}

