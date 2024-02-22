package com.example.shortlink.admin.config;


import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RBloomFilterConfig {
    @Bean
    public RBloomFilter<String> userRegisterBloomFilter(RedissonClient redissonClient){
        RBloomFilter<String> shortLinkBloomFilter = redissonClient.getBloomFilter("userRegisterBloomFilter");
        shortLinkBloomFilter.tryInit(100000000L, 0.01);
        return shortLinkBloomFilter;
    }
}
