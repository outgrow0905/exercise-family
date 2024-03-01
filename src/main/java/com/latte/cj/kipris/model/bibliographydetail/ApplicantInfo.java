package com.latte.cj.kipris.model.bibliographydetail;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantInfo {
    private String address; // 	출원인주소
    private String code; // 	특허고객번호
    private String country; // 	출원인국가
    private String engName; // 	출원인명
    private String name; // 	출원인명
}
