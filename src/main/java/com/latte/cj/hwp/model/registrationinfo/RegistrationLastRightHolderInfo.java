package com.latte.cj.hwp.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationLastRightHolderInfo {
    private String lastRightHolderNumber;
    private String lastRightHolderName;
    private String lastRightHolderAddress;
    private String lastRightHolderCountry;
}
