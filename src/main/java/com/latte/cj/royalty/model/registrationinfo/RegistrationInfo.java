package com.latte.cj.royalty.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationInfo {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RegistrationLastRightHolderInfo> registrationLastRightHolderInfo = new ArrayList<>();
    private RegistrationRightInfo registrationRightInfo;
}
