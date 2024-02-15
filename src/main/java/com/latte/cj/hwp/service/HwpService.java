package com.latte.cj.hwp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractorListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HwpService {

	public List<String> extractTextLines(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("./" + file.getOriginalFilename());
			Files.write(path, bytes);

			HWPFile hwpFile = HWPReader.fromFile(new File(file.getOriginalFilename()));
			String hwpText = TextExtractor.extract(hwpFile, TextExtractMethod.InsertControlTextBetweenParagraphText);

			return hwpText.lines().collect(Collectors.toList());
		} catch (Exception e) {
			log.error("extractText failed.");
			throw new RuntimeException(e);
		}
	}
}
