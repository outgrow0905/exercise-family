package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRightHolderInfoB {
	private String rankNumber; //	순위번호
	private String documentName; //	서류명
	private String receiptDate; //	접수일자
	private String registrationCauseName; //	등록원인명
	private String indicationOfEvent; //	사건의표시
}
