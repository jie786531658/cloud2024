package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer myRetryer() {
        //最大请求次数为3(初始1次+重试2次)，初始间隔为100ms，重试间最大间隔为1s
//        return new Retryer.Default(100, 1, 3);
        return Retryer.NEVER_RETRY; //Feign默认不走重试策略
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
