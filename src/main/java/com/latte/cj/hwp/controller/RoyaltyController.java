package com.latte.cj.hwp.controller;


import com.latte.cj.hwp.model.RoyaltyCode;
import com.latte.cj.hwp.service.RoyaltyExcelProducer;
import com.latte.cj.hwp.service.RoyaltyParser;
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

    @PostMapping(path = "code",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public Resource getLoyaltyCode(
        @RequestPart("files") MultipartFile file
    ) throws Exception {

        byte[] bytes = file.getBytes();
        Path path = Paths.get("./" + file.getOriginalFilename());
        Files.write(path, bytes);

        HWPReader.forExtractText(file.getOriginalFilename(),
            new RoyaltyParser(), TextExtractMethod.InsertControlTextBetweenParagraphText);

        File result = royaltyExcelProducer.produce();

        return new ByteArrayResource(
            Files.readAllBytes(Paths.get(result.getAbsolutePath())));
    }
}
