package com.ajouway.domain.service.map;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.repository.AmenityInfoRepository;
import com.ajouway.domain.repository.BuildingRepository;
import com.ajouway.dto.map.response.AmenityInfoResponse;
import com.ajouway.dto.map.request.BuildingAmenityAddRequest;
import com.ajouway.dto.map.response.BuildingSimpleResponse;
import com.ajouway.storage.entity.map.AmenityInfo;
import com.ajouway.storage.entity.map.Building;
import com.ajouway.dto.map.response.BuildingResponse;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MapService {

    private final BuildingRepository buildingRepository;
    private final AmenityInfoRepository amenityInfoRepository;

    public BuildingResponse getBuildingMarker(final Long buildingId) {
        Building buildingMarker = buildingRepository.getById(buildingId);
        return BuildingResponse.fromEntity(buildingMarker);
    }

    public List<BuildingSimpleResponse> getBuildingMarkers() {
        return buildingRepository.findAll()
                .stream()
                .map(BuildingSimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public AmenityInfoResponse addAmenityForBuilding(final Long buildingId, final BuildingAmenityAddRequest request) {
        if (amenityInfoRepository.existsByBuildingIdAndAmenityInfoType(buildingId, request.amenityInfoType())) {
            throw new CustomException(CustomExceptionInfo.ALREADY_EXIST_AMENITY_INFO_TYPE);
        }
        Building buildingMarker = buildingRepository.getById(buildingId);
        AmenityInfo amenityInfo = amenityInfoRepository.save(request.toEntity(buildingMarker));
        return AmenityInfoResponse.fromEntity(amenityInfo);
    }

    @Transactional
    public AmenityInfoResponse updateAmenityForBuilding(final Long amenityId, final BuildingAmenityAddRequest request) {
        AmenityInfo amenityInfo = amenityInfoRepository.getById(amenityId);
        return AmenityInfoResponse.fromEntity(amenityInfo.update(request.contents()));
    }
}