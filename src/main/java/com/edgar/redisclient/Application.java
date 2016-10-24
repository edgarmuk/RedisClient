package com.edgar.redisclient;

import com.edgar.redisclient.service.MessageReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    MessageReceiver receiver(CountDownLatch latch){
        return new MessageReceiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }



    public static void main(String[] args) throws InterruptedException{

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);


        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        template.convertAndSend("chat", "Hello from Redis!");
        latch.await();

    }
}
