package com.latte.cj.kipris.feign;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import com.latte.cj.kipris.model.bibliographydetail.Item;
import com.latte.cj.kipris.model.registrationinfo.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class KiprisFeignClientTest {
    private final String KIPRIS_APIKEY = "JjCaFVi61n4pN4BwPXo2r3RfzXlkTGDOhKqDazaHRmc=";
    private XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    private KiprisFeignClient client;

    @Test
    void getRegistrationInfo() throws Exception {
        String result = client.getRegistrationInfo(
            GetRegistrationInfoRequest.builder()
                .registrationNumber("1025441780000")
                .accessKey(KIPRIS_APIKEY)
                .build()
        );

        Response response = xmlMapper.readValue(result, Response.class);
        log.info("response: {}", response);
    }

    @Test
    void getRoyaltyStatus() throws Exception {
        String result = client.getRoyaltyStatus(
            KIPRIS_APIKEY
            , "1019900022085"
        );

        com.latte.cj.kipris.model.bibliographydetail.Response response =
            xmlMapper.readValue(result, com.latte.cj.kipris.model.bibliographydetail.Response.class);

        log.info("response: {}", response);
        Item item = response.getBody().getItem();
        log.info("getApplicantInfoArray: {}", item.getApplicantInfoArray());
        log.info("getInternationalInfoArray: {}", item.getInternationalInfoArray());
        log.info("getFamilyInfoArray: {}", item.getFamilyInfoArray());
        log.info("getClaimInfoArray: {}", item.getClaimInfoArray());
        log.info("getIpcInfoArray: {}", item.getIpcInfoArray());
        log.info("getInventorInfoArray: {}", item.getInventorInfoArray());
        log.info("getBiblioSummaryInfoArray: {}", item.getBiblioSummaryInfoArray());
        log.info("getAbstractInfoArray: {}", item.getAbstractInfoArray());
        log.info("getLegalStatusInfoArray: {}", item.getLegalStatusInfoArray());
    }
}