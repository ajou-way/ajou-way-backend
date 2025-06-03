package com.ajouway.dto.map;

import com.ajouway.domain.enums.FacilityType;
import com.ajouway.dto.GeoJsonPoint;
import com.ajouway.storage.entity.map.FacilityEntity;
import lombok.Builder;

@Builder
public record FacilityResponse(
        Long id,
        FacilityType facilityMarkerType,
        GeoJsonPoint geometry,
        String remarks,
        String imgUrl,
        Long buildingId,
        String buildingName
) {
    public static FacilityResponse fromEntity(final FacilityEntity facility){
        return FacilityResponse.builder()
                .id(facility.getId())
                .facilityMarkerType(facility.getFacilityType())
                .geometry(GeoJsonPoint.converter(facility.getGeometry()))
                .remarks(facility.getRemarks())
                .imgUrl(facility.getImgUrl())
                .buildingId(facility.getBuilding().getId())
                .buildingName(facility.getBuilding().getName())
                .build();
    }
}
