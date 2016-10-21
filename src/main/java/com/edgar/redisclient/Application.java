package com.edgar.redisclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        SpringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
    }
}
