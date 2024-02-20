package com.latte.cj.hwp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.latte.cj.royalty.model.registrationinfo.RegistrationInfo;
import com.latte.cj.royalty.model.registrationinfo.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class KiprisServiceTest {
    @Autowired
    private KiprisService kiprisService;

    @Test
    void getRoyaltyCodeOnlyNumber() {
        assertEquals("1020399840000", kiprisService.getRegistrationNumberByRoyaltyCode("제10-2039984호"));
    }

    @Test
    void getRoyalty() throws JsonProcessingException {
        Response response = kiprisService.getRegistrationInfo("제10-2544178호");
        log.info("response: {}", response);
    }

    @Test
    void getRoyaltyStatus() {
        com.latte.cj.royalty.model.royaltystatus.Response response = kiprisService.getRoyaltyStatus("1020190054708");
    }
}