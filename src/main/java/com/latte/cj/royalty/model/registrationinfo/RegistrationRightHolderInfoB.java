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
public class RegistrationRightHolderInfoB {
	@Id
	private String registrationNumber; //	등록번호
	@Id
	private Integer rankNumber; //	순위번호
	@Id
	private String documentName; //	서류명
	@Id
	private String receiptDate; //	접수일자
	private String registrationCauseName; //	등록원인명
	private String indicationOfEvent; //	사건의표시
	@ManyToOne
	private RegistrationRightInfo registrationRightInfo;
}
