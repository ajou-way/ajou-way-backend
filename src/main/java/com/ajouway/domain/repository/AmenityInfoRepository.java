package com.ajouway.domain.repository;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfo;

public interface AmenityInfoRepository {
    AmenityInfo save(AmenityInfo amenityInfo);

    AmenityInfo getById(Long amenityId);

    boolean existsByBuildingIdAndAmenityInfoType(Long buildingId, AmenityInfoType amenityInfoType);
}
