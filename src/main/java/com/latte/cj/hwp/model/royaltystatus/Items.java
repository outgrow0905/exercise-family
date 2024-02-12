package com.latte.cj.hwp.model.royaltystatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.latte.cj.hwp.model.registrationinfo.RegistrationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private Item item;
}
