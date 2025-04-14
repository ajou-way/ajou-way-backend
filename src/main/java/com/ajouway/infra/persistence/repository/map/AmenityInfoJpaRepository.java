package com.ajouway.infra.persistence.repository.map;

import com.ajouway.infra.persistence.entity.map.AmenityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityInfoJpaRepository extends JpaRepository<AmenityInfo, Long> {
}
