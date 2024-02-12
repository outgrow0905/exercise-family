package com.latte.cj.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class BusinessResponse {
    private String request_cnt;
    private String match_cnt;
    private String status_code;
    private List<BusinessData> data;
}
