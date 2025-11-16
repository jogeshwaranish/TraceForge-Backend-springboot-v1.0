package com.TraceForge.AI;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;

@Configuration
public class config {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        Dotenv dotenv = Dotenv.load();
        String redis_pass = dotenv.get("redis_password");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("redis-17154.c74.us-east-1-4.ec2.cloud.redislabs.com");
        redisStandaloneConfiguration.setPort(17154);
        redisStandaloneConfiguration.setUsername("default");
        redisStandaloneConfiguration.setPassword(redis_pass);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }


}
