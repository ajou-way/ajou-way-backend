package com.ajouway.dto.map;

import com.ajouway.infra.persistence.entity.map.AmenityInfo;
import com.ajouway.infra.persistence.entity.map.BuildingMarker;
import com.ajouway.dto.GeoJsonPoint;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record BuildingMarkerResponse (
        Long id,
        String name,
        GeoJsonPoint geometry,
        String remarks,
        String imgUrl,
        List<AmenityInfoSimpleResponse> amenityInfos
){
    public static BuildingMarkerResponse fromEntity(final BuildingMarker buildingMarker){
        return BuildingMarkerResponse.builder()
                .id(buildingMarker.getId())
                .name(buildingMarker.getName())
                .geometry(GeoJsonPoint.converter(buildingMarker.getGeometry()))
                .remarks(buildingMarker.getRemarks())
                .imgUrl(buildingMarker.getImgUrl())
                .amenityInfos(buildingMarker.getAmenityInfos()
                        .stream()
                        .map(AmenityInfoSimpleResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();

    }
}
