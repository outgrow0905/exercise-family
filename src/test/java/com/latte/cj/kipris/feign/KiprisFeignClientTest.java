package com.latte.cj.kipris.feign;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import com.latte.cj.kipris.model.bibliographydetail.BiblioSummaryInfo;
import com.latte.cj.kipris.model.bibliographydetail.BiblioSummaryInfoArray;
import com.latte.cj.kipris.model.bibliographydetail.Item;
import com.latte.cj.kipris.model.registrationinfo.Response;
import com.latte.cj.royalty.repository.BiblioSummaryInfoRepository;

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

    @Autowired
    private BiblioSummaryInfoRepository biblioSummaryInfoRepository;

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
        String result = client.getBiblio(
            KIPRIS_APIKEY
            , "1019900022085"
        );

        com.latte.cj.kipris.model.bibliographydetail.Response response =
            xmlMapper.readValue(result, com.latte.cj.kipris.model.bibliographydetail.Response.class);

        log.info("response: {}", response);
        Item item = response.getBody().getItem();
        log.info("getBiblioSummaryInfoArray: {}", item.getBiblioSummaryInfoArray());
        log.info("getApplicantInfoArray: {}", item.getApplicantInfoArray());
        log.info("getInternationalInfoArray: {}", item.getInternationalInfoArray());
        log.info("getClaimInfoArray: {}", item.getClaimInfoArray());
        log.info("getIpcInfoArray: {}", item.getIpcInfoArray());
        log.info("getInventorInfoArray: {}", item.getInventorInfoArray());
        log.info("getAbstractInfoArray: {}", item.getAbstractInfoArray());
        log.info("getLegalStatusInfoArray: {}", item.getLegalStatusInfoArray());

        BiblioSummaryInfo biblioSummaryInfo = item.getBiblioSummaryInfoArray().getBiblioSummaryInfo();
        biblioSummaryInfo.setApplicantInfo(item.getApplicantInfoArray().getApplicantInfo());
        biblioSummaryInfo.setInternationalInfo(item.getInternationalInfoArray().getInternationalInfo());
        biblioSummaryInfo.setClaimInfo(item.getClaimInfoArray().getClaimInfo());
        biblioSummaryInfo.setIpcInfo(item.getIpcInfoArray().getIpcInfo());
        biblioSummaryInfo.setInternationalInfo(item.getInternationalInfoArray().getInternationalInfo());
        biblioSummaryInfo.setAbstractInfo(item.getAbstractInfoArray().getAbstractInfo());
        biblioSummaryInfo.setLegalStatusInfo(item.getLegalStatusInfoArray().getLegalStatusInfo());

        item.getApplicantInfoArray().getApplicantInfo().forEach(applicantInfo
            -> applicantInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getInternationalInfoArray().getInternationalInfo().forEach(internationalInfo
            -> internationalInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getClaimInfoArray().getClaimInfo().forEach(claimInfo
            -> claimInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getIpcInfoArray().getIpcInfo().forEach(ipcInfo
            -> ipcInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getInternationalInfoArray().getInternationalInfo().forEach(internationalInfo
            -> internationalInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getAbstractInfoArray().getAbstractInfo().forEach(abstractInfo
            -> abstractInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));
        item.getLegalStatusInfoArray().getLegalStatusInfo().forEach(legalStatusInfo
            -> legalStatusInfo.setApplicationNumber(biblioSummaryInfo.getApplicationNumber()));

        biblioSummaryInfoRepository.save(biblioSummaryInfo);
    }
}