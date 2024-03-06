package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cj_app_inventor_info")
@Entity
public class InventorInfo {
    @Id
    private String applicationNumber; 		//	출원번호
    private String address; //	발명자주소
    private String code; //	특허고객번호(발명자번호)
    @Id
    private String country; //	발명자국가
    private String engName; //	발명자명
    @Id
    private String name; //	발명자명
}
