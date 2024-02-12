package com.latte.cj.business.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latte.cj.business.model.BusinessData;
import com.latte.cj.business.model.BusinessRequest;
import com.latte.cj.business.model.BusinessResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
public class BusinessController {
    private final String BUSINESS_URL = "api.odcloud.kr";
    private final String BUSINESS_APIKEY = "Dc9NTOjdCF/WlflapJLyf39bVys0FAEWptjUHVoZOzrnOwSTRKPs1HWmeTAbFkC2LKKTT9qbl9XTAcKH30oBbg==";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(path = "business/status", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource data(@RequestParam("b_no") List<String> businessNumbers) throws IOException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(BUSINESS_URL)
                .path("api/nts-businessman/v1/status")
                .queryParam("serviceKey", BUSINESS_APIKEY)
                .queryParam("returnType", "JSON")
                .build().toUri())
            .headers("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(
                objectMapper.writeValueAsString(
                    BusinessRequest.builder()
                        .b_no(businessNumbers.stream()
                            .map(businessNumber
                                -> businessNumber = businessNumber.replaceAll("-", ""))
                            .collect(Collectors.toList()))
                        .build())
            ))
            .build();

        BusinessResponse businessResponse;
        List<BusinessData> data;
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            businessResponse = objectMapper.readValue(response.body(),
                BusinessResponse.class);
            log.info("businessResponse: {}", businessResponse);
            data = businessResponse.getData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Workbook 객체 생성
        Workbook workbook = new HSSFWorkbook();

        // Excel Sheet1 생성
        Sheet sheet1 = workbook.createSheet("business");

        // Sheet1 Head write
        Row headRow = sheet1.createRow(0);
        sheet1.setColumnWidth(0, 5000);
        Cell cell1 = headRow.createCell(0);
        cell1.setCellValue("b_no");
        Cell cell2 = headRow.createCell(1);
        cell2.setCellValue("b_no");
        Cell cell3 = headRow.createCell(2);
        cell3.setCellValue("b_stt");

        for (int i = 0 ; i < data.size() ; i++){
            Row bodyRow = sheet1.createRow(i+1);

            for (int j = 0 ; j < 3 ; j++) {
                if (j == 0) {
                    Cell cell = bodyRow.createCell(j);
                    cell.setCellValue(data.get(i).getB_no());
                }
                if (j == 1) {
                    Cell cell = bodyRow.createCell(j);
                    cell.setCellValue(data.get(i).getB_stt());
                }
                if (j == 2) {
                    Cell cell = bodyRow.createCell(j);
                    cell.setCellValue(data.get(i).getB_stt_cd());
                }
            }
        }

        // File 생성
        File result = new File("./business.xls");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            ((HSSFWorkbook) workbook).write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.debug("finally end");
        }


        return new ByteArrayResource(
            Files.readAllBytes(Paths.get(result.getAbsolutePath())));
    }
}
