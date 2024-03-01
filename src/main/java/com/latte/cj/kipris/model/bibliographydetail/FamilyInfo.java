package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyInfo {
    private String familyApplicationNumber; // 패밀리 출원번호
}
