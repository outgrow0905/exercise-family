package com.latte.cj.royalty.model.registrationinfo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRightHolderInfo {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<RegistrationRightHolderInfoA> registrationRightHolderInfoA = new ArrayList<>();
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<RegistrationRightHolderInfoB> registrationRightHolderInfoB = new ArrayList<>();
}
