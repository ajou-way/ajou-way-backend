package com.ajouway.dto.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.dto.GeoJsonPoint;
import com.ajouway.infra.persistence.entity.map.AmenityInfo;
import com.ajouway.infra.persistence.entity.map.BuildingMarker;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record BuildingMarkerSimpleResponse(
        Long id,
        String name,
        GeoJsonPoint geometry,
        String remarks,
        String imgUrl,
        List<AmenityInfoType> amenityInfoTypes
) {
    public static BuildingMarkerSimpleResponse fromEntity(final BuildingMarker buildingMarker) {
        return BuildingMarkerSimpleResponse.builder()
                .id(buildingMarker.getId())
                .name(buildingMarker.getName())
                .geometry(GeoJsonPoint.converter(buildingMarker.getGeometry()))
                .remarks(buildingMarker.getRemarks())
                .imgUrl(buildingMarker.getImgUrl())
                .amenityInfoTypes(buildingMarker.getAmenityInfos()
                        .stream()
                        .map(AmenityInfo::getAmenityInfoType)
                        .collect(Collectors.toList()))
                .build();
    }
}