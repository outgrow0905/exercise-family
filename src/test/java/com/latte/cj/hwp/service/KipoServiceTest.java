package com.latte.cj.hwp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KipoServiceTest {
    @Autowired
    private KipoService kipoService;

    @Test
    void getLegStatusHistoryInfoSearch() {
        kipoService.getLegStatusHistoryInfoSearch("1020220118940");
    }

}