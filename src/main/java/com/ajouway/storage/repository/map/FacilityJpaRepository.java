package com.ajouway.storage.repository.map;

import com.ajouway.domain.enums.FacilityType;
import com.ajouway.storage.entity.map.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityJpaRepository extends JpaRepository<FacilityEntity, Long> {
    List<FacilityEntity> findAllByFacilityType(FacilityType facilityType);
}
