package com.latte.cj.kipris.feign.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRoyaltyStatusRequest {
    private String ServiceKey;
    private String applicationNumber;
}
