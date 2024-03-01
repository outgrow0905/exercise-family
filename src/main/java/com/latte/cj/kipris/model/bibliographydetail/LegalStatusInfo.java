package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LegalStatusInfo {
    private String commonCodeName; // 	처리상태
    private String documentEngName; // 	서류명
    private String documentName; // 	서류명
    private String receiptDate; // 	접수일자
    private String receiptNumber; // 	접수번호
}
