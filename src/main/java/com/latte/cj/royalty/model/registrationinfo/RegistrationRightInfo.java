package com.latte.cj.royalty.model.registrationinfo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistrationRightInfo {
    @Id
    private String registrationNumber; //	등록번호
    private String regReferenceNumber; //	등록참조번호
    private String registrationDate; //	등록일자
    private String assessmentDate; //	사정일자
    private String expirationDate; //	존속기간만료일자
    private String terminationCauseName; //	소멸원인명
    private String terminationDate; //	소멸일자
    private String applicationNumber; //	출원번호
    private String appReferenceNumber; //	출원참조번호
    private String applicationDate; //	출원일자
    private String publicationNumber; //	공고번호
    private String publicationDate; //	공고일자
    private String internationRegistrationNumber; //	국제등록번호
    private String internationRegistrationDate; //	국제등록일자
    private String originalApplicationNumber; //	원출원번호
    private String originalApplicationDate; //	원출원일자
    private String classCode; //	분류코드
    private String titleOfInvention;	// 발명의명칭/물품명칭/상표명칭
    private String titleOfInventionEng;	// 발명의명칭/물품명칭/상표명칭
    private String claimCount; //	청구항수
    private String priorityCountry; //	우선권주장국가
    private String priorityDate; //	우선권주장일자
    private String priorityCount; //	우선권주장수

    @OneToMany(mappedBy = "registrationRightInfo", cascade = CascadeType.ALL)
    private List<RegistrationRightHolderInfoA> registrationRightHolderInfoAs;
    @OneToMany(mappedBy = "registrationRightInfo", cascade = CascadeType.ALL)
    private List<RegistrationRightHolderInfoB> registrationRightHolderInfoBs;
    @OneToMany(mappedBy = "registrationRightInfo", cascade = CascadeType.ALL)
    private List<RegistrationRightRankInfo> registrationRightRankInfos;
    @OneToMany(mappedBy = "registrationRightInfo", cascade = CascadeType.ALL)
    private List<RegistrationFeeInfo> registrationFeeInfos;
    @OneToMany(mappedBy = "registrationRightInfo", cascade = CascadeType.ALL)
    private List<RegistrationLastRightHolderInfo> registrationLastRightHolderInfos;
}
