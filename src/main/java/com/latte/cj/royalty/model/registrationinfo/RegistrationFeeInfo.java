package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFeeInfo {
	private String registrationDate; //	등록일자
	private String startAnnual; //	시작연차
	private String lastAnnual; //	마지막연차
	private String paymentDegree; //	납부차수
	private String paymentFee; //	납부금액
}
