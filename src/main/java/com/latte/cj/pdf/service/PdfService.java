package com.latte.cj.pdf.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class PdfService {
    public List<String> extractTextLines(MultipartFile multipartFile) {
        File file = null;
        try {
            Files.write(
                Paths.get("./" + multipartFile.getOriginalFilename())
                , multipartFile.getBytes()
            );

            file = new File(multipartFile.getOriginalFilename());

            PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(file));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            return text.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("PdfService extractTextLines failed.");
        } finally {
            file.delete();
        }
    }
}
