package com.latte.cj.config.feign.common;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CommonFeignConfig {
    @Bean
    public ErrorDecoder CommonErrorDecoder() {
        return new CommonErrorDecoder();
    }
}
