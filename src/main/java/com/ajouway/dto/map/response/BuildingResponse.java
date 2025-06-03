package com.ajouway.dto.map.response;

import com.ajouway.storage.entity.map.BuildingEntity;
import com.ajouway.dto.GeoJsonPoint;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record BuildingResponse(
        Long id,
        String name,
        GeoJsonPoint geometry,
        String remarks,
        String imgUrl,
        List<AmenityInfoResponse> amenityInfos
){
    public static BuildingResponse fromEntity(final BuildingEntity building){
        return BuildingResponse.builder()
                .id(building.getId())
                .name(building.getName())
                .geometry(GeoJsonPoint.converter(building.getGeometry()))
                .remarks(building.getRemarks())
                .imgUrl(building.getImgUrl())
                .amenityInfos(building.getAmenityInfos()
                        .stream()
                        .map(AmenityInfoResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();

    }
}
