package com.latte.cj.royalty.model.dto;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoyaltyCodeExcelDto {
	private String title;
	private Set<String> royaltyCodes;
}
