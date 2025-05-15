package com.ajouway.storage.repository.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityInfoJpaRepository extends JpaRepository<AmenityInfo, Long> {
    Boolean existsByBuildingIdAndAmenityInfoType(Long buildingId, AmenityInfoType amenityInfoType);
}
