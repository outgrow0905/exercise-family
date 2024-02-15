package com.latte.cj.hwp.service;

import java.util.ArrayList;
import java.util.List;

import kr.dogfoot.hwplib.tool.textextractor.TextExtractorListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HwpTextExtractor implements TextExtractorListener {
	private List<String> testLines = new ArrayList<>();

	@Override
	public void paragraphText(String text) {
		log.info("text: {}", text);
		String trim = text.trim();
		String[] lines = trim.split(System.lineSeparator());

		for (String line : lines) {
			testLines.add(line);
		}
	}

	public List<String> getTextLines() {
		return testLines;
	}
}
