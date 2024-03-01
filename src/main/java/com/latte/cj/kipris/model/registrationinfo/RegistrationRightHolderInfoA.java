package com.latte.cj.kipris.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cj_registration_right_holder_infoa")
public class RegistrationRightHolderInfoA {
	@Id
	private String registrationNumber; //	등록번호
	@Id
	private Integer rankNumber; //	순위번호
	@Id
	private Integer rankCorrelatorSerialNumber; //	순위관련자일련번호
	@Id
	private String rankCorrelatorType; //	순위관련자종류
	@Id
	private String rankCorrelatorName; //	순위관련자명
	private String rankCorrelatorAddress; //	순위관련자주소
	@ManyToOne
	private RegistrationRightInfo registrationRightInfo;
}
