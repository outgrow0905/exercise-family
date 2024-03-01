package com.latte.cj.royalty.repository;

import com.latte.cj.royalty.model.royaltystatussummary.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Item, String> {
}
