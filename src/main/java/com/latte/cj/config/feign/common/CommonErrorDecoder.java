package com.latte.cj.config.feign.common;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() == null) {
            log.error("feign error response. [{} {}]", response.status(), response.reason());
            return new RuntimeException("Internal Server Error");
        }

        switch (response.status()) {
            case 400:
                return new RuntimeException("Bad Request");
            case 401:
                return new RuntimeException("Unauthorized");
//            case 500:
//                if (StringUtils.contains(methodKey, ReleasePayUncompleteObjectRequest.class.getSimpleName())) {
//                    throw new RetryableException(
//                            ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
//                            ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
//                            response.request().httpMethod(),
//                            null,
//                            response.request());
//                }
            default:
                return new RuntimeException("Internal Server Error");
        }
    }
}
