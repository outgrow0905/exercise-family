package com.latte.cj.royalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latte.cj.kipris.model.bibliographydetail.BiblioSummaryInfo;
import com.latte.cj.kipris.model.registrationinfo.RegistrationRightInfo;

public interface BiblioSummaryInfoRepository extends JpaRepository<BiblioSummaryInfo, String> {
}
