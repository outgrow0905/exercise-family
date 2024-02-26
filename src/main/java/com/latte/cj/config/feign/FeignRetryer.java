package com.latte.cj.config.feign;


import feign.Retryer;
import org.springframework.context.annotation.Bean;

public class FeignRetryer {
    @Bean
    public Retryer retryer() {
        final long PERIOD = 1000;
        final long MAX_PERIOD = 2000; // MAX_PERIOD = PERIOD * 1.5
        final int MAX_ATTEMPTS = 3;
        return new Retryer.Default(PERIOD, MAX_PERIOD, MAX_ATTEMPTS);
    }
}
