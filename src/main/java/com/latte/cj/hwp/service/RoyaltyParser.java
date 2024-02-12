package com.latte.cj.hwp.service;

import com.latte.cj.hwp.model.RoyaltyCode;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractorListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoyaltyParser implements TextExtractorListener {

    static boolean read = false;
    static boolean title = false;

    @Override
    public void paragraphText(String text) {
        String trim = text.trim();
        String[] lines = trim.split(System.lineSeparator());

        for (String line : lines) {
            log.info("line: {}", lines);

            String titleSearchString = line.replaceAll(" ", "");
            if (titleSearchString.contains("제품규격서")) {
                title = true;
                continue;
            }

            if (title) {
                RoyaltyCode.setFileName(line);
                title = false;
            }

            if (line.contains("2.3 제품에 적용된 기술")) {
                read = true;
                continue;
            }
//
            if (line.contains("3. 구성, 재료")) {
                read = false;
                break;
            }


            if (read) {
                String[] split = line.split("특허");
                for (String sp : split) {
                    if (sp.contains("제10") || sp.contains("제20")) {
                        String[] split1 = sp.split(" ");
                        for (String sp2 : split1) {
                            if (sp2.contains("제10") || sp2.contains("제20")) {
                                int end = sp.indexOf("호");
                                System.out.println(sp2.substring(0, end + 1));
                                String royaltyCode = sp2.substring(0, end + 1);
                                RoyaltyCode.setRoyaltyCodes(royaltyCode);
                            }
                        }
                    }
                }
            }
        }
    }
}
