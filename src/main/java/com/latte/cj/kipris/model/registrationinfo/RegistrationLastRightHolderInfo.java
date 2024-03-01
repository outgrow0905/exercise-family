package com.latte.cj.kipris.model.registrationinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cj_registration_last_right_holder_info")
public class RegistrationLastRightHolderInfo {
    @Id
    private String registrationNumber; //	등록번호
    @Id
    private String lastRightHolderNumber; // 최종권리자번호(특허고객번호)
    private String lastRightHolderName; // 최종권리자명
    private String lastRightHolderAddress; // 최종권리자주소
    private String lastRightHolderCountry; // 최종권리자국가
    @ManyToOne
    private RegistrationRightInfo registrationRightInfo;
}
