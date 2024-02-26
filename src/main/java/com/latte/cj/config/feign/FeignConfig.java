package com.latte.cj.config.feign;


import feign.Logger;
import feign.Request;
import feign.Request.Options;
import feign.RequestInterceptor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class FeignConfig {

    // http call options
    @Bean
    public Options options() {
        final long CONNECT_TIMEOUT = 10000; // 10s
        final long READ_TIMEOUT = 30000; // 30s
        return new Request.Options(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS, READ_TIMEOUT, TimeUnit.MILLISECONDS, false);
//        return new Request.Options(1, TimeUnit.MILLISECONDS, 1, TimeUnit.MILLISECONDS, false); // for test
    }

    // default headers
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            requestTemplate.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        };
//    }

    @Bean
    Logger.Level feignLoggerLevel() {
        // NONE: no logging (DEFAULT)
        // BASIC: logs the request method and URL and the response status code and execution time
        // HEADERS: logs the basic information along with the request and response headers
        // FULL: logs the headers, body, and metadata for both requests and responses
        return Logger.Level.FULL;
    }
}
