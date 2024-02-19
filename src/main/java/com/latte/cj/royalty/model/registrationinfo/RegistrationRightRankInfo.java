package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRightRankInfo {
	private String rankNumber; //	순위번호
	private String pertinentPartition; //	해당란
	private String documentName; //	서류명
	private String originalRegistrationNumber; //	원등록번호
	private String registrationPurpose; //	등록목적
	private String registrationDate; //	등록일자
	private String registrationCauseName; //	등록원인명
	private String registrationCauseDate; //	등록원인일자
	private String receiptNumber; //	접수번호
	private String receiptDate; //	접수일자
	private String disappearanceFlag; //	주말유무
	private String disappearanceCauseName; //	주말원인내용
	private String disappearanceDate; //	주말일자
	private String internationalRegRecordDateMD; //	국제등록기록일자
	private String expirationDateMD; //	존속기간만료일자
	private String latestRenewalDateMD; //	최근갱신일자
	private String subDesignationDateMD; //	사후지정일자
}
