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
@Table(name = "cj_app_international_info")
@Entity
public class InternationalInfo {
    @Id
    private String applicationNumber; 		//	출원번호
    @Id
    private String internationOpenDate; //	국제공개일자
    @Id
    private String internationOpenNumber; //	국제공개번호
    @Id
    private String internationalApplicationDate; //	국제출원일자
    @Id
    private String internationalApplicationNumber; //	국제출원번호
}
