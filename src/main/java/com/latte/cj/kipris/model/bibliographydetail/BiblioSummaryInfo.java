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
@Table(name = "cj_app_biblio_summary_info")
@Entity
public class BiblioSummaryInfo {
    private String applicationDate; 		//	출원일자
    @Id
    private String applicationNumber; 		//	출원번호
    private String claimCount; 		//	청구항수
    private String finalDisposal; 		//	최종처분내용
    private String inventionTitle; 		//	발명의명칭
    private String inventionTitleEng; 		//	발명의명칭
    private String openDate; 		//	공개일자
    private String openNumber; 		//	공개번호
    private String originalApplicationDate; 		//	원출원출원일자
    private String originalApplicationKind; 		//	원출원종류
    private String originalApplicationNumber; 		//	원출원출원번호
    private String originalExaminationRequestDate; 		//	심사청구일자
    private String originalExaminationRequestFlag; 		//	심사청구여부
    private String publicationDate; 		//	공고일자
    private String publicationNumber; 		//	공고번호
    private String registerDate; 		//	등록일자
    private String registerNumber; 		//	등록번호
    private String registerStatus; 		//	등록상태
    private String translationSubmitDate; 		//	번역문제출일자
}
