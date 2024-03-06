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
@Table(name = "cj_app_applicant_info")
@Entity
public class ApplicantInfo {
    @Id
    private String applicationNumber; 		//	출원번호
    @Id
    private String address; // 	출원인주소
    @Id
    private String code; // 	특허고객번호
    @Id
    private String country; // 	출원인국가
    private String engName; // 	출원인명
    private String name; // 	출원인명
}
