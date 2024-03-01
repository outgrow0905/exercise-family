package com.latte.cj.royalty.repository;

import com.latte.cj.kipris.service.KiprisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private KiprisService kiprisService;

    @Test
    void save() {
        com.latte.cj.royalty.model.royaltystatussummary.Response response =
            kiprisService.getRoyaltyStatus("1020190054708");
        applicationRepository.save(response.getBody().getItems().getItem());
    }
}