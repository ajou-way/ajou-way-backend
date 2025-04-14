package com.ajouway.domain.service.map;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.dto.map.BuildingAmenityAddRequest;
import com.ajouway.dto.map.BuildingMarkerSimpleResponse;
import com.ajouway.infra.persistence.entity.map.BuildingMarker;
import com.ajouway.dto.map.BuildingMarkerResponse;
import com.ajouway.infra.persistence.repository.map.AmenityInfoJpaRepository;
import com.ajouway.infra.persistence.repository.map.BuildingMarkerJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {

    private final BuildingMarkerJpaRepository buildingMarkerRepository;
    private final AmenityInfoJpaRepository amenityInfoRepository;

    @Transactional(readOnly = true)
    public BuildingMarkerResponse getBuildingMarker(final Long buildingId) {
        final BuildingMarker buildingMarker = getBuildingMarkerById(buildingId);
        return BuildingMarkerResponse.fromEntity(buildingMarker);
    }

    @Transactional(readOnly = true)
    public List<BuildingMarkerSimpleResponse> getBuildingMarkers(){
        final List<BuildingMarker> buildingMarkers = new ArrayList<>();
        return buildingMarkerRepository.findAll()
                .stream()
                .map(BuildingMarkerSimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public void addAmenityForBuilding(final BuildingAmenityAddRequest request){
        final BuildingMarker buildingMarker = getBuildingMarkerById(request.buildingId());
        amenityInfoRepository.save(request.toEntity(buildingMarker));
    }

    @Transactional(readOnly = true)
    public BuildingMarker getBuildingMarkerById(final Long id){
        return buildingMarkerRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomExceptionInfo.BUILDING_NOT_FOUND));
    }
}