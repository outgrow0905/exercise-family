package com.latte.cj.royalty.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Set;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.latte.cj.royalty.service.RoyaltyExcelProducer;
import com.latte.cj.royalty.model.dto.RoyaltyCodeExcelDto;
import com.latte.cj.royalty.service.RoyaltyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("royalty")
@RequiredArgsConstructor
public class RoyaltyController {

    private final RoyaltyExcelProducer royaltyExcelProducer;
    private final RoyaltyService royaltyService;

    @PostMapping(path = "code",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
        // , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public void getRoyaltyCode(
        @RequestPart("files") MultipartFile multipartFile) {

        RoyaltyCodeExcelDto royaltyCodeExcelDto = royaltyService.getRoyaltyCode(multipartFile);

        royaltyService.saveRoyalty(royaltyCodeExcelDto.getRoyaltyCodes());

        // File file = royaltyExcelProducer.produce(royaltyCode);
        // ByteArrayResource resource = new ByteArrayResource(
        //     Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        // file.delete();
        // return resource;
    }

    @PostMapping(path = "royalty-code")
    public void registerRoyaltyCode(
        @RequestBody List<String> royaltyCodes) {
        log.info("royaltyCodes: {}, size: {}", royaltyCodes, royaltyCodes.size());
        royaltyService.saveRoyalty(Set.copyOf(royaltyCodes));
    }

    @PostMapping(path = "biblio")
    public void registerBiblio(
        @RequestBody List<String> applicationNumbers) {
        royaltyService.saveBiblio(Set.copyOf(applicationNumbers));
    }
}
