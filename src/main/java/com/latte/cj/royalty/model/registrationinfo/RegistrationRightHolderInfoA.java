package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRightHolderInfoA {
	private String rankNumber; //	순위번호
	private String rankCorrelatorSerialNumber; //	순위관련자일련번호
	private String rankCorrelatorType; //	순위관련자종류
	private String rankCorrelatorName; //	순위관련자명
	private String rankCorrelatorAddress; //	순위관련자주소
}
