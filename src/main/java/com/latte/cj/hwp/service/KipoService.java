package com.latte.cj.hwp.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class KipoService {
    private final String KIPO_URL = "kipo-api.kipi.or.kr";
    private final String KIPO_APIKEY = "Dc9NTOjdCF/WlflapJLyf39bVys0FAEWptjUHVoZOzrnOwSTRKPs1HWmeTAbFkC2LKKTT9qbl9XTAcKH30oBbg==";

    private ObjectMapper objectMapper = new ObjectMapper();

    public void getLegStatusHistoryInfoSearch(String applicationNumnber) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(KIPO_URL)
                .path("openapi/service/legStatusInfoSearchService/getLegStatusHistoryInfoSearch")
                .queryParam("serviceKey", KIPO_APIKEY)
                .queryParam("applicationNumber", applicationNumnber)
                .build().toUri())
            .build();
//
        try {
            HttpResponse<String> responseFormatXml = client.send(request, BodyHandlers.ofString());
            log.info("body: {}", responseFormatXml.body());
//            return xmlMapper.readValue(responseFormatXml.body(), Response.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
