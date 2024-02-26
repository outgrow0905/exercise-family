package com.latte.cj.kipris.feign;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import com.latte.cj.royalty.model.registrationinfo.Response;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class KiprisFeignClientTest {
    private final String KIPRIS_APIKEY = "JjCaFVi61n4pN4BwPXo2r3RfzXlkTGDOhKqDazaHRmc==";
    private XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    private KiprisFeignClient client;

    @Test
    void getRegistrationInfo() throws Exception {
        String result = client.getRegistrationInfo(
            GetRegistrationInfoRequest.builder()
                .registrationNumber("제10-2544178호")
                .accessKey(KIPRIS_APIKEY)
                .build()
        );

        Response response = xmlMapper.readValue(result, Response.class);
        log.info("response: {}", response);
    }
}