package com.latte.cj.royalty.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.latte.cj.hwp.service.KiprisService;
import com.latte.cj.royalty.model.registrationinfo.RegistrationFeeInfo;
import com.latte.cj.royalty.model.registrationinfo.RegistrationInfo;
import com.latte.cj.royalty.model.registrationinfo.RegistrationLastRightHolderInfo;
import com.latte.cj.royalty.model.registrationinfo.RegistrationRightHolderInfoA;
import com.latte.cj.royalty.model.registrationinfo.RegistrationRightHolderInfoB;
import com.latte.cj.royalty.model.registrationinfo.RegistrationRightInfo;
import com.latte.cj.royalty.model.registrationinfo.RegistrationRightRankInfo;
import com.latte.cj.royalty.model.registrationinfo.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class RegistrationRightInfoRepositoryTest {
	@Autowired
	private RegistrationRightInfoRepository repository;

	@Autowired
	private KiprisService kiprisService;

	@Test
	@Transactional
	@Rollback(value = false)
	void save() {
		Response response = kiprisService.getRegistrationInfo("제10-2544178호");

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
		repository.save(registrationRightInfo);

		// delete
		// repository.delete(registrationRightInfo);
	}

}