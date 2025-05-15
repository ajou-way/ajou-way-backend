package com.ajouway.dto.map.response;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.dto.GeoJsonPoint;
import com.ajouway.storage.entity.map.AmenityInfo;
import com.ajouway.storage.entity.map.Building;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record BuildingSimpleResponse(
        Long id,
        String name,
        GeoJsonPoint geometry,
        String remarks,
        String imgUrl,
        List<AmenityInfoType> amenityInfoTypes
) {
    public static BuildingSimpleResponse fromEntity(final Building building) {
        return BuildingSimpleResponse.builder()
                .id(building.getId())
                .name(building.getName())
                .geometry(GeoJsonPoint.converter(building.getGeometry()))
                .remarks(building.getRemarks())
                .imgUrl(building.getImgUrl())
                .amenityInfoTypes(building.getAmenityInfos()
                        .stream()
                        .map(AmenityInfo::getAmenityInfoType)
                        .collect(Collectors.toList()))
                .build();
    }
}