package com.ajouway.storage.repository.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityInfoJpaRepository extends JpaRepository<AmenityInfoEntity, Long> {
    Boolean existsByBuildingIdAndAmenityInfoType(Long buildingId, AmenityInfoType amenityInfoType);
}
