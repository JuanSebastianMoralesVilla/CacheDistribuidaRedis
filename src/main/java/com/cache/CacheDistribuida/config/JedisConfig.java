package com.cache.CacheDistribuida.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.cache.CacheDistribuida.model.User;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 
@Configuration
public class JedisConfig {
 
	
	
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	
    	int port = 6379;
    	String hostname="localhost";
    	
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
 
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();


        template.setConnectionFactory(jedisConnectionFactory());
        
 

        template.setKeySerializer(new StringRedisSerializer());
        
        template.setHashKeySerializer(new StringRedisSerializer());
        
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        
        return template;
    }

    
  
    
    
}
