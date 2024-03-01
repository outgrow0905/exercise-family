package com.latte.cj.royalty.service;

import com.latte.cj.pdf.service.PdfService;
import com.latte.cj.kipris.model.registrationinfo.RegistrationFeeInfo;
import com.latte.cj.kipris.model.registrationinfo.RegistrationInfo;
import com.latte.cj.kipris.model.registrationinfo.RegistrationLastRightHolderInfo;
import com.latte.cj.kipris.model.registrationinfo.RegistrationRightHolderInfoA;
import com.latte.cj.kipris.model.registrationinfo.RegistrationRightHolderInfoB;
import com.latte.cj.kipris.model.registrationinfo.RegistrationRightInfo;
import com.latte.cj.kipris.model.registrationinfo.RegistrationRightRankInfo;
import com.latte.cj.royalty.repository.ApplicationRepository;
import com.latte.cj.royalty.repository.RegistrationRightInfoRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.latte.cj.kipris.service.KiprisService;
import com.latte.cj.royalty.model.dto.RoyaltyCodeExcelDto;
import com.latte.cj.kipris.model.registrationinfo.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoyaltyService {
//	private final HwpService hwpService;
	private final KiprisService kiprisService;
	private final PdfService pdfService;
	private final RegistrationRightInfoRepository registrationRightInfoRepository;
	private final ApplicationRepository applicationRepository;

	public RoyaltyCodeExcelDto getRoyaltyCode(MultipartFile file) {
		List<String> textLines = pdfService.extractTextLines(file);

		return RoyaltyCodeExcelDto.builder()
			.title(extractTitle(textLines))
			.royaltyCodes(extractRoyaltyCodes(textLines, "9. 적용자료", null))
			.laws(extractLaws(textLines, "9. 적용자료", null))
			.build();
	}

	public void saveRoyalty(Set<String> royaltyCodes) {
		int size = royaltyCodes.stream().map(kiprisService::getRegistrationNumberByRoyaltyCode)
			.toList().size();
		log.info("saveRoyalty size: {}", size);
		for (String royaltyCode : royaltyCodes.stream().map(kiprisService::getRegistrationNumberByRoyaltyCode).toList()) {
			Response response = kiprisService.getRegistrationInfo(royaltyCode);
			log.info("response: {}", response);
			// parse
			RegistrationInfo registrationInfo = response.getBody().getItems().getRegistrationInfo();
			RegistrationRightInfo registrationRightInfo = registrationInfo.getRegistrationRightInfo();
			List<RegistrationRightHolderInfoA> registrationRightHolderInfoA = registrationInfo
				.getRegistrationRightHolderInfo()
				.getRegistrationRightHolderInfoA();
			List<RegistrationRightHolderInfoB> registrationRightHolderInfoB = registrationInfo.getRegistrationRightHolderInfo()
				.getRegistrationRightHolderInfoB();
			List<RegistrationRightRankInfo> registrationRightRankInfo = registrationInfo.getRegistrationRightRankInfo();
			List<RegistrationFeeInfo> registrationFeeInfo = registrationInfo.getRegistrationFeeInfo();
			List<RegistrationLastRightHolderInfo> registrationLastRightHolderInfo = registrationInfo.getRegistrationLastRightHolderInfo();

			// registrationNumber set
			registrationRightHolderInfoA.forEach(data -> data.setRegistrationNumber(registrationRightInfo.getRegistrationNumber()));
			registrationRightHolderInfoB.forEach(data -> data.setRegistrationNumber(registrationRightInfo.getRegistrationNumber()));
			registrationRightRankInfo.forEach(data -> data.setRegistrationNumber(registrationRightInfo.getRegistrationNumber()));
			registrationFeeInfo.forEach(data -> data.setRegistrationNumber(registrationRightInfo.getRegistrationNumber()));
			registrationLastRightHolderInfo.forEach(data -> data.setRegistrationNumber(registrationRightInfo.getRegistrationNumber()));

			log.info("RegistrationRightInfo: {}", registrationRightInfo);
			log.info("RegistrationRightHolderInfoA: {}", registrationRightHolderInfoA);
			log.info("RegistrationRightHolderInfoB: {}", registrationRightHolderInfoB);
			log.info("RegistrationRightRankInfo: {}", registrationRightRankInfo);
			log.info("RegistrationFeeInfo: {}", registrationFeeInfo);
			log.info("RegistrationLastRightHolderInfo: {}", registrationLastRightHolderInfo);

			// for cascade
			registrationRightInfo.setRegistrationRightHolderInfoAs(registrationRightHolderInfoA);
			registrationRightInfo.setRegistrationRightHolderInfoBs(registrationRightHolderInfoB);
			registrationRightInfo.setRegistrationRightRankInfos(registrationRightRankInfo);
			registrationRightInfo.setRegistrationFeeInfos(registrationFeeInfo);
			registrationRightInfo.setRegistrationLastRightHolderInfos(registrationLastRightHolderInfo);

			// save
			registrationRightInfoRepository.save(registrationRightInfo);

			// call
//			com.latte.cj.royalty.model.royaltystatus.Response royaltyStatus = kiprisService.getRoyaltyStatus(
//				registrationRightInfo.getApplicationNumber());
//			Item item = royaltyStatus.getBody().getItems().getItem();
//			item.setApplicationNumber(item.getApplicationNumber());
//			applicationRepository.save(item);
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
