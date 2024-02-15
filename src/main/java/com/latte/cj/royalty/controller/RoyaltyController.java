package com.latte.cj.royalty.controller;


import com.latte.cj.hwp.service.RoyaltyExcelProducer;
import com.latte.cj.hwp.service.RoyaltyParser;
import com.latte.cj.royalty.service.RoyaltyService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController("royalty")
@RequiredArgsConstructor
public class RoyaltyController {

    private final RoyaltyExcelProducer royaltyExcelProducer;
    private final RoyaltyService royaltyService;

    @PostMapping(path = "code",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public Resource getRoyaltyCode(
        @RequestPart("files") MultipartFile file
    ) throws Exception {

        royaltyService.getRoyaltyCode(file);

        // byte[] bytes = file.getBytes();
        // Path path = Paths.get("./" + file.getOriginalFilename());
        // Files.write(path, bytes);
        //
        // RoyaltyParser royaltyParser = new RoyaltyParser();
        // HWPReader.forExtractText(file.getOriginalFilename(),
        //     royaltyParser, TextExtractMethod.InsertControlTextBetweenParagraphText);
        //
        // File result = royaltyExcelProducer.produce();
        //
        // return new ByteArrayResource(
        //     Files.readAllBytes(Paths.get(result.getAbsolutePath())));
        return null;
    }
}
