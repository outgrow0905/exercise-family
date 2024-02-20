package com.latte.cj.royalty.service;

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
import com.latte.cj.hwp.service.KiprisService;
import com.latte.cj.royalty.model.RoyaltyCode;
import com.latte.cj.royalty.model.dto.RoyaltyCodeExcelDto;
import com.latte.cj.royalty.model.registrationinfo.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoyaltyService {
	private final HwpService hwpService;
	private final KiprisService kiprisService;

	public RoyaltyCodeExcelDto getRoyaltyCode(MultipartFile file) {
		List<String> textLines = hwpService.extractTextLines(file);

		return RoyaltyCodeExcelDto.builder()
			.title(extractTitle(textLines))
			.royaltyCodes(extractRoyaltyCodes(textLines,
				"2.3 제품에 적용된 기술", "3. 구성, 재료"))
			.build();
	}

	public void saveRoyalty(RoyaltyCodeExcelDto royaltyCodeExcelDto) {
		for (String royaltyCode : royaltyCodeExcelDto.getRoyaltyCodes()) {
			Response registrationInfo = kiprisService.getRegistrationInfo(royaltyCode);

		}
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
		Set<String> royaltyCodes = null;

		for (String line : textLines) {
			if (line.contains(startSection)) {
				royaltyCodeParagraph = true;
				continue;
			}

			if (line.contains(endSection)) {
				break;
			}

			if (royaltyCodeParagraph) {
				royaltyCodes = extractRoyaltyCodes(line);
				royaltyCodes.forEach(royaltyCode -> log.info("royaltyCode: {}", royaltyCode));
			}
		}

		return royaltyCodes;
	}

	private Set<String> extractRoyaltyCodes(String line) {
		String trimmed = line.replaceAll(" ", Strings.EMPTY);
		String[] splits = trimmed.split("특허");
		Set<String> royaltyCodes = new HashSet<>();

		for (String split : splits) {
			if (split.contains("제10") || split.contains("제20")) {
				int end = split.indexOf("호");
				String royaltyCode = split.substring(0, end + 1);
				royaltyCodes.add(royaltyCode);
			}
		}

		return royaltyCodes;
	}
}
