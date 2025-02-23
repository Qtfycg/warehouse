package com.qtfycg.back.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration

public class redis {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate<String, Object>对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(factory);
        // 设置key序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置hash key序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }


}
