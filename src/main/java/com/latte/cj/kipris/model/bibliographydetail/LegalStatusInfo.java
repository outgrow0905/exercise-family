package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cj_app_legal_status_info")
@Entity
public class LegalStatusInfo {
    @Id
    private String applicationNumber; 		//	출원번호
    private String commonCodeName; // 	처리상태
    private String documentEngName; // 	서류명
    private String documentName; // 	서류명
    @Id
    private String receiptDate; // 	접수일자
    @Id
    private String receiptNumber; // 	접수번호
}
