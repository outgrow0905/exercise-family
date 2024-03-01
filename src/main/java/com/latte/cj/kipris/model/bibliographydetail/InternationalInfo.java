package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternationalInfo {
    private String internationOpenDate; //	국제공개일자
    private String internationOpenNumber; //	국제공개번호
    private String internationalApplicationDate; //	국제출원일자
    private String internationalApplicationNumber; //	국제출원번호
}
