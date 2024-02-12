package com.latte.cj.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latte.cj.business.model.BusinessRequest;
import java.util.List;
import org.junit.jupiter.api.Test;


class BusinessRequestTest {
    @Test
    void json() throws Exception {
        BusinessRequest request =
            BusinessRequest.builder().b_no(List.of("123", "456")).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(request);
        System.out.println("result: " + result);
    }
}