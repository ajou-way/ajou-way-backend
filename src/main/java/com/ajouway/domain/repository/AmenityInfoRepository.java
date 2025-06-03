package com.ajouway.domain.repository;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfoEntity;

public interface AmenityInfoRepository {
    AmenityInfoEntity save(AmenityInfoEntity amenityInfo);

    AmenityInfoEntity getById(Long amenityId);

    boolean existsByBuildingIdAndAmenityInfoType(Long buildingId, AmenityInfoType amenityInfoType);
}
