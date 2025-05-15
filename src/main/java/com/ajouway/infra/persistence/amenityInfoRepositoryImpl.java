package com.ajouway.infra.persistence;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.domain.repository.AmenityInfoRepository;
import com.ajouway.storage.entity.map.AmenityInfo;
import com.ajouway.storage.repository.map.AmenityInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class amenityInfoRepositoryImpl implements AmenityInfoRepository {
    private final AmenityInfoJpaRepository amenityInfoJpaRepository;

    @Override
    public AmenityInfo save(AmenityInfo amenityInfo) {
        return amenityInfoJpaRepository.save(amenityInfo);
    }

    @Override
    public AmenityInfo getById(Long amenityId) {
        return amenityInfoJpaRepository.findById(amenityId)
                .orElseThrow(() -> new CustomException(CustomExceptionInfo.AMENITY_NOT_FOUND));
    }

    @Override
    public boolean existsByBuildingIdAndAmenityInfoType(Long buildingMarkerId, AmenityInfoType amenityInfoType) {
        return amenityInfoJpaRepository.existsByBuildingIdAndAmenityInfoType(buildingMarkerId, amenityInfoType);
    }
}
