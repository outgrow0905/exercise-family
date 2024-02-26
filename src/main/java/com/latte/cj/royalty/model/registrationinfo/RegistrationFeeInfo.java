package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistrationFeeInfo {
	@Id
	private String registrationNumber; //	등록번호
	@Id
	private String registrationDate; //	등록일자
	@Id
	private String startAnnual; //	시작연차
	private String lastAnnual; //	마지막연차
	private String paymentDegree; //	납부차수
	private String paymentFee; //	납부금액
	@ManyToOne
	private RegistrationRightInfo registrationRightInfo;
}
