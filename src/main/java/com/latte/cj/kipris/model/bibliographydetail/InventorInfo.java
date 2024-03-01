package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventorInfo {
    private String address; //	발명자주소
    private String code; //	특허고객번호(발명자번호)
    private String country; //	발명자국가
    private String engName; //	발명자명
    private String name; //	발명자명
}
