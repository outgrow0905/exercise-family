package com.latte.cj.royalty.service;

import com.latte.cj.pdf.service.PdfService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.latte.cj.hwp.service.HwpService;
import com.latte.cj.royalty.model.RoyaltyCode;
import com.latte.cj.royalty.model.dto.RoyaltyCodeExcelDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoyaltyService {
//	private final HwpService hwpService;
	private final PdfService pdfService;

	public RoyaltyCodeExcelDto getRoyaltyCode(MultipartFile file) {
		List<String> textLines = pdfService.extractTextLines(file);

		return RoyaltyCodeExcelDto.builder()
			.title(extractTitle(textLines))
			.royaltyCodes(extractRoyaltyCodes(textLines, "9. 적용자료", null))
			.laws(extractLaws(textLines, "9. 적용자료", null))
			.build();
	}

	public String extractTitle(List<String> textLines) {
		boolean title = false;

		for (String line : textLines) {
			String titleSearchString = line.replaceAll(" ", "");

			if (titleSearchString.contains("제품규격서")) {
				title = true;
				continue;
			}

			if (title) {
				log.info("title: {}", line);
				return line;
			}
		}

		return Strings.EMPTY;
	}

	public Set<String> extractRoyaltyCodes(List<String> textLines, String startSection, String endSection) {
		boolean royaltyCodeParagraph = false;
		Set<String> royaltyCodes = new HashSet<>();

		for (String line : textLines) {
			if (line.contains(startSection)) {
				royaltyCodeParagraph = true;
				continue;
			}

			if (StringUtils.isNotEmpty(endSection) && line.contains(endSection)) {
				break;
			}

			String royaltyCode = Strings.EMPTY;
			if (royaltyCodeParagraph) {
				royaltyCode = extractRoyaltyCode(line);
			}

			if (StringUtils.isNotEmpty(royaltyCode)) {
				royaltyCodes.add(royaltyCode);
			}
		}

		royaltyCodes.forEach(royaltyCode -> log.info("royaltyCode: {}", royaltyCode));

		return royaltyCodes;
	}

	private String extractRoyaltyCode(String line) {
		String royaltyCode = Strings.EMPTY;
		String trimmed = line.replaceAll(" ", Strings.EMPTY);
		String[] splits = trimmed.split("특허");

		for (String split : splits) {
			if (split.contains("제10") || split.contains("제20")) {
				int end = split.indexOf("호");
				royaltyCode = split.substring(0, end + 1);
				return royaltyCode;
			}
		}

		return royaltyCode;
	}

	private String extractLaw(String line) {
		String law = Strings.EMPTY;

		String trimmed = line.replaceAll(" ", Strings.EMPTY);
		if (trimmed.endsWith("법")) {
			trimmed = trimmed.replaceAll("[0-9]", Strings.EMPTY);
			trimmed = trimmed.replaceAll("\\.", Strings.EMPTY);
			return trimmed.substring(0, trimmed.indexOf("법") + 1);
		}

		return law;
	}

	public Set<String> extractLaws(List<String> textLines, String startSection, String endSection) {
		boolean lawParagraph = false;
		Set<String> laws = new HashSet<>();

		for (String line : textLines) {
			if (line.contains(startSection)) {
				lawParagraph = true;
				continue;
			}

			if (StringUtils.isNotEmpty(endSection) && line.contains(endSection)) {
				break;
			}

			String law = Strings.EMPTY;
			if (lawParagraph) {
				law = extractLaw(line);
			}

			if (StringUtils.isNotEmpty(law)) {
				laws.add(law);
			}
		}

		laws.forEach(royaltyCode -> log.info("laws: {}", royaltyCode));

		return laws;
	}
}
