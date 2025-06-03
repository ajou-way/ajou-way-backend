package com.ajouway.domain.repository;

import com.ajouway.domain.enums.FacilityType;
import com.ajouway.storage.entity.map.FacilityEntity;

import java.util.List;

public interface FacilityRepository {
    List<FacilityEntity> findAll();

    List<FacilityEntity> findByFacilityType(FacilityType facilityType);
}
