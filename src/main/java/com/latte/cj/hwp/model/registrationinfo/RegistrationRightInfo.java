package com.latte.cj.hwp.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRightInfo {
    private String titleOfInvention;
    private String expirationDate;
    private String terminationCauseName;
    private String terminationDate;
    private String applicationNumber;
}
