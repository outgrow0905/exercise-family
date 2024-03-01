package com.latte.cj.kipris.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.latte.cj.kipris.feign.KiprisFeignClient;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import com.latte.cj.kipris.model.registrationinfo.Response;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class KiprisService {
    private final String KIPRIS_URL = "plus.kipris.or.kr";
    private final String KIPRIS_APIKEY = "JjCaFVi61n4pN4BwPXo2r3RfzXlkTGDOhKqDazaHRmc=";

    // private ObjectMapper objectMapper = new ObjectMapper();
    private final KiprisFeignClient feignClient;
    private XmlMapper xmlMapper = new XmlMapper();



    public Response getRegistrationInfo(String royaltyCode) {
        String result = feignClient.getRegistrationInfo(
            GetRegistrationInfoRequest.builder()
                .accessKey(KIPRIS_APIKEY)
                .registrationNumber(getRegistrationNumberByRoyaltyCode(royaltyCode))
                .build()
        );

        try {
            return xmlMapper.readValue(result, Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("getRegistrationInfo parse fail");
        }
    }

    // 서지요약정보 - getBibliographySumryInfoSearch
    // https://plus.kipris.or.kr/portal/data/service/DBII_000000000000001/view.do?menuNo=210000&kppBCode=&kppMCode=&kppSCode=&subTab=&entYn=N&clasKeyword=#soap_ADI_0000000000002131
    public com.latte.cj.royalty.model.royaltystatussummary.Response getRoyaltyStatus(String applicationNumber) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(KIPRIS_URL)
                .path("kipo-api/kipi/patUtiModInfoSearchSevice/getBibliographySumryInfoSearch")
                .queryParam("ServiceKey", KIPRIS_APIKEY)
                .queryParam("applicationNumber", applicationNumber)
                .build().toUri())
            .build();



        try {
            HttpResponse<String> responseFormatXml = client.send(request, BodyHandlers.ofString());
            // log.info("getRoyaltyStatus response: {}", responseFormatXml.body());
            return xmlMapper.readValue(responseFormatXml.body(), com.latte.cj.royalty.model.royaltystatussummary.Response.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRegistrationNumberByRoyaltyCode(String royaltyCode) {
        String s1 = royaltyCode.replace("제", "");
        String s2 = s1.replace("호", "");
        String s3 = s2.replace("-", "");
        return StringUtils.rightPad(s3, 13, "0");
    }
}
