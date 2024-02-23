package com.example.shortlink.project.common.config;


import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RBloomFilterConfig {
    @Bean
    public RBloomFilter<String> ShortLinkBloomFilter(RedissonClient redissonClient){
        RBloomFilter<String> shortLinkBloomFilter = redissonClient.getBloomFilter("shortLinkBloomFilter");
        shortLinkBloomFilter.tryInit(100000000L, 0.01);
        return shortLinkBloomFilter;
    }
}
