package com.latte.cj.royalty.model.royaltystatussummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Table(name = "application")
public class Item {
    @Id
    private String applicationNumber; //	출원번호
    private String inventionTitle; //	발명의명칭
    private String inventionTitleEng; //	발명의명칭
    private String applicationDate; //	출원일자
    private String openNumber; //	공개번호
    private String openDate; //	공개일자
    private String publicationNumber; //	공고번호
    private String publicationDate; //	공고일자
    private String registerNumber; //	등록번호
    private String registerDate; //	등록일자
    private String originalApplicationKind; //	원출원종류
    private String originalApplicationNumber; //	원출원출원번호
    private String originalApplicationDate; //	원출원출원일자
    private String finalDisposal; //	최종처분내용
    private String registerStatus; //	등록상태
    private String originalExaminationRequestFlag; //	심사청구여부
    private String originalExaminationRequestDate; //	심사청구일자
    private String claimCount; //	청구항수
    private String translationSubmitDate; //	번역문제출일자
    private String applicationFlag; //	출원구분
}
