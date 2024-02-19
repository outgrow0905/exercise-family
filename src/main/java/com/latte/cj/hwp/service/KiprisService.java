package com.latte.cj.hwp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.latte.cj.royalty.model.registrationinfo.Response;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class KiprisService {
    private final String KIPRIS_URL = "plus.kipris.or.kr";
    private final String KIPRIS_APIKEY = "foXVfrM3vWv2y4IfGIFakaSGB5t5CifP5ll1kzUsZ8E=";

    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();


    public Response getRegistrationInfo(String royaltyCode) {
        HttpClient client = HttpClient.newHttpClient();

        // https://plus.kipris.or.kr/portal/data/util/DBII_000000000000015/view.do?menuNo=210004&subTab=SC001#soap_ADI_0000000000009942
        HttpRequest request = HttpRequest.newBuilder()
            .uri(UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(KIPRIS_URL)
                .path("openapi/rest/RegistrationService/registrationInfo")
                .queryParam("accessKey", KIPRIS_APIKEY)
                .queryParam("registrationNumber", getRegistrationNumberByRoyaltyCode(royaltyCode))
                .build().toUri())
            .build();

        try {
            HttpResponse<String> responseFormatXml = client.send(request, BodyHandlers.ofString());
            log.info("getRegistrationInfo: {}", responseFormatXml.body());
            return xmlMapper.readValue(responseFormatXml.body(), Response.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 서지요약정보 - getBibliographySumryInfoSearch
    // https://plus.kipris.or.kr/portal/data/service/DBII_000000000000001/view.do?menuNo=210000&kppBCode=&kppMCode=&kppSCode=&subTab=&entYn=N&clasKeyword=#soap_ADI_0000000000002131
    public com.latte.cj.royalty.model.royaltystatus.Response getRoyaltyStatus(String applicationNumber) {
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
            return xmlMapper.readValue(responseFormatXml.body(), com.latte.cj.royalty.model.royaltystatus.Response.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRegistrationNumberByRoyaltyCode(String royaltyCode) {
        String s1 = royaltyCode.replace("제", "");
        String s2 = s1.replace("호", "");
        String s3 = s2.replace("-", "");
        String result = s3.concat("0000");
        return result;
    }
}
