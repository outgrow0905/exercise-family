package com.latte.cj.royalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latte.cj.royalty.model.registrationinfo.RegistrationRightInfo;

public interface RegistrationRightInfoRepository extends JpaRepository<RegistrationRightInfo, String> {
}
